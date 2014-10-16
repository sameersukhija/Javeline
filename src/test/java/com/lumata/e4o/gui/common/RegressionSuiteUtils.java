package com.lumata.e4o.gui.common;

import java.util.Arrays;
import java.util.List;

import org.testng.Reporter;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.e4o.dao.tenant.DAOSetHobbies;
import com.lumata.e4o.exceptions.FieldException;
import com.lumata.e4o.exceptions.GeneratorException;
import com.lumata.e4o.generators.common.Generator;

public class RegressionSuiteUtils extends RegressionSuiteXmlrpcCore {

	/**
	 * Print to standard output during execution
	 */
	private static final Boolean PRINT2STDOUT__ = Boolean.TRUE;
	
	@Parameters({"baseFolder", "baseFileName", "voucherName", "startIndexVoucher", "numberOfVoucher"})
	@Test
	public void createVoucherImportList(@Optional("./") String baseFolder,
										@Optional("VoucherFile") String baseFileName,
										@Optional("VoucherName") String voucherName,
										@Optional("10") Integer startIndexVoucher,
										@Optional("50") Long numberOfVoucher ) throws GeneratorException {
	
		Reporter.log( "voucher with incremental code", PRINT2STDOUT__ );
		
		Generator.vouchers()
					.voucherIncremental( voucherName, startIndexVoucher, 1)
					.createCSVFile( baseFolder, baseFileName, numberOfVoucher);
	}
	
	/**
	 * 
	 */
	private static final String defaultHobbiesList = "Football;Golfing;Cycling;Baseball;Volley;Dance;Juggling;BodyBuilding;Photography;Shopping;Swimming";
	
	@Test
	@Parameters({"hobbiesList"})
	public void generateHobbies(@Optional(defaultHobbiesList) String hobbiesList) throws IOFileException, FieldException {
		
		Reporter.log( "Add to Hobbies Set the passed values : ", PRINT2STDOUT__ );
		
		String[] temp = hobbiesList.split(";");
		List<String> input = Arrays.asList(temp);
		
		int counter = 0;
		
		for (String string : input)
			Reporter.log( ++counter + " -> " + string, PRINT2STDOUT__ );
		
		DAOSetHobbies.getInstance( mysql ).insertHobbies( input );
	}
}
