package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener {

//	ExtentReports extentReport;
//	ExtentTest extentTest;
//	String testName;
//	
//	@Override
//	public void onStart(ITestContext context) {
//		extentReport = ExtentReporter.generateExentReport();
//		
//	}
//	 
//	@Override
//	public void onTestStart(ITestResult result) {
//		testName = result.getName();
//		extentReport.createTest(testName);
//		extentTest = extentReport.createTest(testName);
//		extentTest.log(Status.INFO, testName + " started executing");
//		
//	}
//
//	@Override
//	public void onTestSuccess(ITestResult result) {
////		you can delete this test name as it is already in the
////		onTestStart method above
//		
////		String testName = result.getName();
//		extentTest.log(Status.PASS, testName + " got successfully executed");
//		
//	}
//
//	@Override
//	public void onTestFailure(ITestResult result) {
//		
////		testName = result.getName();
//		
//		WebDriver driver = null;
//		
//		try {
//			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
//		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
//			e.printStackTrace();
//		}
//		
//		String destinationScreenshotPath = Utilities.captureScreenshot(driver, testName);
//		
//		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
//		extentTest.log(Status.INFO, result.getThrowable());
//		extentTest.log(Status.FAIL, testName + " got failed");
//
//		
//	}
//
//	@Override
//	public void onTestSkipped(ITestResult result) {
////		String testName = result.getName();
//		extentTest.log(Status.INFO, result.getThrowable());
//		extentTest.log(Status.SKIP,testName + " got skipped");
//		
//	}
//	
//	
////	@Override
////	public void onTestFailedWithTimeout(ITestResult result) {
////		String testName = result.getName();
////		System.out.println(testName + " got failed with timeout");
////		System.out.println(result.getThrowable());
////	}
//
//	@Override
//	public void onFinish(ITestContext context) {
//		
//		extentReport.flush();
//		
//		String pathOfExtentReport = System.getProperty("user.dir") + "/test-output/ExtentReports/extentReport.html";
//		File extentReportFile = new File(pathOfExtentReport);
//		
//		try {
//			Desktop.getDesktop().browse(extentReportFile.toURI());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
	ExtentReports extentReport;
	ExtentTest extentTest;

	@Override
	public void onStart(ITestContext context) {
		
		extentReport = ExtentReporter.generateExentReport();
	
	}

	@Override
	public void onTestStart(ITestResult result) {
		
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+" started executing");
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS,result.getName()+" got successfully executed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		String destinationScreenshotPath = Utilities.captureScreenshot(driver,result.getName());
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,result.getName()+" got failed");
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.SKIP, result.getName()+" got skipped");
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
