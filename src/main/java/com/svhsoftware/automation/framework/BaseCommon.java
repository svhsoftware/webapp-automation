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
     * Click Method by using JAVA Generics (You can use both By or Webelement)
     * @param elementAttr
     */
    public <T> void click (T elementAttr) {
        if(elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).click();
        } else {
            ((WebElement) elementAttr).click();
        }
    }
 
    /**
     * Write Text by using JAVA Generics (You can use both By or Webelement)
     * @param elementAttr
     * @param text
     */
    public <T> void writeText (T elementAttr, String text) {
        if(elementAttr.getClass().getName().contains("By")) {
            driver.findElement((By) elementAttr).sendKeys(text);
        } else {
            ((WebElement) elementAttr).sendKeys(text);
        }
    }
 
    /**
     * Read Text using  JAVA Generics(You can use both By or Webelement)
     * @param elementAttr
     * @return
     */
    public <T> String readText (T elementAttr) {
        if(elementAttr.getClass().getName().contains("By")) {
            return driver.findElement((By) elementAttr).getText();
        } else {
            return ((WebElement) elementAttr).getText();
        }
    }
 
    /**
     * Close popup if exists
     * @param by
     * @throws InterruptedException
     */
    public void handlePopup (By by) throws InterruptedException {
        List<WebElement> popup = driver.findElements(by);
        if(!popup.isEmpty()){
            popup.get(0).click();
            Thread.sleep(200);
        }
    }
    
}
