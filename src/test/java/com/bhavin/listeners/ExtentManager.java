package com.bhavin.listeners;

import java.io.File;
import java.io.IOException;

import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;



//import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.bhavin.base.TestBase;


//import com.relevantcodes.extentreports.ExtentReports;

// TODO: Auto-generated Javadoc
/**
 * The Class ExtentManager.
 */
public class ExtentManager extends TestBase {

	/** The extent. */
	public static ExtentReports extent;

	/**
	 * Creates the instance.
	 *
	 * @param fileName the file name
	 * @return the extent reports
	 */
	public static ExtentReports createInstance(String fileName) {
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);

		htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
		htmlReporter.config().setChartVisibilityOnOpen(false);
		// extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
		htmlReporter.config().setTheme(Theme.DARK);

		htmlReporter.config().setDocumentTitle("Automation Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("Automation Report");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Automation Tester", System.getProperty("user.name"));
		extent.setSystemInfo("Organization", "Org");
		extent.setSystemInfo("Build no", "Build 01");
		/*
		 * //extent.setSystemInfo("Browser Name :" +
		 * DesiredCapabilities.chrome().getBrowserName().toString(), "Browser Version :"
		 * + DesiredCapabilities.chrome().getVersion().toString());
		 */
		// extent.setSystemInfo(, v);

		return extent;
	}

	/** The screenshot path. */
	// Screenshot Capture
	public static String screenshotPath;

	/** The screenshot name. */
	public static String screenshotName;

	public static String folderName;

	/**
	 * Capture screenshot.
	 */
	public static void captureScreenshot() {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		Date d = new Date();
		screenshotName = d.toString().replace(":", "_").replace(" ", "_") + ".jpg";

		folderName = d.toString().substring(4, 10).replace(" ", "_") + "_" + d.toString().substring(24, 28);

		try {
			FileUtils.copyFile(scrFile,
					new File(System.getProperty("user.dir") + "/reports/" + folderName + "/" + screenshotName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
