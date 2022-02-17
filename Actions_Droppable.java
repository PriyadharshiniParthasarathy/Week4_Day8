package Week4_Day2;



import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Actions_Droppable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		driver.get("https://jqueryui.com/droppable/"); 
        driver.switchTo().frame(0);
        WebElement Drag = driver.findElement(By.xpath("//div[@id='draggable']//p[1]"));
        WebElement Drop = driver.findElement(By.xpath("//div[@id='draggable']/following-sibling::div[1]"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(Drag).moveToElement(Drop).release().build().perform();
        System.out.println("Droppable - successed ");
  

	}

}
