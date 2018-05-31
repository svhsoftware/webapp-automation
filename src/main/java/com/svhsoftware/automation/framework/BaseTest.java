package com.svhsoftware.automation.framework;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest extends BaseCommon {
 
    @BeforeClass
    public void classLevelSetup() {
    	initialSetup();
    	log.info("Started running test : " + this.getClass().getName());
    }
 
    @AfterClass
    public void teardown () {
    	cleanup() ;
    	log.info("Completed running test : " + this.getClass().getName());
    }
    
    
    private void initialSetup()
    {
    	log.info("Initial setup....");
    	setupBrowserWebDrivers();
    	
        //Create a Chrome driver. All test classes use this.
        driver =  new FirefoxDriver(); //new ChromeDriver(); //new SafariDriver();
 
        //Maximize Window
        driver.manage().window().maximize();
        
        setWebDriver(driver);
    }
    
    private void cleanup() {
    	log.info("Releasing resources and closing....");
        driver.quit();
    }
    
    /**
     * Checks and download latest webdrivers.
     */
    private void setupBrowserWebDrivers()
    {
    	log.info("Verifing and downloading latest webdrivers.");
    	try {    	
	    	WebDriverManager.chromedriver().setup();
	    	WebDriverManager.firefoxdriver().setup();
	    	WebDriverManager.operadriver().setup();
	    	WebDriverManager.phantomjs().setup();
	    	WebDriverManager.edgedriver().setup();
	    	//WebDriverManager.iedriver().setup();
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
    	
    }
}
