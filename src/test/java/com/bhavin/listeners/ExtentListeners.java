package com.bhavin.listeners;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

// TODO: Auto-generated Javadoc
/**
 * The Class ExtentListeners.
 */
public class ExtentListeners implements ITestListener {

	/** The d. */
	static Date d = new Date();

	/** The file name. */
	static String fileName = "Report_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	static String folderName = d.toString().substring(4, 10).replace(" ", "_") + "_" + d.toString().substring(24, 28);

	File file = new File(System.getProperty("user.dir") + "//reports//" + folderName);

	/*
	 * public void checkExistsFolder() {
	 * 
	 * if (!file.exists()) {
	 * 
	 * file.mkdir();
	 * 
	 * } else {
	 * 
	 * System.out.println("Directory already present");
	 * 
	 * }
	 * 
	 * }
	 */

	// static String fileName = "index.html";

	/** The extent. */
	public static ExtentReports extent = ExtentManager
			.createInstance(System.getProperty("user.dir") + "/reports/" + folderName + "/" + fileName);

	public static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();

	// ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);

	/** The test report. */
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestStart(org.testng.ITestResult)
	 */
	public void onTestStart(ITestResult result) {

		// ExtentTest child =
		// parentTest.get().createNode(result.getMethod().getDescription());
		// parentTest.set(child);

		ExtentTest test = extent.createTest(result.getMethod().getDescription());

		// test.assignCategory(result.getMethod().getGroups());
		// child.assignCategory(parentTest.get().getModel().getParent().getName());
		test.assignCategory(result.getMethod().getXmlTest().getSuite().getName());

		// extent.createTest(result.getTestName());

		testReport.set(test);

		// result.getTestClass().getName()+
		// result.getMethod().getMethodName()

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestSuccess(org.testng.ITestResult)
	 */
	public void onTestSuccess(ITestResult result) {

		String methodName = result.getMethod().getDescription();
		String logText = "<b>" + "TEST CASE:- " + methodName.toUpperCase() + "--- PASSED" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
	 */
	public void onTestFailure(ITestResult result) {

		String excepionMessage = Arrays.toString(result.getThrowable().getStackTrace());

		String assertionMessage = result.getThrowable().getMessage();

		testReport.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
						+ "</font>" + "</b >" + "</summary>" + excepionMessage.replaceAll(",", "<br>") + "</details>"
						+ " \n");

		testReport.get()
				.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>"
						+ "Exception Occured For Data:Click to see" + "</font>" + "</b >" + "</summary>"
						+ assertionMessage.replaceAll(",", "<br>") + "</details>" + " \n");

		try {

			ExtentManager.captureScreenshot();
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName).build());
		} catch (IOException e) {

		}

		String failureLogg = "TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onTestSkipped(org.testng.ITestResult)
	 */
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getDescription();
		String logText = "<b>" + "Test Case:- " + methodName + " Skipped" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.testng.ITestListener#onTestFailedButWithinSuccessPercentage(org.testng.
	 * ITestResult)
	 */
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onStart(org.testng.ITestContext)
	 */
	public void onStart(ITestContext context) {

		ExtentTest parent = extent.createTest(context.getSuite().getName());
		// parentTest.set(parent);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.testng.ITestListener#onFinish(org.testng.ITestContext)
	 */
	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}

	}

}
