package com.svhsoftware.automation.framework;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.TargetLocator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseCommon {

	protected  Logger log = null;
	
	protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected static JavascriptExecutor jsExecutor;
    protected static ConfigManager configManager;
    protected static DataManager dataManager;
    
    public BaseCommon() {
    	log = LogManager.getLogger(this.getClass());
    }
    
    /**
     * Initialize Components.
     * @param webDriver
     */
    public void initializeComponents(WebDriver webDriver) {
    	log.info("Initialiizing Driver Components");
    	
        driver = webDriver;
    
        //Create a wait. All test classes use this.
        if(null == wait) {
            wait = new WebDriverWait(driver, 30);	
        }
        
        if(null == jsExecutor) {
        	jsExecutor = (JavascriptExecutor) driver;
        }

	}
    
    /**
     * Initialize Managers.
     */
    public void initializeManagers()
    {
    	log.info("Initialiizing Managers");
        if(null == configManager) {
        	configManager = new ConfigManager();
        }
        
        if(null == dataManager) {
        	dataManager = new DataManager();
        }
    }
    
    public static WebDriver getDriver() {
    	return driver;
    }
    
    /**
     * Creates and return new page
     * @param pageClass
     * @return
     */
    public  <TPage extends BasePage> TPage getPageInstance (Class<TPage> pageClass) {
        try {
            //Initialize the Page with its elements and return it.
        	log.info("Creating page object : " + pageClass.getSimpleName());
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
    public WebElement findElement(By by) {
    	log.info("findElement : " + by.toString());
    	return driver.findElement(by);
    }
    
    /**
     * Find elements using By
     * @param by
     * @return
     */
    public List<WebElement> findElements(By by) {
    	log.info("findElements : " + by.toString());
    	return driver.findElements(by);
    }
    
    /**
     * Dump the elements that matches By.
     * @param by
     */
    public void dumpElements(By by) {
    	List<WebElement> elements = findElements(By.cssSelector("input[value='WORK']"));
    	for(WebElement element: elements) {
    		log.info("x:"+element.getLocation().getX()+",y:"+element.getLocation().getY()+"display:"+element.isDisplayed());
    	}
    }

	public TargetLocator switchTo() {
		log.info("switchTo ");
		return driver.switchTo();
	}

	/**
	 * Click invisible element
	 * @param element
	 */
	public void clickInvisibleElement(WebElement element) {
 
		String js = "arguments[0].click();";
		//String js = "arguments[0].setAttribute('style', 'visibility: visible;');";
		executeJS(js, element);
	}
	
	/**
	 * Scroll to Element.
	 * @param element
	 */
	public void scrollToElement(WebElement element) {
		 
		String js = "arguments[0].scrollIntoView(true);";
		executeJS(js, element);
	}
	
	/**
	 * Find immediate Parent Element.
	 * @param element
	 */
	public WebElement findParentElement(WebElement element) {
		 
		String js = "return arguments[0].parentNode;";
		return (WebElement) jsExecutor.executeScript(js, element);
	}
	
	/**
	 * Find Parent Element that matches the tag
	 * @param element
	 */
	public WebElement findParentElement(WebElement element, String tagName) {
		WebElement parent = null;
		
		try {
			parent = element.findElement(By.xpath(".."));
			while(null != parent) {
				parent = parent.findElement(By.xpath(".."));
				if(parent.getTagName().equalsIgnoreCase(tagName)) {
					break;
				}	
			}
		} catch(NoSuchElementException ex) {
			log.warn("findParentElement - parent with this tag doesn't exist : " + tagName);
		}
		return parent;
	}
	
	public void explicitWait(int secs)
	{
		log.info("explicitWait : " + secs);
		try {
			Thread.sleep(1000 * secs);
		} catch (InterruptedException e) {
			log.fatal("In explicitWait for " + secs + " secs : " + e.getMessage());
		}
	}

	public WebElement findClickableElement(By by) {
    	log.info("findClickableElement : " + by.toString());
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));
		return element;
	}
	
	public WebElement findVisibleElement(By by) {
    	log.info("findVisibleElement : " + by.toString());
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(by));
		return element;
	}

	public WebElement findEnabledElement(By by) {
    	log.info("findEnabledElement : " + by.toString());
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
		return element;
	}
	
	public boolean waitUntilPresent(WebElement element) {
    	log.info("waitUntilPresent : " + element.toString());
		return wait.until(new ExpectedCondition<WebElement>() {

			@Override
			public WebElement apply(WebDriver input) {
				element.getTagName(); // work around for page factory web element for lazy loading
				return element;
			}
			
		}).isEnabled();
	}
	
	public boolean waitUntilVisible(WebElement element) {
    	log.info("waitUntilVisible : " + element.toString());
		try {
			return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
		}
		catch (Exception ex) {
			log.fatal(element.toString() + " is still not visible");		
		}
		
		return false;
	}
	
	public void waitUntilElementIncludesValue(WebElement element, String text) {
    	log.info("waitUntilElementIncludesValue : " + text);
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

	public void executeJS(String jsCommand, WebElement element) {
		try {
			jsExecutor.executeScript(jsCommand, element);
		}
		catch (Exception ex) {
			log.fatal(ex.getMessage());		
		}
	}
}
