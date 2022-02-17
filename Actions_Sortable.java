package Week4_Day2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Actions_Sortable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		driver.get("https://jqueryui.com/sortable/");
	    driver.switchTo().frame(0);
        List<WebElement> Sort = driver.findElements(By.xpath("//ul[@id='sortable']//li"));
        WebElement From = Sort.get(6);
        WebElement To = Sort.get(1);
        Actions actions = new Actions(driver);
        actions.clickAndHold(From).moveToElement(To).release().build().perform();
        System.out.println("Sortable - successed ");
  

	}

}
