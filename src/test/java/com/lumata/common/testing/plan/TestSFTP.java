package com.lumata.common.testing.plan;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.SftpException;
import com.lumata.common.testing.network.SFTPClient;

/**
 * Created with IntelliJ IDEA.
 * User: adipasquale
 * Date: 7/23/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestSFTP {

    private static final Logger logger = LoggerFactory.getLogger(TestSFTP.class);

    @Test()
    public void ssh() {

    	String user = "root";
    	String host = "10.120.38.25";
    	int port = 22;
    	
        SFTPClient sftp = new SFTPClient( host, port, user, "cmdISnJjdQ==" );
        
        sftp.getFile( "/opt/lumata/server_test_expression-qa/cdr/" , "cdr.log" );
    	
        try {
        
        	Vector<LsEntry> v = sftp.getChannel().ls( "/opt/lumata/server_test_expression-qa/cdr/" );
        
        
	        for(int i = 0; i < v.size(); i++){
	//			out.println(vv.elementAt(ii).toString());
	
	            Object obj= v.elementAt( i );
	            
	            System.out.println(((LsEntry)obj).getLongname());
	        
	        }
        
        } catch( SftpException e ) {
        	logger.error( e.getMessage(), e );
        }
        
        /*
    	String password = "mysql";

        String encrypted_password = Security.encrypt( password );

        logger.info( encrypted_password );
        
        String decrypted_password = Security.decrypt( encrypted_password );

        logger.info( decrypted_password );
        
        /*Assert.assertEquals( password, decrypted_password );*/

        //Assert.assertEquals( password, decrypted_password );
        
        
    }


}
