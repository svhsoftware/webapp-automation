package com.svhsoftware.automation.framework;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners({com.svhsoftware.automation.framework.TestListener.class})
public abstract class BaseTest extends BaseCommon {
 
	@BeforeSuite
    public void suiteLevelSetup()
    {
    	log.info("Initial setup....");
    	setupBrowserWebDrivers();
    	
        //Create a Chrome driver. All test classes use this.
        driver =  new FirefoxDriver(); //new ChromeDriver(); //new SafariDriver();
 
        //Maximize Window
        driver.manage().window().maximize();
        
        initializeComponents(driver);
    }
    
	@AfterSuite
    public void suiteLevelTearDown() {
    	log.info("Releasing resources and closing....");
        driver.quit();
    }
    
    @BeforeClass
    public void classLevelSetup() {
    	//
    }
 
    @AfterClass
    public void classLevelTeardown () {
    	//
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
