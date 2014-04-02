package com.lumata.e4o.system.cdr;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.e4o.system.cdr.annotations.BundleBalance;
import com.lumata.e4o.system.cdr.annotations.BundleName;
import com.lumata.e4o.system.cdr.annotations.BundlePurchased;
import com.lumata.e4o.system.cdr.annotations.Location;
import com.lumata.e4o.system.cdr.annotations.Msisdn;
import com.lumata.e4o.system.cdr.annotations.Amount;
import com.lumata.e4o.system.cdr.annotations.Balance;
import com.lumata.e4o.system.cdr.annotations.Date;
import com.lumata.e4o.system.cdr.annotations.DeactivationDate;
import com.lumata.e4o.system.cdr.annotations.Delay;
import com.lumata.e4o.system.cdr.annotations.Download;
import com.lumata.e4o.system.cdr.annotations.Duration;
import com.lumata.e4o.system.cdr.annotations.NewNetwork;
import com.lumata.e4o.system.cdr.annotations.NewProfile;
import com.lumata.e4o.system.cdr.annotations.NewRatePlan;
import com.lumata.e4o.system.cdr.annotations.NewStatus;
import com.lumata.e4o.system.cdr.annotations.NewSubProfile;
import com.lumata.e4o.system.cdr.annotations.NewSubscriptionDate;
import com.lumata.e4o.system.cdr.annotations.OldNetwork;
import com.lumata.e4o.system.cdr.annotations.OldProfile;
import com.lumata.e4o.system.cdr.annotations.OldRatePlan;
import com.lumata.e4o.system.cdr.annotations.OldStatus;
import com.lumata.e4o.system.cdr.annotations.OldSubProfile;
import com.lumata.e4o.system.cdr.annotations.OldSubscriptionDate;
import com.lumata.e4o.system.cdr.annotations.Sms;
import com.lumata.e4o.system.cdr.annotations.TenantId;
import com.lumata.e4o.system.cdr.annotations.Terminating;
import com.lumata.e4o.system.cdr.annotations.Type;
import com.lumata.e4o.system.cdr.annotations.Upload;
import com.lumata.e4o.system.cdr.annotations.ValidityDate;
import com.lumata.e4o.system.cdr.annotations.VoucherCode;
import com.lumata.e4o.system.csv.annotations.CSVMethod;

public class CDRClassGenerator {	
	
	private static final Logger logger = LoggerFactory.getLogger( CDRClassGenerator.class );
	
	StringBuilder import_classes;
	boolean import_calendar_package;
	boolean import_enum_package;
	boolean import_json_package;
	
	final String CDR_PACKAGE = "com.lumata.e4o.system.cdr";
	final String CDR_TYPES_PACKAGE = "com.lumata.e4o.system.cdr.types";
	final String CDR_ANNOTATIONS_PACKAGE = "com.lumata.e4o.system.cdr.annotations";
	final String CSV_PACKAGE = "com.lumata.e4o.system.csv";
	final String CSV_TYPES_PACKAGE = "com.lumata.e4o.system.csv.types";
	
	/** CDR types definition */
	private enum CDRTypes {
		
		History {	
			public List<Class<? extends Annotation>> fields() {
				List<Class<? extends Annotation>> fields = new ArrayList<Class<? extends Annotation>>();
				fields.add( Msisdn.class );
				return fields;
			}
		},
		Revenue {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, Amount.class, Balance.class, ValidityDate.class, DeactivationDate.class, Type.class, Delay.class );
			}
		},
		RevenueMultitenant {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, Amount.class, Balance.class, ValidityDate.class, DeactivationDate.class, Type.class, Delay.class, TenantId.class );
			}
		},
		Bundle {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, BundleName.class, BundleBalance.class, BundlePurchased.class );
			}
		},
		Voucher {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, VoucherCode.class, Date.class, Location.class );
			}
		},
		LifeCycle {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, NewRatePlan.class, OldRatePlan.class, NewProfile.class, OldProfile.class, NewSubProfile.class, OldSubProfile.class, NewStatus.class, OldStatus.class, NewNetwork.class, OldNetwork.class, NewSubscriptionDate.class, OldSubscriptionDate.class );
			}
		},
		Call {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, Duration.class, Amount.class, Balance.class, Terminating.class );
			}
		},
		CallMultitenant {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, Duration.class, Amount.class, Balance.class, Terminating.class, TenantId.class );
			}
		},
		Data {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, Amount.class, Download.class, Upload.class, Balance.class );
			}
		},
		Message {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, Amount.class, Sms.class, Balance.class );
			}
		},
		OtherUsage {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, Amount.class, Balance.class );
			}
		};
		
		public abstract List<Class<? extends Annotation>> fields();
		
	}
	
	public void generate() throws IOFileException, ClassNotFoundException {
						
		/** Load CSV type classes */
		Map<String, String> csv_type_classes = loadCSVTypeClasses(); 
				
		if( csv_type_classes != null ) {
			
			/** Generate CDR classes */ 
			for( CDRTypes cdr_type : CDRTypes.values() ) {	
				
				/** Import needed packages */
				import_calendar_package = true;
				import_enum_package = true;
				import_json_package = true;				
				
				import_classes = new StringBuilder();
				import_classes.append( "import " ).append( CDR_PACKAGE ).append( ".CDR;\n" );
				import_classes.append( "import " ).append( CDR_ANNOTATIONS_PACKAGE + ".*;\n" );
				import_classes.append( "import com.lumata.expression.operators.exceptions.CDRException;\n" );
				//import_classes.append( "import org.slf4j.Logger;\n" );
				//import_classes.append( "import org.slf4j.LoggerFactory;\n" );
								
				/** Build methods to add to new CDR Type class */	
				StringBuilder cdr_type_methods = new StringBuilder();
				cdr_type_methods.append( "\tpublic int getFieldsCount() {\n\t\treturn this.FIELDS;\n\t}\n\n" );
				cdr_type_methods.append( this.generateMethods( cdr_type, csv_type_classes ) );
														
				/** Define new CDR Type class name */
				final StringBuilder class_name = new StringBuilder();
				class_name.append( "CDR" ).append( cdr_type.name() );
				
				/** Define new CDR Type path name */
				final StringBuilder filePath = new StringBuilder();
		    	filePath.append( System.getProperty( "user.dir" ) ).append( "/src/main/java/" ).append( CDR_TYPES_PACKAGE.toString().replaceAll( "[.]" , "/" ) );
								
		    	/** Define new CDR Type logger class
				final StringBuilder logger_class = new StringBuilder();
				logger_class.append( "\tprivate static final Logger logger = LoggerFactory.getLogger( " ).append( class_name ).append( ".class );" );
				 */
		    	
				/** Define new CDR Type fields count */
				final StringBuilder cdr_type_fields = new StringBuilder();
				cdr_type_fields.append( "\tprivate final int FIELDS = " ).append( cdr_type.fields().size() ).append( ";\n\n" );
								
				
				/** Build new CDR Type class */
				final StringBuilder cdr_type_class = new StringBuilder();
				cdr_type_class.append( "package " )							
								.append( CDR_TYPES_PACKAGE )
								.append( ";\n\n" )
								.append( import_classes )
								.append( "\n" )
								.append( "public class " )
								.append( class_name )
								.append( " extends CDR { \n\n" )
								//.append( logger_class )
								//.append( "\n\n" )
								.append( cdr_type_fields )								
								.append( this.generateDefaultConstructor( class_name.toString() ) )
								.append( "\n" )
								.append( cdr_type_methods )
								.append( "}" );
				
				/** Store new CDR Type class */
				IOFileUtils.saveFile( cdr_type_class.toString(), filePath.toString(), class_name + ".java");
								
			}
			
		}	
		
	}

	/** Load CSV Type classes */
	private Map<String, String> loadCSVTypeClasses() throws IOFileException {
		
		Map<String, String> csvTypeClasses = new HashMap<String, String>();
				
		StringBuilder dir = new StringBuilder();
		
		dir.append( System.getProperty( "user.dir" ) )
			.append( "/src/main/java/" )
			.append( CSV_TYPES_PACKAGE.replace( "." , "/" ) )
			.append( "/" );
		
		File directory = new File( dir.toString() );
 
		if( directory.isDirectory() ) {
		
			File[] fList = directory.listFiles();
			 
	        for( File file : fList ) {
	            
	        	if( file.isFile() ) {	                
	                csvTypeClasses.put( file.getName().replace( ".java", ""), IOFileUtils.loadFileAsString( dir.toString(), file.getName() ) );
	            }
	        	
	        }
	        
		}
        				
		return csvTypeClasses;
		
	}

	/** Build CDR Type Class Default constructor */
	public StringBuilder generateDefaultConstructor( String class_name ) {
		
		StringBuilder constructor = new StringBuilder();
		
		constructor.append( "\tpublic " ).append( class_name ).append( "() {\n\t\tsuper();\n\t} \n" );
		
		return constructor;
		
	}
	
	/** Build CDR Type Class Methods Body */
	private StringBuilder generateMethods( CDRTypes cdr_type, Map<String, String> csv_type_classes ) throws ClassNotFoundException {
		
		/** Load CDR parent class */
		CDR cdr_class = new CDR();
		
		/** CDR Type Class Methods Body */
		StringBuilder cdr_type_methods = new StringBuilder();
		
		/** Load CDR type fields */
		for( int f = 0; f < cdr_type.fields().size(); f++ ) {
		
			/** Load CDR ( father class ) fields */
			for( Field cdr_field : cdr_class.getClass().getDeclaredFields() ) {
				
				if( cdr_field.isAnnotationPresent( cdr_type.fields().get( f ) ) ) {
					
					/** Define cdr type field */
					String cdr_type_field = cdr_type.fields().get( f ).getSimpleName();
					
					/** Get CSV Type class */
					Class<?> csv_type_class = Class.forName( CSV_TYPES_PACKAGE + "." + cdr_field.getType().getSimpleName() );
					
					/** Get CSV Type methods */
					for( Method csv_type_method : csv_type_class.getDeclaredMethods() ) {
							
						/** Get CSV Type annotated methods only*/
						if( csv_type_method.isAnnotationPresent( CSVMethod.class ) ) {
							
							/** Create new cdr type method parameters regex */
							StringBuilder parameter_regex = new StringBuilder();
							
							for( Class<?> parameter : csv_type_method.getParameterTypes() ) {
								
								parameter_regex.append( ".*" ).append( parameter.getName().replaceAll( "[0-9a-zA-Z]+[.]", "" ) ).append( "[ _<>?a-zA-Z0-9]+," ) ;
								
							}
							
							if( parameter_regex.length() > 0 ) { parameter_regex.setLength( parameter_regex.length() - 1 ); }
							
							/** Create new cdr type method returned type expression */
							StringBuilder method_returned_type = new StringBuilder();					
							method_returned_type.append( csv_type_method.toString().replace( CDR_PACKAGE + ".CDR.", "" ).replaceAll( csv_type_method.getName() + ".*" , "" ).replaceAll( "[0-9a-zA-Z]+[.]" , "" ) );
														
							/** Create new cdr type method regex expression */
							StringBuilder method_regex = new StringBuilder();					
							method_regex.append( method_returned_type ).append( csv_type_method.getName() ).append( "[ ]*[(]" ).append( parameter_regex ).append( "[)][ _0-9a-zA-Z]*" );
							
							/** Get method from CSV Type Class */						
							Pattern pattern_csv_type_method = Pattern.compile( method_regex.toString() );
							Matcher matcher_csv_type_method = pattern_csv_type_method.matcher( csv_type_classes.get( cdr_field.getType().getSimpleName() ) );
							
							if( matcher_csv_type_method.find() ) {
								
								/** Get csv type managed field name */
								String csv_type_field = csv_type_class.getAnnotations()[0].getClass().getInterfaces()[0].getSimpleName();
																
								/** Build cdr type method class body */
								String csv_type_method_call = matcher_csv_type_method.group(0).replace( method_returned_type.toString(), "" ).replaceAll( "[ ]*throws[ a-zA-Z]*", "" );
								
								for( java.lang.reflect.Type param_type : csv_type_method.getGenericParameterTypes() ) {
									
									String method_class_body_regex = param_type.toString().replaceAll( ".+[.](.+[ <>?]+extends).+[.](.+)", "$1 $2" ).replaceAll( ".+[.]", "" ).replace( "?" , "[?]") + "[ ]+";
									
									csv_type_method_call = csv_type_method_call.replaceAll( method_class_body_regex, "").replaceAll( "final[ ]+", "" );
									
								}
								
								StringBuilder cdr_type_method_body = new StringBuilder();
								
								if( csv_type_method.getReturnType().toString().equals( "void" ) ) {
									
									cdr_type_method_body.append( "if( this." )
														.append( cdr_field.getName() )
														.append(" != null ) { this.")
														.append( cdr_field.getName() )
														.append( "." )
														.append( csv_type_method_call.trim() )
														.append( "; }" );							
								
								} else {
									
									cdr_type_method_body.append( "return this." )
														.append( cdr_field.getName() )
														.append(".")
														.append( csv_type_method_call.trim() )
														.append( ";" );
									
								}
								
								StringBuilder cdr_type_method = new StringBuilder();
								
								cdr_type_method.append( "\t" )
												.append( ( !csv_type_method.getReturnType().toString().equals( "void" ) ? "@" + cdr_type.fields().get( f ).getSimpleName() + "( position = " + f + " )\n\t" : "" ) )
												.append( matcher_csv_type_method.group(0).replace( csv_type_method.getName(), csv_type_method.getName().replace( csv_type_field.replace( "CSVField" , "" ), cdr_type_field ) ) )
												.append( " {\n\t\t" )
												.append( cdr_type_method_body )
												.append( "\n\t}\n\n" );
																				
								/** Put cdr type method */
								cdr_type_methods.append( cdr_type_method );
																
								/** Check needed packages */
								
								/** Put Calendar packages if it needs */						
								Pattern pattern_calendar = Pattern.compile( "Calendar" );
								Matcher matcher_calendar = pattern_calendar.matcher( matcher_csv_type_method.group(0) );
								if( matcher_calendar.find() && import_calendar_package ) {
									import_classes.append( "import java.util.Calendar;\n" );
									import_classes.append( "import " ).append( CSV_TYPES_PACKAGE ).append( ".CSVDateIncrement;\n" );
									import_calendar_package = false;
								}
								
								/** Put Enum packages if it needs */						
								Pattern pattern_enum = Pattern.compile( "ICSVEnum" );
								Matcher matcher_enum = pattern_enum.matcher( matcher_csv_type_method.group(0) );
								if( matcher_enum.find() && import_enum_package ) {
									import_classes.append( "import " ).append( CSV_TYPES_PACKAGE ).append( ".ICSVEnum;\n" );
									import_enum_package = false;
								}
								
								/** Put JSONObject or JSONArray packages if it needs */
								Pattern pattern_json = Pattern.compile( "JSONObject|JSONArray" );
								Matcher matcher_json = pattern_json.matcher( matcher_csv_type_method.group(0) );								
								if( matcher_json.find() && import_json_package ) {
									import_classes.append( "import org.json.*;\n" );
									import_json_package = false;
								}
															
							}							
														
						}
										
					}
										
				}
				
				
			}
			
		}
		
		return cdr_type_methods;
		
	}
	
}

// public void setMsisdn.*[(].*Integer[ a-zA-Z0-9]+,.*Integer[ a-zA-Z0-9]+,.*Integer[ a-zA-Z0-9]+[)]