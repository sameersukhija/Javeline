package com.lumata.common.testing.plan;

import java.util.ArrayList;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.SftpException;
import com.lumata.common.testing.network.SFTPClient;
import com.lumata.common.testing.network.SFTPClient.CopyType;

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
        
        String remote_path = "/opt/lumata/server_test_expression-qa/cdr/";
        String remote_file_name = "cdr.log.2013-08-23";
        
        String local_path = "/home/adipasquale/";
        String local_file_name = remote_file_name;
                
        ArrayList<LsEntry> fileList = sftp.listDirectory( remote_path );
        
        sftp.printDirectory( fileList );
        
        sftp.copyFile( remote_path , remote_file_name, local_path, local_file_name, SFTPClient.CopyType.REMOTE );
    	
        
        
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
