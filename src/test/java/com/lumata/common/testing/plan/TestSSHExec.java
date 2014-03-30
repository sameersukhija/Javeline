package com.lumata.common.testing.plan;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.lumata.common.testing.network.SSHExecClient;
import com.lumata.common.testing.system.KernelCommands;

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
    	String host = "10.120.38.27";
    	int port = 22;
    	
        SSHExecClient sshExec = new SSHExecClient( host, port, user, "cmdISnJjdQ==" );
        	
    	System.out.println( "Connected" );
    	
    	//String command = "date +%D%T -s \"2014-02-01 01:10:10\"";
    	
    	Calendar cal = Calendar.getInstance();
    	//cal.set( Calendar.DATE, cal.get( Calendar.DATE ) - 1 );
    	
    	sshExec.execCommand( KernelCommands.getDateTime( cal ) );    	
    	
    }  

}
