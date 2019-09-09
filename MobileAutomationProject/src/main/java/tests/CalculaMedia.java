package tests;

import java.net.URL;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.io.FileInputStream;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.remote.MobileCapabilityType;

public class CalculaMedia 
{
	static Properties prop;
	AppiumDriver<MobileElement> driver;
	
	/**********************************************
	Function Name: setup
	Description: Setting up the desired capabilities 
	for identifying the mobile device or emulator	
	**********************************************/
	@BeforeClass
	 public void setup() {
	  try {
		loadProperties();	
        DesiredCapabilities dc= new DesiredCapabilities();
        dc.setCapability("BROWSER_NAME",prop.getProperty("browserName"));
		dc.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("deviceName"));
		dc.setCapability("platform", prop.getProperty("platform"));
		dc.setCapability("appPackage", prop.getProperty("appPackage"));
		dc.setCapability("appActivity", prop.getProperty("appActivity"));
		URL url= new URL(prop.getProperty("url"));
		driver= new AppiumDriver<MobileElement>(url,dc);
	   }
		catch(Exception exp) {
		 System.out.println("The cause of Exception is:"+exp.getCause());
		 System.out.println("Exception Message is:"+exp.getMessage());
		}
}
	/**********************************************
	Function Name: loadProperties
	Description: Reading the specifications of the 
	mobile device or emulator from the config.properties files	
	**********************************************/
	public static void loadProperties() {
	 try {
	   InputStream input= new FileInputStream("config.properties");
	   prop= new Properties();
	   prop.load(input);
	  }
	  catch (IOException ex) {
        ex.printStackTrace();
       }
	}
	/**********************************************
	Function Name: tearDown
	Description: Closing the application
	**********************************************/	
	@AfterClass
	 public void tearDown() {
	   driver.quit();
	  }
}
