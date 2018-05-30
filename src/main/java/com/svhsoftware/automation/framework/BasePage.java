package com.svhsoftware.automation.framework;

import org.openqa.selenium.WebDriver;

public abstract class BasePage extends BaseCommon {

    public BasePage(WebDriver driver) {
        setWebDriver(driver);
    }
    
    public abstract boolean isPageOpened();
}
