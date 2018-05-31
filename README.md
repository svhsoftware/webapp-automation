Web App Automation Testing Framework
=====================================

Automation framework to test Web based application using
  - Selenium
  - Page Object Model (POM) using Page Factory
  - TestNG
  - Maven


Project Setup
=============

## Eclipse
  TestNG Plugin - Install from Eclipse Marketplace.

## Web Drivers:

- Download browser web drivers from https://www.seleniumhq.org/download/ and put all drivers in one directory.
- Safari: No need of downloading driver. But you must enable the 'Allow Remote Automation' option in Safari's Develop menu to control Safari via WebDriver.


Selenium Notes
===============
## FindBy strategies for Selenium

The @FindBy annotation is used in Page Objects in Selenium tests to specify the object location strategy for a WebElement or a list of WebElements. Using the PageFactory, these WebElements are usually initialized when a Page Object is created. In this post, I will demonstrate various ways in which you can use @FindBy annotations to efficiently locate (groups of) WebElements.

@FindBy
The @FindBy annotation is used to locate one or more WebElements using a single criterion. For example, to identify all elements that have the same class attribute, we could use the following identification:

?
@FindBy(how = How.CLASS_NAME, using = "classname")
private List<WebElement> singlecriterion;
If we are sure there is only a single element that is identified by our location strategy, for example when we use the element ID, we can also directly assign the result to a WebElement variable:

?
@FindBy(how = How.ID, using = "elementid")
private WebElement element;
To instantiate the elements, we call the initElements method of the PageFactory class:

?
PageFactory.initElements(driver, this);
@FindBys and @FindAll
In some cases we want (or need) to use more than a single criterion to identify one or more objects, for instance when page elements do not have a unique ID. In this case, there are two possible annotations that can be used:

The @FindBys annotation is used in case elements need to match all of the given criteria
The @FindAll annotation is used in case elements need to match at least one of the given criteria
Let’s take a look at an example that illustrates the difference between the two.

The Parabank homepage contains two textboxes, one for the username and one for the password. Both elements have a name attribute that we are going to use to identify them within a Page Object.

Using @FindBys:

?
@FindBys({
    @FindBy(how = How.NAME, using = "username"),
    @FindBy(how = How.NAME, using = "password")
})
private List<WebElement> bothcriteria;
The bothcriteria list should contain 0 elements, as there is no element that has both a name attribute with the value username and a name attribute with the value password.

Using @FindAll:

?
@FindAll({
    @FindBy(how = How.NAME, using = "username"),
    @FindBy(how = How.NAME, using = "password")
})
private List<WebElement> eithercriterion;
The eithercriterion list should contain 2 elements, as there is one element that has a name attribute with the value username and also one that has a name attribute with the value password.

## Finding Elements with By Methods
Webdriver’s find methods with “By” is listed below.

Method | 	Syntax | 	Description
-------|-----------|----------------
By.id     |	driver.findElement(By.id(<element ID>))	| Locates an element using the ID attribute
By.name   |	driver.findElement(By.name(<element name>)) |	Locates an element using the Name attribute
By.LinkText|	driver.findElement(By.linkText(<linktext>)) |	Locates a link using link text
By.partialLinkText|	driver.findElement(By.partialLinkText(<linktext>)) |	Locates a link using the link’s partial text
By.className	 | driver.findElement(By.className(<element class>)) |	Locates an element using the Class attribute
By.tagName |	driver.findElement(By.tagName(<htmltagname>)) |	Locates an element using the HTML tag
