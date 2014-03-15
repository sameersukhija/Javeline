package com.lumata.expression.operators.system.cdr;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.io.IOFileUtils;
import com.lumata.expression.operators.system.cdr.annotations.Amount;
import com.lumata.expression.operators.system.cdr.annotations.Balance;
import com.lumata.expression.operators.system.cdr.annotations.Date;
import com.lumata.expression.operators.system.cdr.annotations.Download;
import com.lumata.expression.operators.system.cdr.annotations.Duration;
import com.lumata.expression.operators.system.cdr.annotations.Msisdn;
import com.lumata.expression.operators.system.cdr.annotations.Sms;
import com.lumata.expression.operators.system.cdr.annotations.TenantId;
import com.lumata.expression.operators.system.cdr.annotations.Terminating;
import com.lumata.expression.operators.system.cdr.annotations.Upload;

public class CDRClassGenerator {	
	
	StringBuilder import_classes;
	boolean import_calendar_package;
	boolean import_enum_package;
	
	final String CDR_PACKAGE = "com.lumata.expression.operators.system.cdr";
	
	// CDR types definition
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
				return Arrays.asList( Msisdn.class, Date.class, Amount.class, Balance.class );
			}
		},
		Call {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, Duration.class, Amount.class, Balance.class, Terminating.class );
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
	
	public void generate() throws IOFileException {
		
		// cdr subclass
		String cdr_class = null;		
				
		// Load CDR parent class
		cdr_class = this.loadCDRClass();
		
		if( cdr_class != null ) {
			
			// Generate CDR classes 
			for( CDRTypes cdr_type : CDRTypes.values() ) {	
				//System.out.println( cdr_type.name() );
				import_calendar_package = true;
				import_enum_package = true;
				
				// Define package name
				final String package_class = CDR_PACKAGE + ".types";
				
				import_classes = new StringBuilder();
				//import_classes.append( "import org.slf4j.Logger;\n" );
				//import_classes.append( "import org.slf4j.LoggerFactory;\n" );
				import_classes.append( "import " ).append( CDR_PACKAGE ).append( ".CDR;\n" );
				import_classes.append( "import " ).append( CDR_PACKAGE ).append( ".annotations.*;\n" );
				import_classes.append( "import com.lumata.expression.operators.exceptions.CDRException;\n" );
								
				// Define cdr subclass name
				final StringBuilder class_name = new StringBuilder();
				class_name.append( "CDR" ).append( cdr_type.name() );
								
				// Define cdr subclass path name
				final StringBuilder filePath = new StringBuilder();
		    	filePath.append( System.getProperty( "user.dir" ) ).append( "/src/main/java/" ).append( package_class.toString().replaceAll( "[.]" , "/" ) );
								
		    	// Define cdr subclass logger class				
				final StringBuilder logger_class = new StringBuilder();
				logger_class.append( "\tprivate static final Logger logger = LoggerFactory.getLogger( " ).append( class_name ).append( ".class );" );
				
				final StringBuilder fields = new StringBuilder();
				fields.append( "\tprivate final int FIELDS = " ).append( cdr_type.fields().size() ).append( ";\n\n" );
				
				// Define cdr subclass methods	
				StringBuilder methods_class = new StringBuilder();
				methods_class.append( "\tpublic int getFieldsCount() {\n\t\treturn this.FIELDS;\n\t}\n\n" );
				methods_class.append( this.generateMethods( cdr_class, cdr_type ) );
				
				// Define cdr subclass
				final StringBuilder cdr_subclass = new StringBuilder();
				cdr_subclass.append( "package " )							
							.append( package_class )
							.append( ";\n\n" )
							.append( import_classes )
							.append( "\n" )
							/*.append( "@Table( \"" ).append( tableName ).append( "\" )\n" )
				        	*/.append( "public class " )
							.append( class_name )
							.append( " extends CDR { \n\n" )
							//.append( logger_class )
							//.append( "\n\n" )
							.append( fields )
							/*.append( fieldsEnumFieldsPojoClass )
							.append( "\n\n" )
							.append( fieldsEnumPojoClass )							
							.append( ( fieldsEnumPojoClass.length() > 0 ) ? "\n\n" : "" )
							.append( fieldsPojoClass )
							.append( "\n" )*/
							.append( this.generateDefaultConstructor( class_name.toString() ) )
							.append( "\n" )
							.append( methods_class )
							.append( "\n }" );
				
				//System.out.println( cdr_subclass.toString() );
				
				//System.out.println( "---------------" );
	
				IOFileUtils.saveFile( cdr_subclass.toString(), filePath.toString(), class_name + ".java");
				
			}
			
		}	
		
	}
	
	// Load CDR parent class
	private String loadCDRClass() throws IOFileException {
		
		// Load CDR class
		Package cdr_class_package = CDR.class.getPackage();
		
		StringBuilder dir = new StringBuilder();
		
		dir.append( System.getProperty( "user.dir" ) )
			.append( "/src/main/java/" )
			.append( cdr_class_package.getName().replace( "." , "/" ) )
			.append( "/" );
		
		String file = "CDR.java";
		
		return IOFileUtils.loadFileAsString( dir.toString(), file );
		
	}
	
	public StringBuilder generateDefaultConstructor( String class_name ) {
		
		StringBuilder constructor = new StringBuilder();
		
		constructor.append( "\tpublic " ).append( class_name ).append( "() {\n\t\tsuper();\n\t} \n" );
		
		return constructor;
		
	}
	
	private StringBuilder generateMethods( String cdr_class, CDRTypes cdr_type ) {
		
		StringBuilder methods_class = new StringBuilder();
		
		// Generate specific methods for CDR type			
		for( int f = 0; f < cdr_type.fields().size(); f++ ) {
			//System.out.println( cdr_type.fields().get( f ).getName() );
			// Select annotated CDR parent methods
			for( Method method : CDR.class.getDeclaredMethods() ) {
								
				if( method.isAnnotationPresent( cdr_type.fields().get( f ) ) ) {
					//System.out.println( method.getName() );
					//System.out.println( cdr_type.fields().get( f ).getSimpleName() );
					// Define parameter regex expression
					StringBuilder parameter_regex = new StringBuilder();
					
					for( Class<?> parameter : method.getParameterTypes() ) {
						
						parameter_regex.append( ".*" ).append( parameter.getName().replaceAll( "[a-zA-Z]+[.]", "" ) ).append( "[ _<>?a-zA-Z0-9]+," ) ;
						
					}
					
					if( parameter_regex.length() > 0 ) { parameter_regex.setLength( parameter_regex.length() - 1 ); }
					
					// Define method returned type expression
					StringBuilder method_returned_type = new StringBuilder();					
					method_returned_type.append( method.toString().replace( CDR_PACKAGE + ".CDR.", "" ).replaceAll( method.getName() + ".*" , "" ).replaceAll( "[a-zA-Z]+[.]" , "" ) );
					
					// Define method regex expression
					StringBuilder method_regex = new StringBuilder();					
					method_regex.append( method_returned_type ).append( method.getName() ).append( "[ ]*[(]" ).append( parameter_regex ).append( "[)][ a-zA-Z]*" );
					
					// Get method from CDR Class						
					Pattern pattern = Pattern.compile( method_regex.toString() );
					//System.out.println( method_regex.toString() );
					Matcher matcher = pattern.matcher( cdr_class );
					
					// Add method to CDR subclass
					if( matcher.find() ) {
						//System.out.println( "-----------" );
						//System.out.println( matcher.group(0) );
						String method_class_body = matcher.group(0).replace( method_returned_type.toString(), "" );
						
						for( Type param_type : method.getGenericParameterTypes() ) {
							//System.out.println( param_type.toString() );
							String method_class_body_regex = param_type.toString().replaceAll( ".+[.](.+[ <>?]+extends).+[.](.+)", "$1 $2" ).replaceAll( ".+[.]", "" ).replace( "?" , "[?]") + "[ ]+";
							//System.out.println( method_class_body_regex );
							method_class_body = method_class_body.replaceAll( method_class_body_regex, "").replaceAll( "final[ ]+", "" );
							//System.out.println( method_class_body );
						}
						
						// Get method from Calendar Class						
						Pattern pattern_calendar = Pattern.compile( "Calendar" );
						//System.out.println( method_regex.toString() );
						Matcher matcher_calendar = pattern_calendar.matcher( matcher.group(0) );
						
						if( matcher_calendar.find() && import_calendar_package ) {
							//System.out.println( matcher_calendar.toString() );
							import_classes.append( "import java.util.Calendar;\n" );
							import_classes.append( "import " ).append( CDR_PACKAGE ).append( ".CDRDateIncrement;\n" );
							import_calendar_package = false;
						}
						
						// Get method from Enum Class						
						Pattern pattern_enum = Pattern.compile( "ICDREnum" );
						//System.out.println( method_regex.toString() );
						Matcher matcher_enum = pattern_enum.matcher( matcher.group(0) );
						
						if( matcher_enum.find() && import_enum_package ) {
							import_classes.append( "import " ).append( CDR_PACKAGE ).append( ".ICDREnum;\n" );
							import_enum_package = false;
						}
						
						methods_class.append( "\t" )
											.append( ( !method.getReturnType().toString().equals( "void" ) ? "@" + cdr_type.fields().get( f ).getSimpleName() + "( position = " + f + " )\n\t" : "" ) )
											.append(matcher.group(0).replace( "protected", "public" ) )
											.append( " {\n\t\t" )
											.append( ( !method.getReturnType().toString().equals( "void" ) ? "return " : "" ) )
											.append( "super." )
											.append( method_class_body.replaceAll( "[ ]*throws[ a-zA-Z]*", "" ) )
											.append( ";\n\t}\n\n" );			
						//System.out.println( "-----------" );
					}
					
				}					
							
			}
			
		}
		
		return methods_class;
		
	}
	
}

// public void setMsisdn.*[(].*Integer[ a-zA-Z0-9]+,.*Integer[ a-zA-Z0-9]+,.*Integer[ a-zA-Z0-9]+[)]