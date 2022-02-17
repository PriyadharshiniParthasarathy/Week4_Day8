package Week4_Day2;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Window_Handling {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("http://leaftaps.com/opentaps/control/login"); 
		driver.findElement(By.id("username")).sendKeys("Demosalesmanager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.partialLinkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Merge Contacts')]")).click();
		
		//Click on Widget of From Contact
		String oldwindow = driver.getWindowHandle();
		WebElement From = driver.findElement(By.xpath("//img[@alt='Lookup']"));
		From.click();
		Set <String> Handles = driver.getWindowHandles();
		for (String newwindow: Handles)
		{
			driver.switchTo().window(newwindow);
		}
		
		//Click on First Resulting Contact
		WebElement From_Resulting = driver.findElement(By.xpath("(//td[@class='x-grid3-col x-grid3-cell x-grid3-td-partyId x-grid3-cell-first ']//div//a)[1]"));
		From_Resulting.click();
		driver.switchTo().window(oldwindow);
		
		// Click on Widget of To Contact
		WebElement To = driver.findElement(By.xpath("(//img[@alt='Lookup'])[2]"));
		To.click();
		Set <String> Handles1 = driver.getWindowHandles();
		for (String newwindow1: Handles1)
		{
			driver.switchTo().window(newwindow1);
		}
		
	    //Click on Second Resulting Contact
		WebElement To_Resulting = driver.findElement(By.xpath("(//td[@class='x-grid3-col x-grid3-cell x-grid3-td-partyId x-grid3-cell-first ']//div//a)[2]"));
		To_Resulting.click();
		driver.switchTo().window(oldwindow);
		
		//Click on Merge button using Xpath Locator
		driver.findElement(By.className("buttonDangerous")).click();
		
		//Accept the Alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		
		//Verify the title of the page
	String actualTitle=driver.getTitle();
		if(actualTitle.contains("View Contact"))
	       {
	    		  System.out.println(actualTitle +" ---------Title is verified" );
	       }
	       else
	    	   
	    	   System.out.println(actualTitle +"-----------Title is not verified" );
	
		
	
	}

}
