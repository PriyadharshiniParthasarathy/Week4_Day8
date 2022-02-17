package Week4_Day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriverManager.chromedriver().setup(); 
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize(); 
		
		//Login into URL
		driver.get("https://www.amazon.in/"); 
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
        WebElement Product = driver.findElement(By.xpath("//input[@class='nav-input nav-progressive-attribute']"));
        
        //search as oneplus 9 pro 
        Product.sendKeys("oneplus 9 pro");
        driver.findElement(By.xpath("(//span[text()='Select the department you want to search in']/following::input)[2]")).click();
        
        //Get the price of the first product
        String Price = driver.findElement(By.xpath("//span[@class='a-price-symbol']/following-sibling::span")).getText();
		String replaceAll = Price.replaceAll("\\D", "");
		int MRP = Integer.parseInt(replaceAll);
		System.out.println("MRP: " + MRP); 
		String replaceAll2 = Integer.toString(MRP);
		
		//click on the stars 
		driver.findElement(By.xpath("(//a[@role='button']//i)[2]")).click();
		
		//Print the number of customer ratings for the first displayed product
		String Customer_Rating = driver.findElement(By.xpath("//i[@data-hook='average-stars-rating-anywhere']/following-sibling::span[1]")).getText();
		System.out.println("Customer_Rating: " + Customer_Rating);
		
		//Get the percentage of ratings for the 5 star.
		String Percentage= driver.findElement(By.xpath("(//span[@class='a-size-base']//a)[2]")).getText();
		System.out.println("Percentage of 5th star: " + Percentage);
		
		//Click the first text link of the first image
		driver.findElement(By.xpath("(//a[contains(@class,'a-link-normal s-underline-text')]//span)[2]")).click();
		
		//Take a screen shot of the product displayed
		WebElement Image = driver.findElement(By.xpath("//div[@id='imgTagWrapperId']"));
		File source_file = Image.getScreenshotAs(OutputType.FILE);
		File Dest_file =  new File (".\\src\\main\\resources\\Amazon_Mobile_Image.png");
		FileHandler.copy(source_file, Dest_file);
		
		//Click 'Add to Cart' button
		driver.findElement(By.xpath("//input[@value='Add to Cart']")).click();
		Thread.sleep(10000);
		
	    //Get the cart subtotal 
        String Cart_subtotal = driver.findElement(By.xpath("(//*[@id='attach-accessory-cart-subtotal'])[1]")).getText();
		String replaceAll1 = Cart_subtotal.replaceAll("\\D", "");
		int Cart_subtotal1 = Integer.parseInt(replaceAll1);
		System.out.println("Cart_subtotal1: " + Cart_subtotal1); 
		String replaceAll3 = Integer.toString(Cart_subtotal1);
	    
	    //verify if it is correct
	   if(replaceAll3.contains(replaceAll2))
			   {
	    	System.out.println("Verified ------It is correct");
	    }else {
	    	System.out.println("Verified ------It is not correct");
	    }
	    	driver.quit();
	
}
}
