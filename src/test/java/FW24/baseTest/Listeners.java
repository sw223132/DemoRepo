package FW24.baseTest;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import FW24.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override  
	public void onTestStart(ITestResult result) {  
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}  
	  
	@Override  
	public void onTestSuccess(ITestResult result) {   
		extentTest.get().log(Status.PASS, "Test Passed");
	}  
	  
	@Override  
	public void onTestFailure(ITestResult result) {   
		extentTest.get().fail(result.getThrowable());
	
	try {
		driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());	
	}catch (Exception e1) {
		e1.printStackTrace();
	}
	
	String filePath = null;
	try {
		filePath = getScreenshot(result.getMethod().getMethodName(), driver);
	} catch (IOException e) {
		e.printStackTrace();
	}
	extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	//Take Screenshot & attached scr to report
	}  
	  
	@Override  
	public void onTestSkipped(ITestResult result) {   
	//System.out.println("Skip of test cases and its details are : "+result.getName());  
	}  
	  
	@Override  
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {  
	//System.out.println("Failure of test cases and its details are : "+result.getName());  
	}  
	  
	@Override  
	public void onStart(ITestContext context) {    
	}  
	  
	@Override  
	public void onFinish(ITestContext context) {  
		extent.flush();
	}  

}
