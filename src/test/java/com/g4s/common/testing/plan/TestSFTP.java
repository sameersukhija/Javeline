package com.g4s.common.testing.plan;

import java.util.ArrayList;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import com.g4s.common.testing.network.SFTPClient;
import com.g4s.common.testing.network.SSHExecClient;
import com.g4s.common.testing.network.SFTPClient.CopyType;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.SftpException;

/**
 * Created with IntelliJ IDEA.
 * User: adipasquale
 * Date: 7/23/13
 * Time: 9:32 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestSFTP {

    private static final Logger logger = LoggerFactory.getLogger(TestSFTP.class);

    @Test( enabled = false )
    public void ssh_copy_remote_to_local() {

    	String user = "root";
    	String host = "10.120.38.25";
    	int port = 22;
    	
        SFTPClient sftp = new SFTPClient( host, port, user, "cmdISnJjdQ==" );
       
        if( sftp.isConnected() ) {
        	
        	String remote_path = "/opt/lumata/server_test_expression-qa/cdr/";
            String remote_file_name = "cdr.log.2013-12-20";
            
            String local_path = "/home/adipasquale/";
            String local_file_name = remote_file_name;
                
            ArrayList<LsEntry> fileList = sftp.listDirectory( remote_path );
            
            sftp.printDirectory( fileList );
            
            sftp.copyFile( remote_path , remote_file_name, local_path, local_file_name, SFTPClient.CopyType.REMOTE_TO_LOCAL );
        	        	
        }        
        
    }
    
    @Test( enabled = false )
    public void ssh_copy_local_to_remote() {

    	String user = "root";
    	String host = "10.120.38.25";
    	int port = 22;
    	
        SFTPClient sftp = new SFTPClient( host, port, user, "cmdISnJjdQ==" );
       
        if( sftp.isConnected() ) {
        	
        	String local_path = System.getProperty( "user.dir" ) + "/src/main/resources/lumata-common-testing/";
			String local_file_name = "save.properties";
			System.out.println( local_path );
			String remote_path = "/nfsdata/files/cdr/incoming/REVENUE_CDR/";
            String remote_file_name = local_file_name;
                        
            sftp.copyFile( local_path, local_file_name, remote_path , remote_file_name, SFTPClient.CopyType.LOCAL_TO_REMOTE );
        	        	
        }        
        
    }   

}
