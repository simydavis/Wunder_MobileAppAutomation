package com.caspar;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.test.utility.TestUtil;
import java.io.File;
import java.util.List;

public class landingpageTest 
{
 WebDriver driver;
	
  @BeforeMethod
	public void setup() 
    {
	 System.setProperty("webdriver.chrome.driver","chromedriver.exe");
	 driver=new ChromeDriver();
	 //Navigate to Caspar Website
	 String url= "https://beta.caspar-health.com/en/#/user/sign_in";
	 driver.get(url);
     driver.manage().window().maximize();
	 driver.manage().deleteAllCookies();
	 driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
		
  @Test(dataProvider="Authentication")
    public void adminloginTTest(String firstName, String lastName, String day, String month, String year) throws Exception 
	{
	 //Logging in using Admin credentials
	 driver.findElement(By.xpath("//*[@id='mat-input-0']")).sendKeys("IXE0865");	
	 driver.findElement(By.xpath("//*[@id=\'mat-input-1\']")).sendKeys("78350619");
	 driver.findElement(By.xpath("/html/body/app-root/div/ui-view/ng-component/ui-view/app-sign-in/div/div[2]/form/div[3]/div/button/span")).click();
	 
	 //Verifying if admin logged in successfully
	 String at= driver.getTitle();
	 System.out.println(at);
	 String et="caspar";
	  if (at.equalsIgnoreCase(et))
	   {
		System.out.println("Logged in Successfully");
	   }
		else
		 {
		  System.out.println("Log in failed");
		 }
	  
	 //Clicking on the "Add Patient" Button
	 driver.findElement(By.xpath("/html/body/app-root/div/ui-view/ng-component/ui-view/app-therapist-dashboard/app-stats-display/div/div[1]/button")).click();
	 String addpatient= driver.findElement(By.xpath("/html/body/app-root/ui-view/app-top-navigation/nav/h1")).getText();
     //Verifying that "Add New Patient" Screen is displayed"
	  String exp="Add New Patient";
	   if (addpatient.equalsIgnoreCase(exp))
		{
		 System.out.println("Add New Patient screen is displayed");
		}
	     else
		  {
		   System.out.println("Add New Patient failed");
		  }
		    
	   //Entering the patient info from the Excel file to "Add Neww Patient Screen"
		 driver.findElement(By.xpath("//*[@id=\'mat-input-7\']")).sendKeys(firstName);
		 driver.findElement(By.xpath("//*[@id=\'mat-input-8\']")).sendKeys(lastName);
		 
		
	   //Selecting the Day from the drop down list
	     //Day dropdown arrow click
		 driver.findElement(By.xpath("//*[@id=\'mat-select-3\']/div/div[2]/div")).click();
		 Thread.sleep(02);	
         //xpath of day dropdown
		 WebElement day_dd= driver.findElement(By.xpath("/html/body/div[3]"));
		 Thread.sleep(1000);
		 List <WebElement> options= day_dd.findElements(By.className("mat-option-text"));
		 Thread.sleep(02);	
		 
			 
	   //Comparison of day from file and the webelement			
		for(WebElement opt : options) 
		{
		 String str= opt.getText();
		  if(str.equals(day))
		   {
			opt.click();
			break;
		   }
		}
				
		
	  //Selecting the Month from the drop down list
		//Month dropdown arrow click
		driver.findElement(By.xpath("//*[@id=\'mat-select-4\']/div/div[2]")).click();	
		Thread.sleep(02);	
	    //xpath of month dropdown
		WebElement month_mm= driver.findElement(By.xpath("/html/body/div[3]"));
		Thread.sleep(02);
		List <WebElement> options1= month_mm.findElements(By.className("mat-option-text")); 
		Thread.sleep(02);
		
		
		//Comparison of month from file and the webelement	
		 for(WebElement opt1 : options1) 
		  { 
		   String str1=opt1.getText();	
			if(str1.equals(month.trim()))
			 {
			  opt1.click();
			  break;
			 }
		  }
			

	 //Selecting the Year from the drop down list
	   //Year dropdown arrow click			
	   driver.findElement(By.xpath("//*[@id=\'mat-select-5\']/div/div[2]/div")).click();
	   Thread.sleep(02);
	   //xpath of year dropdown
	   WebElement year_yy= driver.findElement(By.xpath("/html/body/div[3]"));
	   Thread.sleep(02);	
	   List <WebElement> options2= year_yy.findElements(By.className("mat-option-text")); 
	   
	   Thread.sleep(02);	
		for(WebElement opt2 : options2)
		 {
		  String str2=opt2.getText();	
		   if(str2.equals(year))			
			{
			 opt2.click();
			 break;
			}
		 }
			
     //Click on Save button
	   driver.findElement(By.xpath("/html/body/app-root/div/ui-view/ng-component/ui-view/app-new-patient/div/div/app-profile-form/form/app-footer/button")).click();
	   // Switching to Alert        
	   driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]"));
	   // Capturing the patient_id and temp_psswrd from the simple alert message.    
	     String patient_id= driver.findElement(By.xpath("//*[@id='mat-dialog-0']/app-new-user-dialog/div[2]/div[3]/div[3]/div[2]")).getText();
	     System.out.println("The patient id is:"+patient_id);
		 String temp_pssword= driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-new-user-dialog/div[2]/div[3]/div[4]/div[2]")).getText();
		 System.out.println("The patient temp pass is:"+temp_pssword);
	   
	   //Writing the patient credentials to Excel file
		 File file= new File("PatientInfo.xlsx");
		 TestUtil.setCellData(file,"Patient_Info", patient_id, temp_pssword);
	
	   //logout admin 
	   driver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-new-user-dialog/div[3]/button[2]/span")).click();
	   driver.findElement(By.xpath("/html/body/app-root/ui-view/app-top-navigation/nav/div[2]/app-user-logo/div/span")).click();
	   //Clicking on Log out
	   driver.findElement(By.xpath("//*[@id=\'cdk-overlay-4\']/div/div/button[2]")).click();
	   Thread.sleep(01);
	   
	   //Caling the Patient Login
	   patientlogin(patient_id, temp_pssword);
	}
			
	public void patientlogin(String patient_id, String temp_pssword ) throws Exception 
	{
	 //Entering the patient credentials
	   driver.findElement(By.name("login")).sendKeys(patient_id);
	   Thread.sleep(02);
	   driver.findElement(By.name("password")).sendKeys(temp_pssword);
	   Thread.sleep(02);
	   driver.findElement(By.xpath("/html/body/app-root/div/ui-view/ng-component/ui-view/app-sign-in/div/div[2]/form/div[3]/div/button/span")).click();
	   Thread.sleep(02);
	   String consent= driver.findElement(By.xpath("/html/body/app-root/div/ui-view/ng-component/ui-view/app-terms-of-service/div/div[1]/div")).getText();
	   String expValue="consent";
	    if (expValue.equalsIgnoreCase(consent))
		 {
		  System.out.println("Patient Logged in Successfully");
		 }
		  else
		   {
			System.out.println("Patient Log in failed");
		   }
	   driver.findElement(By.xpath("/html/body/app-root/div/ui-view/ng-component/ui-view/app-terms-of-service/div/div[1]/button/span")).click();
	}
	
	@AfterMethod
	 public void teardown() 
	  {
	   driver.close();
	  }

	@DataProvider
	  public Object[][] Authentication() throws Exception
	   {
		File file= new File("PatientInfo.xlsx");
        Object[][] testObjArray = TestUtil.getTableArray(file,"Sheet1");
        return (testObjArray);
       }
}

