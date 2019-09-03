package com.caspar;

import org.testng.TestNG;

public class CasperRunner {

	static TestNG testNg;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		testNg = new TestNG();
		testNg.setTestClasses(new Class[] {landingpageTest.class});
		testNg.run();

	}

}
