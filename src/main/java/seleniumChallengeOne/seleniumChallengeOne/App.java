package seleniumChallengeOne.seleniumChallengeOne;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App 
{
    public static void main( String[] args )
    {
    	String searchStr = "General Software";
    	String expectedStr = "General Software Inc";
    	App obj = new App();
        System.setProperty("webdriver.chrome.driver","C:\\Users\\Owner\\Desktop\\HOME\\Home\\PROYECTOS\\"
        		+ "selenium\\WebDrivers\\chromedriver_win32\\chromedriver.exe");
      	WebDriver driver = new ChromeDriver();
      	driver.get("https://google.com.cu");
      	
      	if (obj.exceuteSearch(driver, obj, searchStr, expectedStr)) {
      		System.out.println("Search matched!");
      		obj.doSiteVisit(driver);
      	} else {
      		System.out.println("Redefining query");
      		searchStr = expectedStr;
      		if (obj.exceuteSearch(driver, obj, searchStr, expectedStr)) {
      			System.out.println("Now it does match");
      			obj.doSiteVisit(driver);
      		}
  		}
    }
    
    private void doSiteVisit(WebDriver driver) {
    	driver.findElement(By.xpath("//a[@href='https://www.gsoftinnovation.com/']")).click();
    }
    
    private boolean exceuteSearch(WebDriver driver, App obj, String searchStr, String expectedStr) {
    	WebElement query = driver.findElement(By.xpath("//input[@name='q']"));
    	query.clear();
      	query.sendKeys(searchStr);
      	query.sendKeys(Keys.RETURN);
      	
      	List<WebElement> results = driver.findElements(By.cssSelector("div[class='g']"));
      	if (obj.checkResults(results, expectedStr)) {
      		return true;
      	}

      	return false;
    }
    
    private boolean checkResults(List<WebElement> list, String expectedStr) {
    	for (int i = 0;i < list.size();i++) {
      		if (list.get(i).getText().contains(expectedStr)) {
      			return true;
      		}
    	}
      	return false;
    }
    
      	/*
      	String baseUrl = "http://demo.guru99.com/test/newtours/";
        String expectedTitle = "Welcome: Mercury Tours";
        String actualTitle = "";
        driver.get(baseUrl);
        actualTitle = driver.getTitle();
        if (actualTitle.contentEquals(expectedTitle)) {
        	System.out.println( "Test Passed!" );
        } else {
        	System.out.println( "Test Failed!" );
        }
        //---------------------------------------------------------------
        //Wait VAR
        WebDriverWait myWaitVar = new WebDriverWait(driver, 3);
        baseUrl = "http://www.facebook.com";
        String tagName = "";
        driver.get(baseUrl);
        tagName = driver.findElement(By.id("email")).getTagName();
        System.out.println(tagName);
        //---------------------------------------------------------------
        //SELENIUM SELECTOR TESTING
        baseUrl = "http://the-internet.herokuapp.com/login";
        driver.get(baseUrl);
        List<WebElement> ems = new ArrayList<WebElement>();
        //1. Selector By Tag Name
        ems = driver.findElements(By.tagName("em"));
        //2. Selector By Id
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys(ems.get(0).getText());
        WebElement userPass = driver.findElement(By.id("password"));
        userPass.sendKeys(ems.get(1).getText());
        WebElement form = driver.findElement(By.id("login"));
        form.submit();
        WebElement result = driver.findElement(By.id("flash"));
        System.out.println(result.getText());
        //3.1 By.className	finds elements based on the value of the “class” attribute	findElement(By.className(“someClassName”))
        List<WebElement> rowElements = new ArrayList<WebElement>();
        rowElements = driver.findElements(By.className("row"));
        for (WebElement element: rowElements) {
        	System.out.println("Por clase: " + element.getText());
        }
        //3.2 Compound class (XPATH)
        WebElement element = driver.findElement(By.xpath("//div[@class='large-12 columns']"));
       	System.out.println("Por clase compuesta (xpath): " + element.getTagName() );
       	//4. By.cssSelector	finds elements based on the driver’s underlying CSS Selector engine	findElement(By.cssSelector(“input#email”))
        List<WebElement> rowElementsByCssSelector = new ArrayList<WebElement>();
        rowElementsByCssSelector = driver.findElements(By.cssSelector("div.row"));
        for (WebElement elementByCssSelector: rowElementsByCssSelector) {
        	System.out.println("Por CSS selector: " + elementByCssSelector.getText());
        }
        //5. By.linkText	finds a link element by the exact text it displays	findElement(By.linkText(“REGISTRATION”))
        driver.findElement(By.linkText("Elemental Selenium")).click();
        //Switch to new tab
        App app =  new App();
        app.switchTab(driver);
        //6. By.name	locates elements by the value of the “name” attribute	findElement(By.name(“someName”))
        driver.findElement(By.name("fields[email]")).sendKeys("adg08101@gmail.com");
        //Select Element
        Select selectElement = new Select(driver.findElement(By.name("fields[programming_language]")));
        selectElement.selectByValue("java");
        driver.findElement(By.id("submit")).click();
        app.switchTab(driver);
        //7. By.partialLinkText	locates elements that contain the given link text	findElement(By.partialLinkText(“REG”))
        try {
        	driver.findElement(By.partialLinkText("Back to Previous")).click();
        } catch (Exception e) {
        	System.out.println("Already subscribed!");
        }
        //Switch to Frame Example
        driver.get("http://demo.guru99.com/selenium/deprecated.html");
        myWaitVar.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("classFrame"));
        //driver.switchTo().frame("classFrame");
        driver.findElement(By.linkText("Deprecated")).click();
        //Pop out Alert Example
        //Wait example
        driver.get("http://jsbin.com/usidix/1");
        driver.findElement(By.cssSelector("input[value=\"Go!\"]")).click();
        if (myWaitVar.until(ExpectedConditions.alertIsPresent()) != null) {
        	System.out.println("Alert is now present");
        }
        String alertMessage = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        System.out.println(alertMessage);
        driver.navigate().to("http://the-internet.herokuapp.com/hovers");
        List<WebElement> images = new ArrayList<WebElement>();
        images = driver.findElements(By.tagName("img"));
        Actions hoverAction = new Actions(driver);
        for (int i = 0;i < images.size();i++) {
        	System.out.println("Enabled " + images.get(i).isEnabled());
        	System.out.println("Displayed " + images.get(i).isDisplayed());
        	System.out.println("Selected " + images.get(i).isSelected());
        	hoverAction.moveToElement(images.get(i)).perform();
        	if (images.get(i).getAttribute("alt").contentEquals("User Avatar")) {
        		try {
        			String href = "/users/" + i;
                	myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@href='" + href + "']")));
                	if (myWaitVar.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='" + href + "']"))) != null) {
                		System.out.println("Profile " + i + " visible and now clickable");
                	}
                	System.out.println("Enabled " + images.get(i).isEnabled());
                	System.out.println("Displayed " + images.get(i).isDisplayed());
                	System.out.println("Selected " + images.get(i).isSelected());
                	System.out.println("Profile " + i + " visible");
                } catch (Exception e) {
                	System.out.println(e.getMessage() + " Profile " + i + " not visible");
                }
        	} else {
        		System.out.println(i + " Not what we are looking for, " +  images.get(i).getAttribute("alt"));
        	}
        }
        driver.get("https://demoqa.com/slider/");
        System.out.println("demoqa webpage Displayed");
        
    	//Maximize browser window
        driver.manage().window().maximize();
        //Instantiate Action Class        
        Actions actions = new Actions(driver);
        //Retrieve WebElemnt 'slider' to perform mouse hover 
    	WebElement slider = driver.findElement(By.xpath("//input[@class='range-slider range-slider--primary']"));
    	//Move mouse to x offset 50 i.e. in horizontal direction
    	actions.moveToElement(slider, 50, 50).perform();
    	slider.click();
    	System.out.println("Moved slider in horizontal directions");
        driver.quit();
        System.exit(0);
        */
        /*
         	WebDriver provides these useful navigation commands
			navigate().forward()
			navigate().back()
			navigate().to()
			navigate().refresh()
        */
    //}
    
    public void switchTab(WebDriver driver) {
    	for (String windowsHandle: driver.getWindowHandles()) {
        	driver.switchTo().window(windowsHandle);
        }
    }
}
