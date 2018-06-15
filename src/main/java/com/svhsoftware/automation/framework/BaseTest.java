package com.svhsoftware.automation.framework;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

@Listeners({com.svhsoftware.automation.framework.TestListener.class})
public abstract class BaseTest extends BaseCommon {
 
	@BeforeSuite
	@Parameters( {"browser.type"} )
    public void suiteLevelSetup(@Optional("CHROME") String browserType)
    {
    	log.info("Initial setup....");
        
    	initializeManagers();
    	
    	BrowserDriver.setupBrowserWebDrivers();
    	
    	log.info("Running tests with browser : " + browserType);
    	driver = BrowserDriver.getWebDriver(browserType);
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
