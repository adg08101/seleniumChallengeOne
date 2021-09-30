package seleniumChallengeOne.seleniumChallengeOne;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App 
{
    public static void main( String[] args )
    {
    	try {
	    	String searchStr = "General Software";
	    	String expectedStr = "General Software Inc";
	    	App obj = new App();
	    	//Change Driver location
	        System.setProperty("webdriver.chrome.driver","C:\\Users\\Owner\\Desktop\\HOME\\Home\\PROYECTOS\\selenium\\"
	        		+ "WebDrivers\\chrome_94\\chromedriver.exe");
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
    	} catch (Exception e) {
    		System.out.println("An error occured: " + e.getMessage());
    	}
    }
    
    private void doSiteVisit(WebDriver driver) {
    	driver.findElement(By.xpath("//a[@href='https://www.gsoftinnovation.com/']")).click();
    	driver.manage().window().maximize();
    	int waitSeconds = 10;
    	WebDriverWait myWaitVar = new WebDriverWait(driver, 5);
    	for (int i = 0;i < 3;i++) {
    		if (myWaitVar.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@class='nextButton']")))) != null) {
    			driver.findElement(By.xpath("//a[@class='nextButton']")).click();
    			driver.manage().timeouts().implicitlyWait(waitSeconds , TimeUnit.SECONDS);
    		}
    	}
    	driver.findElement(By.linkText("Services")).click();
    	WebElement link = driver.findElement(By.linkText("Artificial Intelligence"));
    	Actions action = new Actions(driver);
    	action.moveToElement(link).perform();
    	link.click();
    	driver.findElement(By.xpath("//button[@class='cls-btn']")).click();
    	driver.findElement(By.xpath("//input[@name='name']")).sendKeys("Ahmed Davila");
    	driver.findElement(By.xpath("//input[@name='email']")).sendKeys("ahmed.davila@generalsoftwareinc.com");
    	driver.findElement(By.xpath("//input[@name='businessName']")).sendKeys("Personal Home Business");
    	driver.findElement(By.xpath("//textarea[@name='body']")).sendKeys("I'm interest into working with you guys. Automatic Testing.");
    	driver.findElement(By.tagName("form")).submit();
    	driver.quit();
    	System.exit(0);
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
}
