package com.svhsoftware.automation.pageobjects.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.svhsoftware.automation.framework.BasePage;

public class SearchPage extends BasePage {

    private static String PAGE_URL = "https://www.google.com/";
 
    @FindBy(id="lst-ib")
    public WebElement googleSearchText;
    
    @FindBy(css="input[name='btnK']")
    public WebElement googleSearchBtn;
 
	public SearchPage(WebDriver driver) {
		super(driver);
	}

    public void goToPage () {
        driver.get(PAGE_URL);
    }
    
    public boolean isPageOpened() {
    	return wait.until(ExpectedConditions.elementToBeClickable(googleSearchBtn)).isDisplayed();
    }
    
    public SearchResultsPage searchFor(String text) {
    	googleSearchText.sendKeys(text);
    	googleSearchBtn.submit();
    	return getPageInstance(SearchResultsPage.class);
    }
}
