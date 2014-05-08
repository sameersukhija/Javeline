package com.lumata.e4o.system.performance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.database.Mysql;
import com.lumata.common.testing.exceptions.EnvironmentException;
import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.system.Environment;
import com.lumata.e4o.system.performance.GenerateTokenThread;

public class RunGenerateTokens {

	private static final Logger logger = LoggerFactory.getLogger( RunGenerateTokens.class );
	
	public static void main(String args[]) throws EnvironmentException, IOFileException {
		
		Environment env= new Environment( "input/environments", "e4o_qa", IOFileUtils.IOLoadingType.RESOURCE );
		
		Mysql mysql = new Mysql( env.getDataSource( "qa" ) );
		
		String query = new String("SELECT COUNT(*) count FROM token;");
		
		int initial_count = 0;
		
		ResultSet rs = mysql.execQuery( query );
		
		try {
		
			while( rs.next() ) {
				
				initial_count = rs.getInt( "count" );
				
			}
		
		} catch( SQLException e ) {}
		
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		
		ArrayList<GenerateTokenThread> threads = new ArrayList<GenerateTokenThread>();
		
		final int N_THREADS = 10;
		
		for( int i = 0; i < N_THREADS; i++ ) {
			
			threads.add( new GenerateTokenThread(Thread.MAX_PRIORITY, env) );
			
		}
		
		long startTime = System.currentTimeMillis();
		
		for( int i = 0; i < N_THREADS; i++ ) {
			
			threads.get( i ).startThread();
			/*
			try {
				
				Thread.sleep(300);
			
			} catch (Exception e){}
			*/
			
		}

		try {
		
			Thread.sleep(10000);
		
		} catch (Exception e){}
		
		for( int i = 0; i < N_THREADS; i++ ) {
			
			threads.get( i ).stopThread();
			
		}
		
		long diffTime = System.currentTimeMillis() - startTime;  
        int decSeconds = (int)(diffTime % 1000 / 100);  
        int seconds = (int)(diffTime / 1000 % 60);  
        int minutes = (int)(diffTime / 60000 % 60);  
        int hours = (int)(diffTime / 3600000);
        String s = String.format("%d:%02d:%02d.%d", hours, minutes, seconds, decSeconds);
        
        /*
		try {
    		
			Thread.sleep(60000);
		
		} catch (Exception e) {}
        */
        //logger.info( s );
        
        int final_count = 0;
		
		rs = mysql.execQuery( query );
		
		try {
		
			while( rs.next() ) {
				
				final_count = rs.getInt( "count" );
				
			}
		
		} catch( SQLException e ) {}
		
		
        StringBuilder result = new StringBuilder();
        int total = 0;
        
        for( int i = 0; i < N_THREADS; i++ ) {
			
			total = total + threads.get( i ).getRequestCount();
			
			result.append( "Requests ( " )
					.append( i )
					.append( " ): " )
					.append( threads.get( i ).getRequestCount() )
					.append( "\n" );
			
		}
        
        //logger.info( "\nTotal: " + total + "\n" + result.toString() );
        int mysql_count = final_count - initial_count;
        
        IOFileUtils.saveResource( "Time: " + s + "\nMysqlCount: " + mysql_count + "\nTotal: " + total + "\n" + result.toString(), "output/results", "result.txt" );
        
	}
	
}
