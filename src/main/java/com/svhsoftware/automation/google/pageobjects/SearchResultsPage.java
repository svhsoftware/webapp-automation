package com.svhsoftware.automation.google.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.svhsoftware.automation.framework.BasePage;

public class SearchResultsPage extends BasePage {

	
    @FindBy(css="a[href='http://svhsoftware.com/']")
    public WebElement searchResultLink;
    
	public SearchResultsPage(WebDriver driver) {
		super(driver);
	}

    public boolean isPageLoaded() {
    	return waitUntilVisible(searchResultLink);
    }
}
