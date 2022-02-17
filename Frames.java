package Week4_Day2;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Frames {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		driver.get("https://chercher.tech/practice/frames-example-selenium-webdriver"); 
		driver.switchTo().frame("frame1");
		driver.findElement(By.tagName("input")).sendKeys("Names");
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frame1").switchTo().frame("frame3");
		driver.findElement(By.xpath("//input[@id='a']")).click();
		driver.switchTo().defaultContent();
		driver.switchTo().frame("frame2");
	Select dropdown = new Select(driver.findElement(By.id("animals")));
	dropdown.selectByIndex(3);
	}
}
