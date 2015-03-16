package com.lumata.e4o.system.utils;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONObject;
import org.testng.annotations.Test;

import com.lumata.common.testing.io.IOFileUtils;


public class CheckEnvironmentDiff {

	private final String ENVIRONMENT_1 = "qa-expression-env2-Mobistar-dev2.4.1.1.X.EFOGC-1817_3rd_DB_Management";
	private final String ENVIRONMENT_2 = "e4o-mobistar-env";
	private final File MAIN_FOLDER_ = new File( System.getProperty( "user.dir" ) + "/src/main/resources/input/e4o_environments/mobistar/" );
	private final File leftDir = new File( MAIN_FOLDER_.getAbsolutePath(), ENVIRONMENT_1 );
	private final File rightDir = new File( MAIN_FOLDER_.getAbsolutePath(), ENVIRONMENT_2 );
	
	@Test(enabled=true, priority = 1 )
	public void checkEnvironmentDiff() {
				
		ArrayList<File> exclusions = new ArrayList<File>();
		
		exclusions.add( new File( "/target" ) );
		
		JSONObject environmentDiff = IOFileUtils.foldersDiff( leftDir, rightDir, true, exclusions );
		
		System.out.println( environmentDiff.toString() );
		
	}
	
}
