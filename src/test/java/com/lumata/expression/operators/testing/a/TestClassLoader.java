package com.lumata.expression.operators.testing.a;

import static com.lumata.common.testing.orm.Filter.all;
import static com.lumata.common.testing.orm.Filter.and;
import static com.lumata.common.testing.orm.Filter.avg;
import static com.lumata.common.testing.orm.Filter.concat;
import static com.lumata.common.testing.orm.Filter.count;
import static com.lumata.common.testing.orm.Filter.distinct;
import static com.lumata.common.testing.orm.Filter.lcase;
import static com.lumata.common.testing.orm.Filter.max;
import static com.lumata.common.testing.orm.Filter.mid;
import static com.lumata.common.testing.orm.Filter.min;
import static com.lumata.common.testing.orm.Filter.now;
import static com.lumata.common.testing.orm.Filter.op;
import static com.lumata.common.testing.orm.Filter.or;
import static com.lumata.common.testing.orm.Filter.round;
import static com.lumata.common.testing.orm.Filter.sum;
import static com.lumata.common.testing.orm.Filter.ucase;
import static com.lumata.common.testing.orm.Query.select;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.common.testing.orm.Statement;
import com.lumata.common.testing.system.Packages;
//import com.lumata.expression.operators.testing.pojo.autogenerator.Agencies;
//import com.lumata.expression.operators.testing.pojo.autogenerator.BdrEvents;
 
public class TestClassLoader{
 
	
		 
	    /**
	     * List all the files and folders from a directory
	     * @param directoryName to be listed
	     */
	    public static void listFilesAndFolders(String directoryName){
	 
	        File directory = new File(directoryName);
	 
	        //get all the files from a directory
	        File[] fList = directory.listFiles();
	 
	        for (File file : fList){
	            System.out.println(file.getName());
	        }
	    }
	 
	    /**
	     * List all the files under a directory
	     * @param directoryName to be listed
	     */
	    public static void listFiles(String directoryName){
	 
	        File directory = new File(directoryName);
	 
	        //get all the files from a directory
	        File[] fList = directory.listFiles();
	 
	        for (File file : fList){
	            if (file.isFile()){
	                System.out.println(file.getName());
	            }
	        }
	    }
	 
	    /**
	     * List all the folder under a directory
	     * @param directoryName to be listed
	     */
	    public static void listFolders(String directoryName){
	 
	        File directory = new File(directoryName);
	 
	        //get all the files from a directory
	        File[] fList = directory.listFiles();
	 
	        for (File file : fList){
	            if (file.isDirectory()){
	                System.out.println(file.getName());
	            }
	        }
	    }
	 
	    /**
	     * List all files from a directory and its subdirectories
	     * @param directoryName to be listed
	     */
	    public static void listFilesAndFilesSubDirectories(String directoryName){
	 
	        File directory = new File(directoryName);
	 
	        //get all the files from a directory
	        File[] fList = directory.listFiles();
	 
	        for (File file : fList){
	            if (file.isFile()){
	               // System.out.println( FilenameUtils.getExtension(file.getAbsolutePath()) );
	            	if( file.getAbsolutePath().endsWith( "Agencies$Fields.class" ) ) System.out.println(file.getAbsolutePath());
	            } else if (file.isDirectory()){
	                listFilesAndFilesSubDirectories(file.getAbsolutePath());
	            }
	        }
	    }
	
	    public static void main (String[] args) throws IOFileException {
	 
	    	/*
	    	Agencies agencies = new Agencies();
	    	BdrEvents bdr_events = new BdrEvents();
	    	
	    	//System.out.println( agencies.getClass().getSimpleName() );
	    	
	    	long startTime = System.currentTimeMillis();    	
	    	
	    	String complexQuery = select( 	max(Agencies.Fields.id),
					min(Agencies.Fields.id),
					avg(Agencies.Fields.id),
					all(Agencies.Fields.id),
					count(),
					//count(Agencies.Fields.id),
					ucase(Agencies.Fields.id),
					lcase(Agencies.Fields.id),
					round(Agencies.Fields.id, 1),
					now(),
					mid(Agencies.Fields.id, 3 ),
					//mid(Agencies.Fields.id, 1, 2),
					//sum( Agencies.Fields.id ),
					sum( " - ( Agencies.Fields.id * 20 ) + BdrEvents.Fields.msisdn " ),
					distinct(Agencies.Fields.id),
					concat(Agencies.Fields.id, "pippo1", 3),
					Agencies.Fields.id, 
					Agencies.Fields.name 
			)
		   .from( agencies )
		   .join( bdr_events )
		   .on( op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn ),
			    and( op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn ),
						 op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn )	
					),
				and( and( op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn ),
						 op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn )
					 ),
					 and( 
						or( op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn ),
							op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn )
						) 
					)
				),
				or( op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn ),
						 op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn )	
				)   
		   )
		   .join( agencies )
		   .on(	op( Agencies.Fields.id ).eq( BdrEvents.Fields.msisdn ))
		   .where( 	op( Agencies.Fields.id ).eq(1),
				   	and( op( Agencies.Fields.name ).in( 1, "pluto", "paperino" ) ),
					and( op( Agencies.Fields.id ).eq(1),
						 op( Agencies.Fields.id ).eq(1)	
					),
					and( and( op( BdrEvents.Fields.msisdn ).eq( "331234561" ),
							 op( Agencies.Fields.id ).eq(1)
						 ),
						 and( 
							or( op( Agencies.Fields.id ).eq(1),
								op( Agencies.Fields.id ).eq(1)
							) 
						)
					),
					or( op( Agencies.Fields.id ).eq(1),
							 op( Agencies.Fields.id ).eq(1)	
					)
			)
			.groupBy( Agencies.Fields.id )
			.having(op( Agencies.Fields.id ).eq(1))									
			.orderBy( Agencies.Fields.id )
			.limit( 1, 2 )
			.build();

	    	System.out.println( complexQuery );
	    	
	    	long diffTime = System.currentTimeMillis() - startTime;  
	        int decSeconds = (int)(diffTime % 1000 / 100);  
	        int seconds = (int)(diffTime / 1000 % 60);  
	        int minutes = (int)(diffTime / 60000 % 60);  
	        int hours = (int)(diffTime / 3600000);
	        String s = String.format("%d:%02d:%02d.%d", hours, minutes, seconds, decSeconds);
	        System.out.println( s );
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	/*
	    	Agencies agencies = new Agencies();

	    	Agencies.Fields test = Agencies.Fields.id;
	    	
	    	System.out.println( test.toString() );
	    	System.out.println( test.getDeclaringClass().getName() );
	    	System.out.println( test.getClass().getSimpleName() );
	    	*/
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	
	    	/*
	    	List<String> files = new ArrayList<String>();
	    	IOFileUtils.searchFile(System.getProperty( "user.dir"), "Agencies$Fields.class", files );
	    	
	    	System.out.println( "Class Path: " + files.size() );
	    		    	
	    	Class<?> test = null; 
	    	
	    	try {
	    		
	    		if( files.size() > 0 ) { 
	    			
	    			String packageClass = files.get( 0 ).replaceAll(".*(com.*)[.]class", "$1").replaceAll( "/" , ".");
	    			test = Class.forName( packageClass ); 
	    			
	    		}
	    
	    	} catch( ClassNotFoundException e ) {
	    		System.out.println( e.getMessage() );
	    	}
	    	*/
	    	
	    	
	    	/*--------------------------
	    	List<Class<?>> classList = Packages.getClassList( System.getProperty( "user.dir"), "Agencies$Fields.class", "com" );
	    	
	    	if( classList.size() > 0 ) {
	    		Enum<?> pippo = Enum.valueOf( (Class<Enum>)classList.get( 0 ), "id" );
	    		System.out.println( Statement.field(pippo) );
	    	}
	    	/*--------------------------
	    	 
	    	 
	    	 
	    	 */
	    	/*
	    	System.out.println( test );
	    	System.out.println( test.getTypeParameters().getClass() );
	    	if( test != null && test.isEnum() ) {  
	    		
	    		Enum<?> pippo = Enum.valueOf( (Class<Enum>)test, "id" );
	    		System.out.println( "is enum" );
	    		System.out.println( Statement.field(pippo) );
	    		//Statement.field( test );
	    	}
	    	
	    	/*
	    	System.out.println( IOFileUtils.searchFile(System.getProperty( "user.dir"), "Agencies$Fields.class") );
	    	
	        final String directoryLinuxMac =System.getProperty( "user.dir");
	        System.out.println( directoryLinuxMac );
	        //Windows directory example
	        //final String directoryWindows ="C://test";
	 
	        //listFilesUtil.
	        //listFiles(directoryLinuxMac);
	        System.out.println( "----------" );
	        //listFilesAndFilesSubDirectories(directoryLinuxMac);
	         * */
	         
	     
	    }
   
   

   

}