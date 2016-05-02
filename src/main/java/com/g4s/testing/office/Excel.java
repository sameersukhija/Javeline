package com.g4s.testing.office;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.g4s.common.testing.exceptions.IOFileException;
import com.g4s.common.testing.exceptions.OfficeException;
import com.g4s.common.testing.io.ExcelUtils;
import com.g4s.common.testing.io.IOFileUtils;
import com.g4s.common.testing.validating.Format;

/**
 * @author <a href="mailto:arcangelo.dipasquale@lumatagroup.com">Arcangelo Di Pasquale</a>
 * 
 */
public class Excel {

	private static final  Logger logger = LoggerFactory.getLogger( Excel.class );
	
	private Workbook workbook;
	private List<List<String>> sheet;
	
	public Excel() {}
		
	public Excel( String excel_file, IOFileUtils.IOLoadingType loadingType ) throws IOException, OfficeException {
		
		this( "", excel_file, loadingType );	
			
	}
	
	public Excel( String folder, String excel_file, IOFileUtils.IOLoadingType loadingType ) throws IOException, OfficeException {
		
		try {
			
			switch( loadingType ) {
				
				case FILE: { this.workbook = ExcelUtils.loadAsFile( folder, excel_file + Format.EXCEL_XLS_EXTENSION );break; }
				case RESOURCE: { this.workbook = ExcelUtils.loadAsResource( folder, excel_file + Format.EXCEL_XLS_EXTENSION );break; }
				default: throw new OfficeException( "You cannot load an excel from resources different by FILE or RESOURCE" );
			
			}		
				
			
		} catch( IOFileException e ) {
			
			logger.error( e.getMessage(), e );
			
			throw new OfficeException( e.getMessage(), e );
		}			
			
	}
	
	public List<List<String>> getSheetById( Integer sheetId ) {
		
		if( workbook == null ) { return null; }
		
		this.sheet = ExcelUtils.loadSheet( workbook.getSheetAt( sheetId ) );
		
		return this.sheet;
		
	}
	
	public List<List<String>> getSheetByName( String sheetName ) {
		
		if( workbook == null ) { return null; }
		
		this.sheet = ExcelUtils.loadSheet( workbook.getSheet( sheetName ) );
		
		return this.sheet;
		
	}
	
}
