package tests;

import java.io.File;
import java.text.DecimalFormat;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;

public class Tests extends CalculaMedia {
	/**********************************************
	Function Name: calcTest
	Description: Automation test to verify the average
	of three numbers 
	**********************************************/
  @Test(dataProvider="Authentication")
   public void calcTest(String firstNumber, String secondNumber, String thirdNumber, String expAverage) throws Exception {
	 MobileElement el1 = (MobileElement) driver.findElementById("com.exemplo.calculamediafinal:id/txtNota1");
	 el1.clear();
	 el1.sendKeys(firstNumber);
	 MobileElement el2 = (MobileElement) driver.findElementById("com.exemplo.calculamediafinal:id/txtNota2");
	 el2.clear();
	 el2.sendKeys(secondNumber);
	 MobileElement el3 = (MobileElement) driver.findElementById("com.exemplo.calculamediafinal:id/txtNota3");
	 el3.clear();
	 el3.sendKeys(thirdNumber);
	 MobileElement el4 = (MobileElement) driver.findElementById("com.exemplo.calculamediafinal:id/btnCalcular");
	 el4.click();
	 String result= driver.findElementById("com.exemplo.calculamediafinal:id/txtMediaFinal").getText();
	 Double d= Double.parseDouble(result);
	 DecimalFormat df= new DecimalFormat("##.00");
	 String str= df.format(d);
	 expAverage=df.format(Double.parseDouble(expAverage));
		
		
	 if(expAverage.equals(str)) {
		System.out.println("The average is: "+result);	
		}
		else {
		   System.out.println("Average is wrong");
		  }

		}
  
	/**********************************************
	Function Name: Authentication
	Description: Data provider provides the test 
	data for the automation test
	**********************************************/
	@DataProvider
	public Object[][] Authentication() throws Exception {
	  File file= new File("CalculaInfo.xlsx");
	  Object[][] testObjArray = TestUtils.getTableArray(file,"Data");
	  return (testObjArray);
	 }
}
