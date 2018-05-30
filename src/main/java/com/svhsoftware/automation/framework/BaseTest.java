package com.svhsoftware.automation.framework;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseTest extends BaseCommon {
 
    @BeforeClass
    public void classLevelSetup() {
        //Create a Chrome driver. All test classes use this.
        driver = new SafariDriver(); //new ChromeDriver();
 
        //Maximize Window
        driver.manage().window().maximize();
        
        setWebDriver(driver);
    }
 
    @AfterClass
    public void teardown () {
        driver.quit();
    }
}
