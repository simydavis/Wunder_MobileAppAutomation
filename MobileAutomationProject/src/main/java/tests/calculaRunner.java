package tests;

import org.testng.TestNG;
public class calculaRunner {
 static TestNG testNg;
	
  public static void main(String[] args) {
	testNg = new TestNG();
	testNg.setTestClasses(new Class[] {Tests.class});
	testNg.run();
   }
}
