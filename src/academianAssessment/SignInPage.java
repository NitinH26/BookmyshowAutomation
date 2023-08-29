package academianAssessment;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SignInPage 
{
	public static void main(String[] args) throws InterruptedException 
	{
        // Set the path of chromedriver.exe
		System.setProperty("chromedriver.chrome.driver", "\\drivers\\chromedriver.exe");
		 
		ChromeOptions options= new ChromeOptions();
		options.addArguments("--remote-allow-origins=*","ignore-certificate-errors");
		options.setBinary("\\drivers\\chrome.exe");
		
		WebDriver driver= new ChromeDriver(options);
		driver.manage().window().maximize();
		
		
		// Open the BookMyShow page
		driver.get("https://in.bookmyshow.com/explore/home");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	
		// Select the city: Bengaluru
		driver.findElement(By.xpath("//span[text()='Bengaluru']")).click();
		Thread.sleep(4000);
		
		// Click on SignIn
		driver.findElement(By.xpath("//div[text()='Sign in']")).click();
		
		// Select Continue with Email
		driver.findElement(By.xpath("//div[text()='Continue with Email']")).click();
		driver.findElement(By.xpath("//input[@id='emailId']")).sendKeys("seleniumauto@yopmail.com");
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Continue']")).click();
		
	    // Open Yopmail.com in new tab
		driver.switchTo().newWindow(WindowType.TAB);
		driver.get("https://yopmail.com");
		
		 // Enter email on yopmail and fetch otp
		driver.findElement(By.xpath("//input[@class='ycptinput']")).sendKeys("seleniumauto@yopmail.com");
		driver.findElement(By.xpath("//button[@class='md']")).click();
		driver.switchTo().frame("ifmail");
	    String otp= driver.findElement(By.xpath("//table[@align='center']//tr[2]/td//tr/td")).getText();
	    System.out.println(otp); // otp
	   
	   // For changing selenium focus
	   Set <String> allIds= driver.getWindowHandles();
	   ArrayList<String> ids= new ArrayList<String> (allIds);
	   String bookmyshowId= ids.get(0);
	   
	   Thread.sleep(3000);
	       // driver.close();
	   driver.switchTo().window(bookmyshowId);
	   
	   //enter the OTP
	   driver.findElement(By.xpath("//div[@class='bwc__sc-m1rlyj-3 bwc__sc-rwpctr-0 eYLSMR']/input")).sendKeys(otp);
	   
       // Validate user is successfully signed in and "Hi, Guest" is displayed
	   String actualResult= driver.findElement(By.xpath("//span[@class='bwc__sc-1nbn7v6-12 cQWvYS']")).getText();	
           // String actualResultdriver.findElement(By.xpath("//p[@class='icon-alert']")).getAttribute("innerHTML");  //( if gettext() not works)
	   String expectedResult= "Hi, Guest";
	   
	   if(actualResult.equals(expectedResult))
	   {
		   System.out.println("You are successfully signed : "+actualResult);
	   }
	   else
	   {
		   System.out.println("you are not logged in :Please login");
	   }
	   
	        //  driver.quit();
	   
	}

}
