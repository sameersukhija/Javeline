package com.lumata.e4o.tasks;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.network.SSHExecClient;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.KernelCommands;
import com.lumata.e4o.system.environment.ExpressionKernelCommands;

/**
 * Created with IntelliJ IDEA.
 * User: adipasquale
 * Date: 7/23/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestSSHExec {

    private static final Logger logger = LoggerFactory.getLogger(TestSSHExec.class);

    @Test( enabled = true )
    public void ssh_set_time() {

    	String user = "root";
    	String host = "192.168.56.151";
    	int port = 22;
    	
        SSHExecClient sshExec = new SSHExecClient( host, port, user, "bXZ2aw==" );
        	
    	System.out.println( "Connected" );
    	
    	//String command = "date +%D%T -s \"2014-02-01 01:10:10\"";
    	
    	Calendar cal = Calendar.getInstance();
    	cal.set( Calendar.DATE, cal.get( Calendar.DATE ) + 1 );
    	System.out.println( KernelCommands.getSetDateTime( cal ) );
    	sshExec.execCommand( KernelCommands.getSetDateTime( cal ) );    	
    	
    }
    
    @Test( enabled = false )
    public void ssh_run_expression() throws EnvironmentException {

    	Environment env = new Environment( "input/environments", "E4O_VM", IOFileUtils.IOLoadingType.RESOURCE );
    	/*
    	String user = "root";
    	String host = "192.168.56.151";
    	int port = 22;
    	
        SSHExecClient sshExec = new SSHExecClient( host, port, user, "bXZ2aw==" );
        	
    	System.out.println( "Connected" );
    	   	    	
    	//sshExec.execCommand( ExpressionKernelCommands.ExpressionSh.start.getCommand() ); 
    	
    	//sshExec.execCommand( ExpressionKernelCommands.CheckCollectorService.status.getCommand() ); 
    	
    	sshExec.execCommand( "initctl collector start" );
    	*/
    	
    	//ExpressionKernelCommands.stopCollector( env );
    	
    	
    	
    	//System.out.println( ExpressionKernelCommands.collectorServiceStatus( env ) );
    	
    	//System.out.println( ExpressionKernelCommands.collectorStatus( env ) );
    	
    	
    	
    	//ExpressionKernelCommands.collectorServiceStart( env );
    	//ExpressionKernelCommands.collectorServiceStop( env );
    	
      	//ExpressionKernelCommands.collectorStart( env );
    	//ExpressionKernelCommands.collectorStop( env );
    	
    	//ExpressionKernelCommands.cdrwriterStart( env );
    	//ExpressionKernelCommands.cdrwriterStop( env );
    	
    	
    	
    	
    }

}
