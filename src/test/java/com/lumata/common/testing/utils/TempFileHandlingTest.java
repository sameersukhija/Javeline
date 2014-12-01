package com.lumata.common.testing.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lumata.common.testing.utils.TempFileHandling.TempFileExtension;

public class TempFileHandlingTest {

	@BeforeMethod
	public void beforeTest() {
	
		TempFileHandling.setCreationPath(null);
	}
	
	@AfterMethod
	public void afterTest() {
		
		TempFileHandling.setCreationPath(null);
	}
	
	/**
	 * For X-check - Part One
	 */
	private static File fileSimpleCreation = null;

	/**
	 * For X-check - Part Two
	 */
	private static File fileComplexCreation = null;
		
	@Test
	public void testSimpleCreation() throws IOException {
		
		List<String> dataTest = Arrays.asList("Bello", "Bongo", "Bango");
		
		fileSimpleCreation = TempFileHandling.createTempTestFile(dataTest, "tempBongo", TempFileExtension.CSV);
		
		Assert.assertTrue( fileSimpleCreation.exists(), "Temp file must exists!");
	}
	
	@Test
	public void testComplexCreation() throws IOException {
		
		String creationPath = System.getProperty("user.dir");
		
		TempFileHandling.setCreationPath(creationPath);
		
		List<String> dataTest = Arrays.asList("Bello", "Bongo", "Bango");
		
		fileComplexCreation = TempFileHandling.createTempTestFile(dataTest, "tempBongo", TempFileExtension.HTML);
		
		Assert.assertTrue( fileComplexCreation.exists(), "Temp file must exists!");
	}
	
	@Test(dependsOnMethods = {"testSimpleCreation", "testComplexCreation"})
	public void testCross() {
		
		Path t1 = fileComplexCreation.toPath().getParent();
		Path t2 = fileSimpleCreation.toPath().getParent();
		
		Assert.assertTrue( !t1.equals(t2), "The two files must be in different folders!");
	}
	
	@Test(dependsOnMethods = { "testCross" }, enabled = false)
	public void testCreationAndUpload() throws IOException {
		
		//String creationPath = System.getProperty("user.dir");
		
//		TempFileHandling.setCreationPath("/home/vincenzo/Documents/shared/");
//		TempFileHandling.setUploadingPath("Z:\\" + File.separator);
		
		List<String> dataTest = Arrays.asList("Bello", "Bongo", "Bango");
		
		fileComplexCreation = TempFileHandling.createTempTestFile(dataTest, "tempBongo", TempFileExtension.HTML);
		
		TempFileHandling.uploadFile( null, fileComplexCreation.toPath());
	}	
}
