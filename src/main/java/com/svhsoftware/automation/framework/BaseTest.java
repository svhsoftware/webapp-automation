package com.svhsoftware.automation.framework;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public abstract class BaseTest extends BaseCommon {
 
    @BeforeClass
    public void classLevelSetup() {
    	
    	setupBrowserWebDrivers();
    	
        //Create a Chrome driver. All test classes use this.
        driver =  new FirefoxDriver(); //new ChromeDriver(); //new SafariDriver();
 
        //Maximize Window
        driver.manage().window().maximize();
        
        setWebDriver(driver);
    }
 
    @AfterClass
    public void teardown () {
        driver.quit();
    }
    
    /**
     * Checks and download latest webdrivers.
     */
    private void setupBrowserWebDrivers()
    {
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
