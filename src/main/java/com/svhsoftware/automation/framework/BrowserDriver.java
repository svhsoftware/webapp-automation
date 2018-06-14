package com.svhsoftware.automation.framework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserDriver {

	public enum BrowserType {
		CHROME,
		FIREFOX,
		SAFARI,
		IE,
		EDGE
	}
	
	protected static final Logger log = LogManager.getLogger("BrowserDriver");
	
	public BrowserDriver() {

	}

    /**
     * Checks and download latest webdrivers.
     */
    public static void setupBrowserWebDrivers()
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
    
    public static WebDriver getWebDriver(String browserName)
    {
    	BrowserType browserType = BrowserType.valueOf(browserName);
    	if(browserType == null) {
    		throw new RuntimeException(browserName + " is not a valid entry. Should be one of " + BrowserType.values());
    	}
    	
    	WebDriver webDriver = null;
    	
    	switch (browserType) {
    	
    		case CHROME:
    			webDriver = new ChromeDriver();
    			break;
    			
    		case FIREFOX:
    			webDriver = new FirefoxDriver();
    			break;
    			
    		case SAFARI:
    			webDriver = new SafariDriver();
    			break;
    			
    		case EDGE:
    			webDriver = new EdgeDriver();
    			break;
    		
    		case IE:
    			webDriver = new InternetExplorerDriver();
    			break;
    			
    		default:
    			webDriver = new ChromeDriver();
    			break;
    	}
    	
        //Maximize Window
    	webDriver.manage().window().maximize();
        
    	return webDriver;
    }
}
