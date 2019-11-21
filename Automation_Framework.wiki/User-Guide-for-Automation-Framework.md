# Automation Framework
This is a data driven automation framework.
***

### Required Software Configuration :
1. Java Version >=8
2. TestNG
3. Maven
4. Eclipse IDE for JAVA Developers (2019)
***
### How to Import Project in Eclipse
1. **Clone** the project first. (**Command** : git clone **repoURL** )
2. Open **Eclipse**
3. Navigate to **File > Import**.
4. Select **General Folder** > **Existing Projects into Workspace**.
5. Click on **Next**.
6. Select Project from the folder using **Browse** and click on **Finish**.
***
### Rules for Writing locators/Elements
Locators properties file (**OR.properties**) available in **src/test/resource/properties**
So, Whenever you find unique locator from the webpage write it down in **OR.properties** file.
Now, there are some rules to write locators.
e.g. If you find xpath for **username field**  than your unique locator must be follow this rule. 
so, Unique locator would be like - **username_XPATH**.
Suppose now AT found unique id than unique locator would be like - **username_ID**. So, Whatever locator you found at the end mention that locator also.
Examples of username locator:
1. username_XPATH
2. username_ID
3. username_NAME
4. username_CSS
5. username_LINKTEXT
***
### Rules for Writing Automation Test Case
Create a class in **com.bhavin.testCases** **package** using valid naming convention as I followed in **src/test/java**.
Example of Naming convention of **class** - **TestCase_01_ValidateRegistrationDoneSuccessfully**.
* Whenever create class than extends with base class i.e **TestBase**
* Example - **public class TestCase_01_ValidateRegistrationDoneSuccessfully extends TestBase**
* So, all the methods that are created in TestBase class we can use it easily.
***
### How to Use Methods That are created in TestBase class
1. There are methods like **type(String locator,String value)** , **click(String locator)** , **openURL(String url)**, **isElementEnabled(String locator)**, **getText(String locator)**.
2. **type(String locator,String value)**
- This method is used for the type/sendkeys values in any input filed.
- e.g. **type("register_email_field_CSS", "bhavin3@qa.com");**
3. **click(String locator)**
- This method is used for the click on any button or any link.
- e.g.** click("register_button_XPATH");**
4. **openURL(String url)**
- This method is used for the open URL.
- e.g. openURL("testSiteURL");
5. **isElementEnabled(String locator)**
- This method is used to check weather element is enabled or disabled.
- e.g. isElementEnabled("register_button_XPATH")
6. **getText(String locator)**
- This method is used to get the text from the element.
- e.g. getText("password_required_text_XPATH")
***
### Demo Test Case Code for the Automation
```package com.bhavin.testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.bhavin.base.TestBase;
import com.bhavin.utilities.Messages;
```
```

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
```

***
### How to Run Individual Test Case
Now, If you want to run the test case than **Right click on particular class from the testcases package and click on Run As > TestNG Test**.
***
### How to Create Test Suite
- **Right click** on the **Project > TestNG > Convert to TestNG**
- So it will open the pop up
- Now, **Add the location** you want to add testng.xml (File name can be changed)  file.
- For better, location for store the file in **src/test/resources/runners**
- Click on **Finish**.

***
### Issues Facing While Import the Project in Eclipse
- If it shows **maven project error **than go to project directory/structure and open CMD there and type command : **mvn eclipse:eclipse **
- If any error found regarding the project import than **right click on the project > Maven > Update Project...**
- If any new error found than **click on Project Menu > Click Clean**
***
### How to Add Jar Files in pom.xml
- If you need any jar files than try to search on [Maven Repository](https://mvnrepository.com/)
- And add it in **pom.xml** under the `<dependencies><dependencies>` section