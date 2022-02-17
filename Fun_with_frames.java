package Week4_Day2;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Fun_with_frames {

	public static void main(String[] args) throws IOException {
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		driver.get("http://leafground.com/pages/frame.html"); 
		driver.switchTo().frame(0);
		
		//Take the the screenshot of the click me button of first frame
		WebElement click = driver.findElement(By.id("Click"));
		File source_file = click.getScreenshotAs(OutputType.FILE);
		File Dest_file =  new File (".\\src\\main\\resources\\Fun_with_frames_Clickme_button.png");
		FileUtils.copyFile(source_file, Dest_file);
		
		click.click();
		System.out.println(click.getText());
		
		//Nested frames
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame(1).switchTo().frame("frame2");
		WebElement Click1 = driver.findElement(By.id("Click1"));
		Click1.click();
		System.out.println(Click1.getText());
		
		//Find the number of frames
		
		driver.switchTo().defaultContent();
		List<WebElement> Total = driver.findElements(By.tagName("iframe"));
		int Total1 = Total.size();
		System.out.println("Find total number of frames---" + Total1);
		

	}

}
