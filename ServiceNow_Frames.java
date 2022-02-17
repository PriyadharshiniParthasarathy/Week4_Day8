package Week4_Day2;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow_Frames {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		
		//Load ServiceNow application URL 
		driver.get("https://dev76574.service-now.com"); 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        driver.switchTo().frame(0);
		WebElement Username = driver.findElement(By.name("user_name"));
		Username.sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("gJOBntpDH23i");
		driver.findElement(By.name("not_important")).click();
		
		//Search “incident “ Filter Navigator
		WebElement search = driver.findElement(By.xpath("//input[@id='filter']"));
		search.sendKeys("incident");
		search.click();
		
		//Click “All”
		driver.findElement(By.linkText("All")).click();
		
		//Click New button
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.xpath("//button[text()='New']")).click();
		
		//Select a value for Caller and Enter value for short_description
		driver.findElement(By.xpath("//input[@id='sys_display.incident.caller_id']")).sendKeys("Sam Sorokin");
		driver.findElement(By.id("incident.short_description")).sendKeys("Incident - Short_description");
		
		//Read the incident number and save it a variable
		String elementval = driver.findElement(By.xpath("(//span[text()='Number']/following::input)[2]")).getAttribute("value");
		System.out.println(elementval);
		Thread.sleep(3000);
		
		//Click on Submit button
		driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();
		System.out.println("Submitted");
		
		//Search the same incident number 
		WebElement Number = driver.findElement(By.xpath("//span[@class='sr-only']/following-sibling::input[1]"));
		Number.sendKeys(elementval);
		Number.sendKeys(Keys.ENTER);
		
		//Verify the incident is created successful 
		driver.switchTo().defaultContent();
		driver.switchTo().frame("gsft_main");
		String Number1= driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
		System.out.println(Number1 +" ---------Incident is created successful");  
		
		
		//Take snapshot of the created incident
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source_file = screenshot.getScreenshotAs(OutputType.FILE);
		File Dest_file =  new File (".\\src\\main\\resources\\ServiceNow_IncidentNumber.png");
		FileHandler.copy(source_file, Dest_file);
		
		

	
	}

}
