package com.lumata.common.testing.plan;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.lumata.common.testing.exceptions.IOFileException;
import com.lumata.common.testing.selenium.FirefoxProfileResourceHandler;

@Deprecated
public class TestFirefoxProfileResourceHandler {

	/**
	 * This is the final folder archived to launch firefox instance
	 */
	private Path finalProfileFolder_ = null;

	/**
	 * Service flag to print report on standard output
	 */
	private final Boolean PRINT_STDOUT = true;

	/**
	 * Service method to launch a firefox instance on just created profile folder
	 * 
	 * @throws InterruptedException
	 */
	
	@Test(dependsOnMethods = { "prepareProfileFromFolderResource" }, enabled=false)
	public void launchFirefox() throws InterruptedException {

		FirefoxProfile profile = new FirefoxProfile(finalProfileFolder_.toFile());

		Reporter.log("Open firefor with folder -> " + finalProfileFolder_.toString(), PRINT_STDOUT);

		FirefoxDriver driver = new FirefoxDriver(profile);

		driver.get("http://www.google.it");

		Thread.sleep(5_000);

		driver.close();
	}

	@Test(enabled=false)
	public void prepareProfileFromFolderResource() {
		
		String resourcePath = "input/browsers/profiles";
		String resourceName = "firefox.default";		
		
		try {
			finalProfileFolder_ = FirefoxProfileResourceHandler.prepareProfileFromResource(resourcePath, resourceName);
		} catch (IOFileException e) {
			
			Assert.assertTrue(false, e.getMessage());
		}
	}
	
	@Test(enabled=false)
	public void prepareProfileFromCompleteResourceName() throws MalformedURLException {

//		Path jarPath = FileSystems.getDefault().getPath(System.getProperty("user.dir") + File.separator + "src/test/resources", "test.jar");
//		
//		ClassLoader loader = URLClassLoader.newInstance(
//                new URL[] { jarPath.toUri().toURL() },
//                getClass().getClassLoader()
//            );
//
//		Thread.currentThread().setContextClassLoader(loader);
		
		File file = new File( "input/browsers/profiles/firefox.default");
		
		String resourceCompletePath = file.getPath();	
				
		try {
			finalProfileFolder_ = FirefoxProfileResourceHandler.prepareProfileFromResource(resourceCompletePath);
		} catch (IOFileException e) {
			
			Assert.assertTrue(false, e.getMessage());
		}
	}	

	/**
	 * This test looks into class path the profile folder resource. If profile folder is a real
	 * local folder pass this path to next application step. If profile folder is a resource into
	 * a jar, create a new local profile into local file system to next application step.
	 * 
	 * @throws IOException
	 * @throws IOFileException
	 */

	@AfterMethod
	public void tearDown() throws IOException {

//		if ( folderToBeClenup_ != null )
//		{
//			Reporter.log(folderToBeClenup_.toString() + " will be cleaned.",PRINT_STDOUT);
//
//			FileUtils.deleteDirectory(folderToBeClenup_.toFile());
//		}
//		else
//			Reporter.log("No cleanup action will be performed.",PRINT_STDOUT);
	}

}
