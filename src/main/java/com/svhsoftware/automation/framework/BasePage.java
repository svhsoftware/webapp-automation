package com.svhsoftware.automation.framework;

import org.openqa.selenium.WebDriver;

public abstract class BasePage extends BaseCommon {

    public BasePage(WebDriver driver) {
    	super();
    }
    
    public abstract boolean isPageLoaded();
}
