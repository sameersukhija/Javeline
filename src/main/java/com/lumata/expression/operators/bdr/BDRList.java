package com.lumata.expression.operators.bdr;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.network.SFTPClient;
import com.lumata.common.testing.system.RemoteService;

public class BDRList {

	private static final Logger logger = LoggerFactory.getLogger( BDRList.class );
	
	private ArrayList<BDR> bdrList;
	private final String CDR_REMOTE_DIR = "/opt/lumata/server_test_expression-qa/cdr/";
	private final String CDR_LOCAL_DIR = System.getProperty( "user.dir" ) + "/src/main/resources/input/cdr/";
	public enum BDRLoadingType { FILE, DATABASE }
	
	public BDRList( RemoteService rs, Date date  ) {
		
		try {
			
			this.bdrList = new ArrayList<BDR>();
			
			// Get Remote BDR file
			SFTPClient sftpClient = new SFTPClient( rs.getHost(), rs.getPort(), rs.getUser(), rs.getPassword() );
			
			String bdr_remote_file_name = this.generateBDRFileName( date );
			String bdr_local_file_name = bdr_remote_file_name.replaceAll( "\\.", "_" ) + ".txt";
			
			sftpClient.copyFile( CDR_REMOTE_DIR, bdr_remote_file_name, CDR_LOCAL_DIR, bdr_local_file_name, SFTPClient.CopyType.REMOTE_TO_LOCAL );
			
			BufferedReader br = IOFileUtils.loadFileAsBufferedReader( CDR_LOCAL_DIR, bdr_local_file_name );
			
			String line;
			
			while ((line = br.readLine()) != null) {
		         
				BDR bdr = new BDR( line );
				
				this.add( bdr );
				
				System.out.println(line);
		      
			} 
			
		} catch( IOFileException e ) {
			
			logger.error( e.getMessage(), e );
			
		} catch( IOException e ) {
			
			logger.error( e.getMessage(), e );
			
		}
		
	}
	
	public String generateBDRFileName( Date date ) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String filename = "cdr.log." + sdf.format( date ); 
		
		return filename;
		
	}
	
	public void add( BDR bdr  ) {
		
		this.bdrList.add( bdr );
		
	}
	
	public BDR get( int index ) {
		
		return this.bdrList.get( index );
		
	}
	
	public ArrayList<BDR> getLast( int rows ) {
		
		ArrayList<BDR> bdrs = new ArrayList<BDR>();
		
		int begin = ( this.bdrList.size() - rows > 0 ? this.bdrList.size() - rows : 0 );
				
		for( int i = begin; i < this.bdrList.size(); i++ ) {
			
			bdrs.add( this.bdrList.get( i ) );
		
		}
		
		return bdrs;
		
	}
	
	public ArrayList<BDR> getBy( String fieldType, String fieldValue ) {
		
		ArrayList<BDR> bdrs = new ArrayList<BDR>();
					
		for( int i = 0; i < this.bdrList.size(); i++ ) {
			
			BDR bdr = this.bdrList.get( i );
			
			if( bdr.get( fieldType ).equals( fieldValue ) ) {
				
				bdrs.add( bdr );
				
			}
						
		}
		
		return bdrs;
		
	}
	
	public int size() {
		
		return this.bdrList.size();
		
	}

}
