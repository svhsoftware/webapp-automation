package com.svhsoftware.automation.framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseCommon {

	protected static WebDriver driver;
    protected static WebDriverWait wait;
    
    public void setWebDriver(WebDriver webDriver) {
        driver = webDriver;
        
        //Create a wait. All test classes use this.
        wait = new WebDriverWait(driver,15);
	}
    
    /**
     * Creates and return new page
     * @param pageClass
     * @return
     */
    public  <TPage extends BasePage> TPage getPageInstance (Class<TPage> pageClass) {
        try {
            //Initialize the Page with its elements and return it.
            return PageFactory.initElements(driver,  pageClass);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Find element using By
     * @param by
     * @return
     */
    public WebElement findElementBy(By by) {
    	return driver.findElement(by);
    }
    
    /**
     * Find elements using By
     * @param by
     * @return
     */
    public List<WebElement> findElementsBy(By by) {
    	return driver.findElements(by);
    }
}
