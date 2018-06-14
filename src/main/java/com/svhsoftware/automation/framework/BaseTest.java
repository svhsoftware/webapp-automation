package com.svhsoftware.automation.framework;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners({com.svhsoftware.automation.framework.TestListener.class})
public abstract class BaseTest extends BaseCommon {
 
	@BeforeSuite
    public void suiteLevelSetup()
    {
    	log.info("Initial setup....");
        
    	initializeManagers();
    	
    	BrowserDriver.setupBrowserWebDrivers();
    	
    	log.info("Running tests with browser : " + configManager.getBrowserType());
    	driver = BrowserDriver.getWebDriver(configManager.getBrowserType());
    	log.info(((RemoteWebDriver)driver).getCapabilities().toString());
        
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
    

}
