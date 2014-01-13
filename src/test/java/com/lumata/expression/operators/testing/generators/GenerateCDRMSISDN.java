package com.lumata.expression.operators.testing.generators;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.io.IOFileUtils.IOLoadingType;
import com.lumata.common.testing.log.Log;
import com.lumata.common.testing.network.SFTPClient;
import com.lumata.common.testing.system.Environment;
import com.lumata.common.testing.system.Environment.ServicesType;
import com.lumata.expression.operators.generators.CDRMSISDNGenerator;
import com.lumata.expression.operators.generators.CDRVouchersGenerator;
import com.lumata.expression.operators.generators.VouchersGenerator;

public class GenerateCDRMSISDN {

	private static final Logger logger = LoggerFactory.getLogger( GenerateCDRMSISDN.class );
	
	Environment env;
	Mysql mysql;
	
	/* 	Initialize Environment */
	@Parameters({"browser", "environment", "tenant" })
	@BeforeSuite
	public void init( @Optional("FIREFOX") String browser, @Optional("E4O_QA") String environment, @Optional("qa") String tenant ) throws EnvironmentException {		
		
		logger.info( Log.LOADING.createMessage( "init" , "environment" ) );
		
		env = new Environment( "input/environments", environment, IOFileUtils.IOLoadingType.RESOURCE );
		
		mysql = new Mysql( env.getDataSource( tenant ) );
				
	}
	
	@Test( priority = 1, enabled = true )
	public void generateCDRMSISDN() throws IOFileException {
		
		CDRMSISDNGenerator.generate( mysql, "input/cdr", "CDR_MSISDN_EVENTS", IOLoadingType.RESOURCE);
		
	}
	
	@Test( priority = 2, enabled = true )
	public void moveCDRMSISDN() throws IOFileException {
			
		try {
		
			JSONObject ssh = env.getServiceType( ServicesType.SSH );
			
			SFTPClient sftp = new SFTPClient( ssh.getString( "host" ), ssh.getInt( "port" ), ssh.getString( "user" ), ssh.getString( "password" ) );
			
			if( sftp.isConnected() ) {
				
				String local_path = System.getProperty( "user.dir" ) + "/src/main/resources/input/cdr/";
				String local_file_name = "import_CDR_MSISDN_EVENTS.csv";
				
				String remote_path = "/nfsdata/files/cdr/incoming/REVENUE_CDR/";
	            String remote_file_name = local_file_name;
	            
	            sftp.copyFile( local_path, local_file_name, remote_path , remote_file_name, SFTPClient.CopyType.LOCAL_TO_REMOTE );
	        				
			}
						
		} catch( JSONException e ) {}
	
	}
	
}
