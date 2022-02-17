package Week4_Day2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Actions_Selectable {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		driver.get("https://jqueryui.com/selectable/"); 
        driver.switchTo().frame(0);
        List<WebElement> Item5 = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(Item5.get(0));
        actions.clickAndHold(Item5.get(1));
        actions.clickAndHold(Item5.get(2));
        actions.clickAndHold(Item5.get(3));
        actions.build().perform();
        System.out.println("Selectable - successed ");
	}

}
