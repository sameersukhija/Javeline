package com.lumata.e4o.system.environment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelExec;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.network.SSHExecClient;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.KernelCommands;

public class ExpressionKernelCommands extends KernelCommands {

	private static final Logger logger = LoggerFactory.getLogger( ExpressionKernelCommands.class );
	
	/** process statuses */
	private enum ProcessStatus {
		started,
		stopped;			
	}
	
	/** process commands */
	private enum ProcessCommand {
		start,
		restart,
		status,
		stop;		
	}
	
	/** expression scripts list */
	public enum ExpressionScript {
		
		expression("expression.sh") {
			public ArrayList<String> getPIDfiles() {
				
				ArrayList<String> pidFiles = new ArrayList<String>();
				//pidFiles.add("/usr/local/actrule/expression-collector.pid");
				//pidFiles.add("/var/lock/subsys/expression-collector");
				//pidFiles.add("/var/log/check_collector.log");
				
				return pidFiles;
				
			}
		},
		collector("collectorsctl") {
			public ArrayList<String> getPIDfiles() {
				
				ArrayList<String> pidFiles = new ArrayList<String>();
				pidFiles.add("/usr/local/actrule/expression-collector.pid");
				pidFiles.add("/var/lock/subsys/expression-collector");
				pidFiles.add("/var/log/check_collector.log");
				
				return pidFiles;
				
			}
		},
		collectorService("checkCollectorService") {
			public ArrayList<String> getPIDfiles() {
				
				ArrayList<String> pidFiles = new ArrayList<String>();
				pidFiles.add("/var/log/checkCollectorService.pid");
				
				return pidFiles;
				
			}
		},
		cdrwriter("cdrwriterctl") {
			public ArrayList<String> getPIDfiles() {
				
				ArrayList<String> pidFiles = new ArrayList<String>();
				pidFiles.add("/usr/local/actrule/expression-cdrwriter.pid");
				pidFiles.add("/var/lock/subsys/expression-cdrwriter");
				
				return pidFiles;
				
			}
		};
		
		private String value;
		public abstract ArrayList<String> getPIDfiles();
		
		ExpressionScript( String value ) {
			this.value = value;
		}
		
		public String getSSHCommand( ProcessCommand processCommand ) {
			
			StringBuilder command = new StringBuilder();
			
			command.append( "sh /usr/local/actrule/bin/" )
					.append( this.value )
					.append( " " )
					.append( processCommand.name() );
			
			return command.toString(); 			
		}
		
		public ArrayList<String> getRemovePIDCommands() {
			
			ArrayList<String> pidFiles = this.getPIDfiles();
			
			ArrayList<String> commands = new ArrayList<String>();
						
			for( int i = 0; i < pidFiles.size(); i++ ) {
				
				StringBuilder command = new StringBuilder();
				command.append( "rm -rf " ).append( pidFiles.get( i ) );
				commands.add( command.toString() );
			
			}
			
			return commands;
			
		}
		
	}
	
	/** set datetime on remote server */
	public static Boolean setDatetime( Environment env, Calendar date ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		
		Pattern patter_server_datetime = Pattern.compile( "[0-9]{1,2}[/][0-9]{1,2}[/][0-9]{1,2}[0-9]{1,2}[:][0-9]{1,2}[:][0-9]{1,2}" );
		 
		ArrayList<String> result = ExpressionKernelCommands.execCommand( env, KernelCommands.getDateTime( date ) );
		
		for( int i = 0; i < result.size(); i++ ) {
			
			Matcher matcher_server_datetime = patter_server_datetime.matcher( result.get( i ) );
			if( matcher_server_datetime.find() ) { 
				
				logger.info( Log.PUTTING.createMessage( "the datetime " + sdf.format( date.getTime() ) + " has been set correctly in the remote server" ) );
				return true; 
			
			}
								
		}
		
		logger.warn( Log.PUTTING.createMessage( "the datetime " + sdf.format( date.getTime() ) + " has not been set correctly in the remote server" ) );
		
		return false;
		
	}
	
	/** exec remote command */
	private static ArrayList<String> execCommand( Environment env, String command ) {
		
		ArrayList<String> result = null;
		
		JSONObject sshInfo = env.getServiceType( Environment.ServicesType.SSH );
		
		try {
			
			SSHExecClient sshExec = new SSHExecClient( sshInfo.getString( "host" ), sshInfo.getInt( "port" ), sshInfo.getString( "user" ), sshInfo.getString( "password" ) );
		
			result = sshExec.execCommand( command );
		
		} catch (JSONException e) {
			logger.error( e.getMessage(), e );
		}
				
		return result;
		
	}
	
	/** remove pid files */
	private static void removePIDFiles( Environment env, ExpressionScript process ) {
		
		ArrayList<String> pidFileRemoveCommands = process.getRemovePIDCommands(); 
		
		for( int i = 0; i < pidFileRemoveCommands.size(); i++ ) {
			
			ExpressionKernelCommands.execCommand( env, pidFileRemoveCommands.get( i ) );
		
		}
		
	}
	
	/** get collector daemon service status */
	public static ProcessStatus collectorServiceStatus( Environment env ) {
		
		ArrayList<String> result = ExpressionKernelCommands.execCommand( env, ExpressionKernelCommands.ExpressionScript.collectorService.getSSHCommand( ProcessCommand.status ) );
		
		Pattern patter_started = Pattern.compile( "[ a-zA-Z]+is up and running[ 0-9a-zA-Z]+" );
		Pattern patter_stopped = Pattern.compile( "[ a-zA-Z]+Stopped" );
		
		for( int i = 0; i < result.size(); i++ ) {
			
			Matcher matcher_started = patter_started.matcher( result.get( i ) );
			if( matcher_started.find() ) { return ProcessStatus.started; }
			
			Matcher matcher_stopped = patter_stopped.matcher( result.get( i ) );
			if( matcher_stopped.find() ) { return ProcessStatus.stopped; }
			
		}
		
		return null;
		
	}

	/** start collector daemon service */
	public static Boolean collectorServiceStart( Environment env ) {
		
		if( ExpressionKernelCommands.collectorServiceStatus( env ).equals( ProcessStatus.stopped ) ) {
		
			ExpressionKernelCommands.execCommand( env, ExpressionKernelCommands.ExpressionScript.collectorService.getSSHCommand( ProcessCommand.start ) );
			
			return ExpressionKernelCommands.collectorServiceStatus( env ).equals( ProcessStatus.started );
			
		}		
		
		return true;
		
	}
	
	/** stop collector daemon service */
	public static Boolean collectorServiceStop( Environment env ) {		
		
		if( ExpressionKernelCommands.collectorServiceStatus( env ).equals( ProcessStatus.started ) ) {
		
			ExpressionKernelCommands.execCommand( env, ExpressionKernelCommands.ExpressionScript.collectorService.getSSHCommand( ProcessCommand.stop ) );
			
			if( ExpressionKernelCommands.collectorServiceStatus( env ).equals( ProcessStatus.stopped ) ) {
				
				ExpressionKernelCommands.removePIDFiles( env, ExpressionKernelCommands.ExpressionScript.collectorService );
				
				return true;
				
			} else { return false; }
			
		} 

		return true; 			
		
	}
	
	
	/** get collector process status */
	public static ProcessStatus collectorStatus( Environment env ) {
		
		ArrayList<String> result = ExpressionKernelCommands.execCommand( env, ExpressionKernelCommands.ExpressionScript.collector.getSSHCommand( ProcessCommand.status ) );
		
		Pattern patter_started = Pattern.compile( "[ 0-9a-zA-Z()]+running" );
		Pattern patter_stopped = Pattern.compile( "[ a-zA-Z]stopped" );
		
		for( int i = 0; i < result.size(); i++ ) {
			
			Matcher matcher_started = patter_started.matcher( result.get( i ) );
			if( matcher_started.find() ) { return ProcessStatus.started; }
			
			Matcher matcher_stopped = patter_stopped.matcher( result.get( i ) );
			if( matcher_stopped.find() ) { return ProcessStatus.stopped; }
			
		}
		
		return null;
		
	}

	/** start collector process */
	public static Boolean collectorStart( Environment env ) {
		
		if( ExpressionKernelCommands.collectorStatus( env ).equals( ProcessStatus.stopped ) ) {
		
			ExpressionKernelCommands.execCommand( env, ExpressionKernelCommands.ExpressionScript.collector.getSSHCommand( ProcessCommand.start ) );
			
			return ExpressionKernelCommands.collectorStatus( env ).equals( ProcessStatus.started );
		}		
		
		return true;
		
	}
	
	/** stop collector process */
	public static Boolean collectorStop( Environment env ) {
		
		if( ExpressionKernelCommands.collectorStatus( env ).equals( ProcessStatus.started ) ) {
		
			ExpressionKernelCommands.execCommand( env, ExpressionKernelCommands.ExpressionScript.collector.getSSHCommand( ProcessCommand.stop ) );
			
			if( ExpressionKernelCommands.collectorStatus( env ).equals( ProcessStatus.stopped ) ) {
				
				ExpressionKernelCommands.removePIDFiles( env, ExpressionKernelCommands.ExpressionScript.collector );
				
				return true;
				
			} else { return false; }
			
		}		
		
		return true;
		
	}	
	
	// ---
	/** get cdrwriter process status */
	public static ProcessStatus cdrwriterStatus( Environment env ) {
		
		ArrayList<String> result = ExpressionKernelCommands.execCommand( env, ExpressionKernelCommands.ExpressionScript.cdrwriter.getSSHCommand( ProcessCommand.status ) );
		
		Pattern patter_started = Pattern.compile( "[ 0-9a-zA-Z()]+running" );
		Pattern patter_stopped = Pattern.compile( "[ a-zA-Z]stopped" );
		
		for( int i = 0; i < result.size(); i++ ) {
						 
			Matcher matcher_started = patter_started.matcher( result.get( i ) );
			if( matcher_started.find() ) { return ProcessStatus.started; }
			
			Matcher matcher_stopped = patter_stopped.matcher( result.get( i ) );
			if( matcher_stopped.find() ) { return ProcessStatus.stopped; }
			
		}
		
		return null;
		
	}

	/** start cdrwriter process */
	public static Boolean cdrwriterStart( Environment env ) {
		
		if( ExpressionKernelCommands.cdrwriterStatus( env ).equals( ProcessStatus.stopped ) ) {
		
			ExpressionKernelCommands.execCommand( env, ExpressionKernelCommands.ExpressionScript.cdrwriter.getSSHCommand( ProcessCommand.start ) );
			
			return ExpressionKernelCommands.cdrwriterStatus( env ).equals( ProcessStatus.started );
		}		
		
		return true;
		
	}
	
	/** stop cdrwriter process */
	public static Boolean cdrwriterStop( Environment env ) {
		
		if( ExpressionKernelCommands.cdrwriterStatus( env ).equals( ProcessStatus.started ) ) {
		
			ExpressionKernelCommands.execCommand( env, ExpressionKernelCommands.ExpressionScript.cdrwriter.getSSHCommand( ProcessCommand.stop ) );
			
			if( ExpressionKernelCommands.cdrwriterStatus( env ).equals( ProcessStatus.stopped ) ) {
				
				ExpressionKernelCommands.removePIDFiles( env, ExpressionKernelCommands.ExpressionScript.cdrwriter );
				
				return true;
				
			} else { return false; }
			
		}		
		
		return true;
		
	}	
	
}
