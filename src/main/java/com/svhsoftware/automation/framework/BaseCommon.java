package com.svhsoftware.automation.framework;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseCommon {

	protected static final Logger log = LogManager.getLogger("Automation");
	
	protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static JavascriptExecutor jsExecutor;
    
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
        	log.info("Creating page object : " + pageClass);
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
    

	public TargetLocator switchTo() {
		return driver.switchTo();
	}


	public WebElement findClickableElement(By locator) {
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		return element;
	}
	
	public WebElement findVisibleElement(By locator) {
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;
	}

	public WebElement findHiddenElement(By locator) {
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		return element;
	}
	
	public void waitUntilVisible(WebElement element) {
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
		}
		catch (Exception ex) {
			throw new RuntimeException(element.toString() + " is still visible");		
		}
	}
	
	public void waitUntilElementIncludesValue(WebElement element, String text) {
		try {
			wait.until(ExpectedConditions.textToBePresentInElement(element, text));
		}
		catch (Exception ex) {
			throw new RuntimeException(text + " is not included in " + element.toString());		
		}		
	}	


	public Boolean titleIs(String title) {
		try {
			return wait.until(ExpectedConditions.titleIs(title));
		}
		catch (Exception ex) {
			log.fatal(ex.getMessage());
			return false;
		}
		
	}
	
	public Boolean titleContains(String title) {
		try {
			return wait.until(ExpectedConditions.titleContains(title));
		}
		catch (Exception ex) {
			log.fatal(ex.getMessage());
			return false;
		}
	}

	public Boolean urlIs(String url) {
		try {
			return wait.until(ExpectedConditions.urlToBe(url));
		}
		catch (Exception ex) {
			log.fatal(ex.getMessage());
			return false;
		}
	}

	public Boolean urlContains(String url) {
		try {
			return wait.until(ExpectedConditions.urlContains(url));
		}
		catch (Exception ex) {
			log.fatal(ex.getMessage());
			return false;
		}
	}	
	
	public void executeJS(String jsCommand) {
		try {
			jsExecutor.executeScript(jsCommand);
		}
		catch (Exception ex) {
			log.fatal(ex.getMessage());		
		}
	}

}
