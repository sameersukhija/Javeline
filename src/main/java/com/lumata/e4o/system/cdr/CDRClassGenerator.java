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
import com.lumata.e4o.system.cdr.fields.Amount;
import com.lumata.e4o.system.cdr.fields.AmountInvoice;
import com.lumata.e4o.system.cdr.fields.AmountPayment;
import com.lumata.e4o.system.cdr.fields.Balance;
import com.lumata.e4o.system.cdr.fields.BundleBalance;
import com.lumata.e4o.system.cdr.fields.BundleName;
import com.lumata.e4o.system.cdr.fields.BundlePurchased;
import com.lumata.e4o.system.cdr.fields.Date;
import com.lumata.e4o.system.cdr.fields.DeactivationDate;
import com.lumata.e4o.system.cdr.fields.Delay;
import com.lumata.e4o.system.cdr.fields.Delete;
import com.lumata.e4o.system.cdr.fields.Download;
import com.lumata.e4o.system.cdr.fields.Duration;
import com.lumata.e4o.system.cdr.fields.Location;
import com.lumata.e4o.system.cdr.fields.Msisdn;
import com.lumata.e4o.system.cdr.fields.NewGender;
import com.lumata.e4o.system.cdr.fields.NewHobbies;
import com.lumata.e4o.system.cdr.fields.NewImei;
import com.lumata.e4o.system.cdr.fields.NewImsi;
import com.lumata.e4o.system.cdr.fields.NewInTag;
import com.lumata.e4o.system.cdr.fields.NewNetwork;
import com.lumata.e4o.system.cdr.fields.NewOptions;
import com.lumata.e4o.system.cdr.fields.NewProfile;
import com.lumata.e4o.system.cdr.fields.NewRatePlan;
import com.lumata.e4o.system.cdr.fields.NewSalary;
import com.lumata.e4o.system.cdr.fields.NewStatus;
import com.lumata.e4o.system.cdr.fields.NewSubProfile;
import com.lumata.e4o.system.cdr.fields.NewSubscriptionDate;
import com.lumata.e4o.system.cdr.fields.NewTongue;
import com.lumata.e4o.system.cdr.fields.OldGender;
import com.lumata.e4o.system.cdr.fields.OldHobbies;
import com.lumata.e4o.system.cdr.fields.OldImei;
import com.lumata.e4o.system.cdr.fields.OldImsi;
import com.lumata.e4o.system.cdr.fields.OldInTag;
import com.lumata.e4o.system.cdr.fields.OldNetwork;
import com.lumata.e4o.system.cdr.fields.OldOptions;
import com.lumata.e4o.system.cdr.fields.OldProfile;
import com.lumata.e4o.system.cdr.fields.OldRatePlan;
import com.lumata.e4o.system.cdr.fields.OldSalary;
import com.lumata.e4o.system.cdr.fields.OldStatus;
import com.lumata.e4o.system.cdr.fields.OldSubProfile;
import com.lumata.e4o.system.cdr.fields.OldSubscriptionDate;
import com.lumata.e4o.system.cdr.fields.OldTongue;
import com.lumata.e4o.system.cdr.fields.Partner;
import com.lumata.e4o.system.cdr.fields.RawData;
import com.lumata.e4o.system.cdr.fields.RechargeAmount;
import com.lumata.e4o.system.cdr.fields.Sms;
import com.lumata.e4o.system.cdr.fields.TenantId;
import com.lumata.e4o.system.cdr.fields.Terminating;
import com.lumata.e4o.system.cdr.fields.Type;
import com.lumata.e4o.system.cdr.fields.Upload;
import com.lumata.e4o.system.cdr.fields.ValidityDate;
import com.lumata.e4o.system.cdr.fields.VoucherCode;
import com.lumata.e4o.system.field.types.FieldMethod;

public class CDRClassGenerator {	
	
	private static final Logger logger = LoggerFactory.getLogger( CDRClassGenerator.class );
	
	StringBuilder import_classes;
	boolean import_calendar_package;
	boolean import_enum_package;
	boolean import_set_package;
	boolean import_json_package;
	
	final String LUMATA_PACKAGE_ = "com.lumata.e4o";
	final String CDR_PACKAGE_ = LUMATA_PACKAGE_ + ".system.cdr";
	final String CDR_TYPES_PACKAGE_ = LUMATA_PACKAGE_ + ".system.cdr.types";
	final String CDR_ANNOTATIONS_PACKAGE_ = LUMATA_PACKAGE_ + ".system.cdr.fields";
	final String FIELD_EXCEPTION_CLASS_ = LUMATA_PACKAGE_ + ".exceptions.FieldException";
	final String FIELD_TYPE_PACKAGE_ = LUMATA_PACKAGE_ + ".system.field.type";
	final String FIELDS_PACKAGE_ = LUMATA_PACKAGE_ + ".system.fields";
	
	/** CDR types definition */
	private enum CDRTypes {
		
		History {	
			public List<Class<? extends Annotation>> fields() {
				List<Class<? extends Annotation>> fields = new ArrayList<Class<? extends Annotation>>();
				fields.add( Msisdn.class );
				return fields;
			}
		},
		RevenueRecharge {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, Amount.class, Balance.class, ValidityDate.class, DeactivationDate.class, Type.class );
			}
		},
		RevenuePayment {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, AmountPayment.class, Balance.class, Type.class, Delay.class );
			}
		},
		RevenueInvoice {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, AmountInvoice.class, Balance.class, Type.class );
			}
		},
		RevenueMultitenant {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, Amount.class, Balance.class, ValidityDate.class, DeactivationDate.class, Type.class, Delay.class, TenantId.class );
			}
		},
		RevenueO2 {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( RawData.class, RechargeAmount.class  );
			}
		},
		Bundle {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, BundleName.class, BundleBalance.class, BundlePurchased.class );
			}
		},
		BundleMultitenant {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, BundleName.class, BundleBalance.class, BundlePurchased.class, TenantId.class  );
			}
		},
		VoucherRedemption {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, VoucherCode.class, Date.class, Location.class, Partner.class );
			}
		},
		LifeCycle {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, NewImei.class, OldImei.class, NewImsi.class, OldImsi.class, NewSubscriptionDate.class, OldSubscriptionDate.class, NewProfile.class, OldProfile.class, NewRatePlan.class, OldRatePlan.class, NewStatus.class, OldStatus.class, NewNetwork.class, OldNetwork.class, NewTongue.class, OldTongue.class, NewInTag.class, OldInTag.class, NewHobbies.class, OldHobbies.class, NewOptions.class, OldOptions.class, NewGender.class, OldGender.class, NewSalary.class, OldSalary.class );
			}
		},
		LifeCycleDelete {	
			public List<Class<? extends Annotation>> fields() {
				return Arrays.asList( Msisdn.class, Date.class, Delete.class );
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
				import_set_package = true;
				import_json_package = true;				
				
				import_classes = new StringBuilder();
				import_classes.append( "import " ).append( CDR_PACKAGE_ ).append( ".CDR;\n" );
				import_classes.append( "import " ).append( CDR_ANNOTATIONS_PACKAGE_ + ".*;\n" );
				import_classes.append( "import " ).append( FIELD_EXCEPTION_CLASS_ ).append( ";\n" );
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
		    	filePath.append( System.getProperty( "user.dir" ) ).append( "/src/main/java/" ).append( CDR_TYPES_PACKAGE_.toString().replaceAll( "[.]" , "/" ) );
								
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
								.append( CDR_TYPES_PACKAGE_ )
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
	public Map<String, String> loadCSVTypeClasses() throws IOFileException {
		
		Map<String, String> csvTypeClasses = new HashMap<String, String>();
				
		StringBuilder dir = new StringBuilder();
		
		dir.append( System.getProperty( "user.dir" ) )
			.append( "/src/main/java/" )
			.append( FIELDS_PACKAGE_.replace( "." , "/" ) )
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
	private StringBuilder generateMethods( CDRTypes cdrType, Map<String, String> fieldTypeClassList ) throws ClassNotFoundException {
		
		/** Load CDR parent class */
		CDR cdrParentClass = new CDR();
		
		/** CDR Type Class Methods Body */
		StringBuilder cdrTypeMethods = new StringBuilder();
		
		/** Load CDR type fields */
		for( int f = 0; f < cdrType.fields().size(); f++ ) {
		
			/** Load CDR ( father class ) fields */
			for( Field cdrField : cdrParentClass.getClass().getDeclaredFields() ) {
				
				if( cdrField.isAnnotationPresent( cdrType.fields().get( f ) ) ) {
					
					/** Define field type */
					final String FIELD_TYPE_ = cdrType.fields().get( f ).getSimpleName();
					
					/** Get CSV Type class */
					final Class<?> fieldTypeClass = Class.forName( FIELDS_PACKAGE_ + "." + cdrField.getType().getSimpleName() );
					
					/** Get CSV Type methods */
					for( final Method fieldTypeMethod : fieldTypeClass.getDeclaredMethods() ) {
							
						/** Get CSV Type annotated methods only*/
						if( fieldTypeMethod.isAnnotationPresent( FieldMethod.class ) ) {
							
							/** Create new cdr type method parameters regex */
							StringBuilder parameterRegex = new StringBuilder();
							
							for( Class<?> parameter : fieldTypeMethod.getParameterTypes() ) {
								
								Boolean isArray = parameter.getName().startsWith( "[" );
								
								parameterRegex.append( ".*" ).append( parameter.getName().replace( ";", "" ).replaceAll( "[\\[\\]0-9a-zA-Z]+[.]", "" ) ).append( ( isArray ? "(<[?]>)*[.\\\\[\\\\]]+" : "" ) ).append( "[ _<>?a-zA-Z0-9]+" ).append( "," ) ;
																
							}
							
							if( parameterRegex.length() > 0 ) { parameterRegex.setLength( parameterRegex.length() - 1 ); }
							
							/** Create new cdr type method returned type expression */
							StringBuilder methodReturnedType = new StringBuilder();					
							methodReturnedType.append( fieldTypeMethod.toString().replace( CDR_PACKAGE_ + ".CDR.", "" ).replaceAll( fieldTypeMethod.getName() + ".*" , "" ).replaceAll( "[0-9a-zA-Z]+[.]" , "" ) );
														
							/** Create new cdr type method regex expression */
							StringBuilder methodRegex = new StringBuilder();					
							methodRegex.append( methodReturnedType ).append( fieldTypeMethod.getName() ).append( "[ ]*[(]" ).append( parameterRegex ).append( "[)][ _0-9a-zA-Z]*" );
							
							/** Get method from Field Type Class */						
							
							final String METHOD_PATTERN_REGEX_ = methodRegex.toString().replace( "\\\\", "\\" );
													
							final Pattern patternFieldTypeMethod = Pattern.compile( METHOD_PATTERN_REGEX_ );
							
							Matcher matcherFieldTypeMethod = patternFieldTypeMethod.matcher( fieldTypeClassList.get( cdrField.getType().getSimpleName() ) );
														
							if( matcherFieldTypeMethod.find() ) {
								
								/** Get csv type managed field name */
								String fieldTypeName = fieldTypeClass.getAnnotations()[0].getClass().getInterfaces()[0].getSimpleName();
																
								/** Build cdr type method class body */
								
								String fieldTypeMethodCall = matcherFieldTypeMethod.group(0).replace( methodReturnedType.toString(), "" ).replaceAll( "[ ]*throws[ a-zA-Z]*", "" );
								
								for( java.lang.reflect.Type paramType : fieldTypeMethod.getGenericParameterTypes() ) {
									
									Boolean isArray = ( paramType.toString().contains( "[Ljava" ) || paramType.toString().contains( "[]" ) );
									
									String methodClassBodyRegex = paramType.toString()
																	.replaceAll( ".+[.](.+[ <>?]+extends).+[.](.+)", "$1 $2" )
																	.replaceAll( ".+[.].+[.](.+)([<]).+[.].+[.](.+)", "$1$2$3" )
																	.replaceAll( ".+[.]", "" )
																	//.replace( "[]", "\\[\\]" )
																	.replace( "[]", "" )
																	.replace( "?" , "[?]")
																	.replace( ";", "" ) +
																	( isArray ? "(...|\\[\\])" : "" ) +
																	"[ ]+";
									
									fieldTypeMethodCall = fieldTypeMethodCall.replaceAll( methodClassBodyRegex, "").replaceAll( "final[ ]+", "" );
								
								}
								
								StringBuilder cdrTypeMethodBody = new StringBuilder();
								
								if( fieldTypeMethod.getReturnType().toString().equals( "void" ) ) {
									
									cdrTypeMethodBody.append( "if( this." )
														.append( cdrField.getName() )
														.append(" != null ) { this.")
														.append( cdrField.getName() )
														.append( "." )
														.append( fieldTypeMethodCall.trim() )
														.append( "; }" );							
								
								} else {
									
									cdrTypeMethodBody.append( "return this." )
														.append( cdrField.getName() )
														.append(".")
														.append( fieldTypeMethodCall.trim() )
														.append( ";" );
									
								}
								
								StringBuilder cdrTypeMethod = new StringBuilder();
								
								cdrTypeMethod.append( "\t" )
												.append( ( !fieldTypeMethod.getReturnType().toString().equals( "void" ) ? "@" + cdrType.fields().get( f ).getSimpleName() + "( position = " + f + " )\n\t" : "" ) )
												.append( matcherFieldTypeMethod.group(0).replace( fieldTypeMethod.getName(), fieldTypeMethod.getName().replace( fieldTypeName.replace( "FieldType" , "" ), FIELD_TYPE_ ) ) )
												.append( " {\n\t\t" )
												.append( cdrTypeMethodBody )
												.append( "\n\t}\n\n" );
																				
								/** Put cdr type method */
								cdrTypeMethods.append( cdrTypeMethod );
																
								/** Check needed packages */
								
								/** Put Calendar packages if it needs */						
								Pattern patternCalendar = Pattern.compile( "Calendar" );
								Matcher matcherCalendar = patternCalendar.matcher( matcherFieldTypeMethod.group(0) );
								if( matcherCalendar.find() && import_calendar_package ) {
									import_classes.append( "import java.util.Calendar;\n" );
									import_classes.append( "import " ).append( FIELDS_PACKAGE_ ).append( ".FieldDateIncrement;\n" );
									import_calendar_package = false;
								}
								
								/** Put Enum packages if it needs */						
								Pattern patternEnum = Pattern.compile( "IFieldEnum" );
								Matcher matcherEnum = patternEnum.matcher( matcherFieldTypeMethod.group(0) );
								if( matcherEnum.find() && import_enum_package ) {
									import_classes.append( "import " ).append( FIELDS_PACKAGE_ ).append( ".IFieldEnum;\n" );
									import_enum_package = false;
								}
								
								/** Put Set packages if it needs */						
								Pattern patternSet = Pattern.compile( "Set" );
								Matcher matcherSet = patternSet.matcher( matcherFieldTypeMethod.group(0) );
								if( matcherSet.find() && import_set_package ) {
									import_classes.append( "import java.util.Set;\n" );
									import_set_package = false;
								}
								
								/** Put JSONObject or JSONArray packages if it needs */
								Pattern patternJson = Pattern.compile( "JSONObject|JSONArray" );
								Matcher matcherJson = patternJson.matcher( matcherFieldTypeMethod.group(0) );								
								if( matcherJson.find() && import_json_package ) {
									import_classes.append( "import org.json.*;\n" );
									import_json_package = false;
								}
															
							}							
														
						}
									
					}
										
				}
				
				
			}
			
		}
		
		return cdrTypeMethods;
		
	}
	
}

// public void setMsisdn.*[(].*Integer[ a-zA-Z0-9]+,.*Integer[ a-zA-Z0-9]+,.*Integer[ a-zA-Z0-9]+[)]