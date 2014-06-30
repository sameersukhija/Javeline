package com.lumata.e4o.system.tasks;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.network.SSHExecClient;
import com.lumata.common.testing.system.Service;
import com.lumata.e4o.system.environment.ExpressionKernelCommands.TaskStatus;

public class TaskManager {

	private static final Logger logger = LoggerFactory.getLogger( TaskManager.class );
	
	private final String JAVA_PATH_ = "/usr/local/java/bin/java ";
	private final String JAVA_CLASSPATH_ = "-classpath /usr/local/actrule/lib/actruleFramework.jar:/usr/local/actrule/lib/log4j.jar com.act750.actrulefwk.management.cli.JVMRuntimeClient ";
	private final String JAVA_OPTIONS = "-host ${host} -port ${port} -domain ${domain} -tenantid ${tenantId} -type ${type} -paramoperations ${paramoperations} -values ${values}";
	
	private final String DEFAULT_HOST_ = "127.0.0.1";
	private final String DEFAULT_PORT_ = "23500";
	private final String DEFAULT_DOMAIN_ = "act750";
	private final String DEFAULT_TENANT_ID_ = "1";
	private final String DEFAULT_TYPE_ = "\"Schedulers\"";
	private final String DEFAULT_PARAMOPERATIONS_ = "execute";
	
	private String host;
	private String port;
	private String domain;
	private String tenantId;
	private String type;
	private String paramoperations;
	
	private SSHExecClient sshExec;
	
	/** available tasks */
	public enum Task {
		ExpiredData,
		AggregateData,
		ProvisionCampaigns,
		SubscriberDelayedRemoval;			
	}
	
	public TaskManager( Service sshService, String sshUser ) {
		
		sshExec = new SSHExecClient( sshService, sshUser );
				
		this.host = DEFAULT_HOST_;
		this.port = DEFAULT_PORT_;
		this.domain = DEFAULT_DOMAIN_;
		this.tenantId = DEFAULT_TENANT_ID_;
		this.type = DEFAULT_TYPE_;
		this.paramoperations = DEFAULT_PARAMOPERATIONS_;
		
	}
	
	public TaskManager( Service sshService, String sshUser, String host, String port, String domain, String tenantId, String type, String paramoperations ) {
		
		sshExec = new SSHExecClient( sshService, sshUser );
		
		this.host = host;
		this.port = port;
		this.domain = domain;
		this.tenantId = tenantId;
		this.type = type;
		this.paramoperations = paramoperations;
		
	}
	
	public static TaskManager exec( Service sshService, String sshUser ) {
				
		return new TaskManager( sshService, sshUser );
		
	}
	
	public static TaskManager exec( Service sshService, String sshUser, String host, String port, String domain, String tenantId, String type, String paramoperations ) {
		
		return new TaskManager( sshService, sshUser, host, port, domain, tenantId, type, paramoperations );
		
	}
	
	private String buildCommand( Task task, Object... parameters ) {
		
		StringBuilder command = new StringBuilder();
		
		StringBuilder commandParameters = new StringBuilder();
		
		commandParameters.append(task.name() );
		
		if( null != parameters ) {
			
			for( int p = 0; p < parameters.length; p++ ) {
				
				commandParameters.append( ", " ).append( parameters[ p ] );
				
			}
					
		}
						
		command.
			append( JAVA_PATH_ ).
			append( JAVA_CLASSPATH_ ).
			append( JAVA_OPTIONS.
						replace( "${host}", host ).
						replace( "${port}", port ).
						replace( "${domain}", domain ).
						replace( "${tenantId}", tenantId ).
						replace( "${type}", type ).
						replace( "${paramoperations}", paramoperations ).
						replace( "${values}", commandParameters.toString() )
			);
		
		return command.toString();
		
	}
	
	private TaskExecution.ExecutionStatus execCommand( Task task, String command ) {
		
		ArrayList<String> result = sshExec.execCommand( command );
		
		/** parse result */
		for( int i = 0; i < result.size(); i++ ) {
			
			Pattern pattern = Pattern.compile( ".+execute=(OK|KO|ALREADY_DONE)" );
			
			Matcher matcher = pattern.matcher( result.get( i ) );
			
			if( matcher.find() ) { 
				
				switch( TaskExecution.ExecutionStatus.valueOf( matcher.group( 1 ) ) ) {
				
					case OK: { 
						
						logger.info( "task " + task.name() + " executed with " + TaskStatus.OK + " status" );
						
						return TaskExecution.ExecutionStatus.OK; 
						
					}
					case KO: { 
						
						logger.info( "task " + task.name() + " executed with " + TaskStatus.KO + " status" );
						
						return TaskExecution.ExecutionStatus.KO; 
						
					}
					case ALREADY_DONE: { 
						
						logger.info( "task " + task.name() + " executed with " + TaskStatus.ALREADY_DONE + " status" );
						
						return TaskExecution.ExecutionStatus.ALREADY_DONE; 
						
					}
					default: { return TaskExecution.ExecutionStatus.UNDEFINED; }
				
				}
				
			}
			
		}
		
		return TaskExecution.ExecutionStatus.UNDEFINED;
				
	}
	
	public TaskExecution ExpiredData() {
		
		return new TaskExecution( execCommand( Task.ExpiredData, buildCommand( Task.ExpiredData, new Object[ 0 ] ) ) );
		
	}
	
	/*
	public TaskExecution ExpiredData( Boolean force, String table ) {
		
		Object[] parameters = new Object[ 2 ];
		
		parameters[ 0 ] = force;
		parameters[ 1 ] = table;
		
		System.out.println( buildCommand( Task.ExpiredData, new Object[ 0 ] ) );
		
		return new TaskExecution( execCommand( Task.ExpiredData, buildCommand( Task.ExpiredData, parameters ) ) );
		
	}
	*/
	
}
