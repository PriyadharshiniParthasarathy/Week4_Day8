package Week4_Day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		
		//Login into URL
		driver.get("https://www.snapdeal.com/"); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        
        //Go to Mens Fashion
        Actions actions = new Actions(driver);
		WebElement Mens_Fashion = driver.findElement(By.xpath("(//a[@class='menuLinks leftCategoriesProduct ']//span)[2]"));
		actions.moveToElement(Mens_Fashion).perform();
		
		//Go to Sports Shoes
		driver.findElement(By.xpath("(//span[text()='Sports Shoes'])[1]")).click();
		
		//Get the count of the sports shoes
		String text = driver.findElement(By.xpath("//div[text()='Sports Shoes for Men']/following-sibling::div")).getText();
	    System.out.println("SPORTS SHOES:--"+text);

	    //Click Training shoes
	    driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
	    
	    //Sort by Low to High
	    driver.findElement(By.xpath("//div[@class='sort-selected']/following-sibling::i[1]")).click();
		WebElement count = driver.findElement(By.xpath("(//li[@data-index='1'])[2]"));
		count.click();

	    //Check if the items displayed are sorted correctly
	    Thread.sleep(3000);
	    List<WebElement> prodList = driver.findElements(By.xpath("//span[@class ='lfloat product-price']"));
        List <String> shoePrice = new ArrayList<String>();
		for (int i = 0; i < prodList.size(); i++) {
		String price = prodList.get(i).getText();
		shoePrice.add(price);
		int intShoePrice = Integer.parseInt(price.replaceAll("\\D", ""));
		System.out.println(intShoePrice);
		}
		
		//Select the price range (900-1200)
		WebElement From = driver.findElement(By.xpath("(//input[@class='input-filter'])[1]"));
		From.clear();
		From.sendKeys("900");
		From.click();
		WebElement To = driver.findElement(By.xpath("(//input[@class='input-filter'])[2]"));
		To.clear();
		To.sendKeys("1200");
		To.click();
		driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow btn')]")).click();
		
		//Filter with color Navy  
        WebElement Element = driver.findElement(By.xpath("//input[@id='Color_s-Navy']/following-sibling::label"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", Element);
        Thread.sleep(5000);
        Element.click();
        
        //verify the all applied filters 
        String Filter_Color = driver.findElement(By.xpath("(//a[@class='clear-filter-pill  '])[1]")).getText();
        String Filter_Price = driver.findElement(By.xpath("(//a[@class='clear-filter-pill'])[1]")).getText();
        System.out.println(Filter_Color);
        System.out.println(Filter_Price);
        if(Filter_Color.contains("Navy"))
        {
        	System.out.println("Navy filter appiled");
        }
        else
        {
        	System.out.println("Navy filter is not appiled");
        }
        if(Filter_Price.contains("900"))
        {
        	System.out.println("Price filter appiled");
        }
        else
        {
        	System.out.println("Price filter is not appiled");
        }
		
	  //Mouse Hover on first resulting Training shoes
	  WebElement First_image = driver.findElement(By.xpath("//source[@class='product-image']/following-sibling::img"));
	  actions.moveToElement(First_image).perform();
	
	  //click QuickView button
	  driver.findElement(By.xpath("(//div[contains(@class,'center quick-view-bar')])[1]")).click();
	  
	  //Print the cost and the discount percentage
	  String Cost = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
	  System.out.println("Cost of product-"+ Cost);
	  String discount = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
	  System.out.println("Discount of product-"+ discount);
	  
	  //Screenshot
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File source_file = screenshot.getScreenshotAs(OutputType.FILE);
		File Dest_file =  new File (".\\src\\main\\resources\\Snapdeal_Shoes_image.png");
		FileHandler.copy(source_file, Dest_file);
		
	   //Close the current window
		driver.close();
		
		//Close the main window
		driver.quit();	

	}

}


