package com.svhsoftware.automation.pageobjects.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        return googleSearchText.isDisplayed();
    }
    
    public void search(String text) {
    	writeText(googleSearchText, text);
    	googleSearchBtn.submit();
    }
}
