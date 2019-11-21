package com.bhavin.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.bhavin.base.TestBase;
import com.bhavin.utilities.Messages;

public class TestCase_05_ValidateUserCanNotLoggedInWithoutPassword extends TestBase {

	/**
	 * Validate user can not logged in without password.
	 */
	@Test(description = "TestCase_05_Validate user can not logged in without password.")
	public void validateUserCanNotLoggedInWithoutPassword() {

		openURL("testSiteURL");
		type("username_field_CSS", "test@test.com");
		click("login_btn_XPATH");

		Assert.assertEquals(getText("password_required_text_XPATH"), Messages.password_required_MSG);

	}

	@AfterMethod
	public void completeTestCase() {

		driver.quit();

	}

}
