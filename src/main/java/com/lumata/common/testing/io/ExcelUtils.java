package com.lumata.common.testing.io;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.lumata.common.testing.exceptions.IOFileException;

public class ExcelUtils {

	
	
	public static Workbook load( String folder, String file ) throws IOFileException, IOException {
		
		Workbook workbook = null;
		
		InputStream excel_file = IOFileUtils.loadResourceAsInputStream( folder, file );
		
		if( excel_file != null ) {
		
			workbook = new HSSFWorkbook( excel_file );			
		
		} else {
			
			throw new IOFileException( "excel file not valid" );
		
		}
		
		return workbook;
		
	}
	
	public static List<List<String>> loadSheet( Sheet sheet ) {
		
		List<List<String>> sheetAsList = new ArrayList<List<String>>();
		
		if( sheet != null ) {
				
			Row first_row = sheet.getRow( 0 );
			
			final int first_column = first_row.getFirstCellNum();
			final int last_column = first_row.getLastCellNum();
			
			for( int i = sheet.getFirstRowNum(); i < sheet.getLastRowNum(); i++ ) {
			
				Row row = sheet.getRow( i );
			
				if( row == null ) continue;
				
				List<String> sheetRow = new ArrayList<String>();
				
				String cell;
				
				for( int j = first_column; j <= last_column; j++ )	{
							
					if( row.getCell( j ) == null ) { cell = "NULL";  }
					else { cell = row.getCell(j).toString(); }
				
					sheetRow.add( cell );
												
				}
				
				sheetAsList.add( sheetRow );
					
			}
		
		}
		
		return sheetAsList;
				
	}
	
}
