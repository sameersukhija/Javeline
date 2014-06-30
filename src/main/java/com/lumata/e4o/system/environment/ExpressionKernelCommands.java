package com.lumata.e4o.system.environment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.network.SFTPClient;
import com.lumata.common.testing.network.SSHExecClient;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.KernelCommands;
import com.lumata.common.testing.system.Service;
import com.lumata.common.testing.system.User;

public class ExpressionKernelCommands extends KernelCommands {

	private static final Logger logger = LoggerFactory.getLogger( ExpressionKernelCommands.class );
	
	Service service;
	String user;
	
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

	/** process statuses */
	public enum TaskStatus {
		OK,
		KO,
		ALREADY_DONE,
		UNDEFINED;			
	}
	
	/** process statuses */
	public enum Task {
		ExpiredData,
		AggregateData,
		ProvisionCampaigns,
		SubscriberDelayedRemoval;			
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

	public ExpressionKernelCommands() {}

	public ExpressionKernelCommands( Service service, String user ) {
		this.service = service;
		this.user = user;
	}

	public Service getService() {
		return this.service;
	}

	public String getUser() {
		return this.user;
	}
	
	public void setService( Service service ) {
		this.service = service;
	}

	public void setUser( String user ) {
		this.user = user;
	}

	/** get datetime from remote server */
	public Calendar getServerDateTime() {
		
		ArrayList<String> result = this.execCommand( KernelCommands.getDateTime() );
		
		Calendar systemDate = Calendar.getInstance();
	    
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    
		for( int i = 0; i < result.size(); i++ ) {
			
			try {
				systemDate.setTime( sdf.parse( result.get( i ) ) );
			} catch( ParseException e ) {
				logger.error( e.getMessage(), e );
				return null;
			}
		    
		}
		
		return systemDate;
		
	}
	
	public Boolean setServerDatetime( Calendar date ) {
		
		return setServerDatetime( date, false );
		
	}
	
	/** set datetime on remote server */
	public Boolean setServerDatetime( Calendar date, Boolean remoteServicesRestart ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		
		Pattern patter_server_datetime = Pattern.compile( "[0-9]{1,4}[-][0-9]{1,2}[-][0-9]{1,2}[ ]+[0-9]{1,2}[:][0-9]{1,2}[:][0-9]{1,2}" );
		 
		ArrayList<String> result = this.execCommand( KernelCommands.getSetDateTime( date ) );
		
		for( int i = 0; i < result.size(); i++ ) {
			
			Matcher matcher_server_datetime = patter_server_datetime.matcher( result.get( i ) );
			
			if( matcher_server_datetime.find() ) { 
				
				try {
					
					this.setRemoteDateFile( sdf.format( date.getTime() ) );
				
				} catch (IOFileException e) {
					
					logger.error( e.getMessage(), e );
					
				}
				
				logger.info( Log.PUTTING.createMessage( "the datetime " + sdf.format( date.getTime() ) + " has been set correctly in the remote server" ) );
				
				return true; 
			
			}
								
		}
				
		logger.warn( Log.PUTTING.createMessage( "the datetime " + sdf.format( date.getTime() ) + " has not been set correctly in the remote server" ) );
		
		return false;
		
	}

	/** exec remote command */
	private ArrayList<String> execCommand( String command ) {
		
		SSHExecClient sshExec = new SSHExecClient( service, user );
		
		return sshExec.execCommand( command );
		
	}
	
	private void setRemoteDateFile( String remoteDate ) throws IOFileException {
		
		StringBuilder remoteDateFile = new StringBuilder();
		
		remoteDateFile.
			append( "#!/bin/sh\n\n" ).
			append( "currentDate=false\n\n" ).
			append( "# specific date setting\n" ).
			append( "if [ \"$currentDate\" = false ] ; then\n" ).
			append( "\tdate +%DT -s \"" ).append( remoteDate ).append( "\"\n" ).
			append( "fi" );

		IOFileUtils.saveResource( remoteDateFile.toString(), "/system/", "changeDate.sh" );
		
		SFTPClient sftp = new SFTPClient( service, user );
		
		if( sftp.isConnected() ) {
			
			String local_path = System.getProperty( "user.dir" ) + "/output/system/";
			
            sftp.copyFile( local_path, "changeDate.sh", "/opt/scripts/" , "changeDate.sh", SFTPClient.CopyType.LOCAL_TO_REMOTE );
            		
		}
		
	}
	
	/** remove pid files */
	private void removePIDFiles( ExpressionScript process ) {
		
		ArrayList<String> pidFileRemoveCommands = process.getRemovePIDCommands(); 
		
		for( int i = 0; i < pidFileRemoveCommands.size(); i++ ) {
			
			this.execCommand( pidFileRemoveCommands.get( i ) );
		
		}
		
	}
	
	/** get collector daemon service status */
	public ProcessStatus collectorServiceStatus() {
		
		ArrayList<String> result = this.execCommand( ExpressionKernelCommands.ExpressionScript.collectorService.getSSHCommand( ProcessCommand.status ) );
		
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
	public Boolean collectorServiceStart() {
		
		if( this.collectorServiceStatus().equals( ProcessStatus.stopped ) ) {
		
			this.execCommand( ExpressionKernelCommands.ExpressionScript.collectorService.getSSHCommand( ProcessCommand.start ) );
			
			return this.collectorServiceStatus().equals( ProcessStatus.started );
			
		}		
		
		return true;
		
	}
	
	/** stop collector daemon service */
	public Boolean collectorServiceStop() {		
		
		if( this.collectorServiceStatus().equals( ProcessStatus.started ) ) {
		
			this.execCommand( ExpressionScript.collectorService.getSSHCommand( ProcessCommand.stop ) );
			
			if( this.collectorServiceStatus().equals( ProcessStatus.stopped ) ) {
				
				this.removePIDFiles( ExpressionScript.collectorService );
				
				return true;
				
			} else { return false; }
			
		} 

		return true; 			
		
	}
	
	
	/** get collector process status */
	public ProcessStatus collectorStatus() {
		
		ArrayList<String> result = this.execCommand( ExpressionScript.collector.getSSHCommand( ProcessCommand.status ) );
		
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
	public Boolean collectorStart() {
		
		if( this.collectorStatus().equals( ProcessStatus.stopped ) ) {
		
			this.execCommand( ExpressionScript.collector.getSSHCommand( ProcessCommand.start ) );
			
			return this.collectorStatus().equals( ProcessStatus.started );
		}		
		
		return true;
		
	}
	
	/** stop collector process */
	public Boolean collectorStop() {
		
		if( this.collectorStatus().equals( ProcessStatus.started ) ) {
		
			this.execCommand( ExpressionScript.collector.getSSHCommand( ProcessCommand.stop ) );
			
			if( this.collectorStatus().equals( ProcessStatus.stopped ) ) {
				
				this.removePIDFiles( ExpressionScript.collector );
				
				return true;
				
			} else { return false; }
			
		}		
		
		return true;
		
	}	
	
	/** get cdrwriter process status */
	public ProcessStatus cdrwriterStatus() {
		
		ArrayList<String> result = this.execCommand( ExpressionScript.cdrwriter.getSSHCommand( ProcessCommand.status ) );
		
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
	public Boolean cdrwriterStart() {
		
		if( this.cdrwriterStatus().equals( ProcessStatus.stopped ) ) {
		
			this.execCommand( ExpressionScript.cdrwriter.getSSHCommand( ProcessCommand.start ) );
			
			return this.cdrwriterStatus().equals( ProcessStatus.started );
		}		
		
		return true;
		
	}
	
	/** stop cdrwriter process */
	public Boolean cdrwriterStop() {
		
		if( this.cdrwriterStatus().equals( ProcessStatus.started ) ) {
		
			this.execCommand( ExpressionScript.cdrwriter.getSSHCommand( ProcessCommand.stop ) );
			
			if( this.cdrwriterStatus().equals( ProcessStatus.stopped ) ) {
				
				this.removePIDFiles( ExpressionScript.cdrwriter );
				
				return true;
				
			} else { return false; }
			
		}		
		
		return true;
		
	}	
	
	/** get expression process status */
	public ProcessStatus expressionStatus() {
		
		ArrayList<String> result = this.execCommand( ExpressionScript.expression.getSSHCommand( ProcessCommand.status ) );
		
		Pattern patter_started = Pattern.compile( "expression-server[ 0-9a-zA-Z()]+running" );
		Pattern patter_stopped = Pattern.compile( "expression-server[ 0-9a-zA-Z()]+stopped" );
		
		for( int i = 0; i < result.size(); i++ ) {
			
			Matcher matcher_started = patter_started.matcher( result.get( i ) );
			if( matcher_started.find() ) { return ProcessStatus.started; }
			
			Matcher matcher_stopped = patter_stopped.matcher( result.get( i ) );
			if( matcher_stopped.find() ) { return ProcessStatus.stopped; }
			
		}
		
		return null;
		
	}

	/** start expression process */
	public Boolean expressionStart() {
		
		if( this.expressionStatus().equals( ProcessStatus.stopped ) ) {
		
			this.execCommand( ExpressionScript.expression.getSSHCommand( ProcessCommand.start ) );
			
			return this.expressionStatus().equals( ProcessStatus.started );
		}		
		
		return true;
		
	}
	
	/** stop expression process */
	public Boolean expressionStop() {
		
		if( this.expressionStatus().equals( ProcessStatus.started ) ) {
		
			this.execCommand( ExpressionScript.expression.getSSHCommand( ProcessCommand.stop ) );
			
			if( this.expressionStatus().equals( ProcessStatus.stopped ) ) {
				
				this.removePIDFiles( ExpressionScript.expression );
				
				return true;
				
			} else { return false; }
			
		}		
		
		return true;
		
	}	
	
	/** restart expression process */
	public Boolean expressionRestart() {
		
		return expressionStop() && expressionStart();
		
	}
	
	/** exec tasks */
	public TaskStatus execTask( Integer port, Integer tenantId, Task task ) {
		
		StringBuilder execCommand = new StringBuilder();
		
		/** exec task command */
		execCommand.append( "/usr/local/java/bin/java -classpath /usr/local/actrule/lib/actruleFramework.jar:/usr/local/actrule/lib/log4j.jar com.act750.actrulefwk.management.cli.JVMRuntimeClient -host 127.0.0.1 -port " )
					.append( port )
					.append( " -domain act750 -tenantid " )
					.append( tenantId )
					.append( " -type \"Schedulers\" -paramoperations execute -value ")
					.append( task );		
			
		logger.info( "executing task: " + task );
		
		logger.info( "command: " + execCommand.toString() );
		
		ArrayList<String> result = this.execCommand( execCommand.toString() );
		
		/** parse result */
		for( int i = 0; i < result.size(); i++ ) {
			System.out.println( result.get( i ) );			
			Pattern pattern = Pattern.compile( ".+execute=(OK|KO|ALREADY_DONE)" );
			
			Matcher matcher = pattern.matcher( result.get( i ) );
			
			if( matcher.find() ) { 
				
				switch( matcher.group( 1 ) ) {
				
					case "OK": { 
						
						logger.info( "task " + task + " executed with " + TaskStatus.OK + " status" );
						
						return TaskStatus.OK; 
						
					}
					case "KO": { 
						
						logger.info( "task " + task + " executed with " + TaskStatus.KO + " status" );
						
						return TaskStatus.KO; 
						
					}
					case "ALREADY_DONE": { 
						
						logger.info( "task " + task + " executed with " + TaskStatus.ALREADY_DONE + " status" );
						
						return TaskStatus.ALREADY_DONE; 
						
					}				
				}
				
			}
			
		}
				
		return TaskStatus.UNDEFINED;
		
	}
	
}
