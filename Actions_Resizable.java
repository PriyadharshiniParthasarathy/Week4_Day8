package Week4_Day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Actions_Resizable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		driver.get("https://jqueryui.com/resizable/"); 
        driver.switchTo().frame(0);
        WebElement Drag = driver.findElement(By.xpath("//div[@id='resizable']//div[3]"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(Drag).moveByOffset(90, 100).release(Drag).build().perform();
        System.out.println("Resizable - successed ");


	}

}
