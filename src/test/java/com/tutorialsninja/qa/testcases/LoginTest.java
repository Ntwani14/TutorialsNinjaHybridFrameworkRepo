package com.tutorialsninja.qa.testcases;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base{

	public WebDriver driver;
	LoginPage loginPage;

	public LoginTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		//here
		//or write the loadProperties method every time instead of using the constructors (Base and login)
		
		driver = initilizeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		
		HomePage homepage = new HomePage(driver);
		//homepage.clickOnMyAccount();
		loginPage =  homepage.navigateToLoginPage();
	}

	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password)
	{
		AccountPage accountPage = loginPage.login(email, password);
		Assert.assertTrue(accountPage.verifyIfEditYourAccountInformtionOptionIsDispayed(),"Edit your account information option is not displayed");

	}
	
	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData() throws IOException
	{
		Object[][] data = Utilities.getTestDataFromExcel("Login");
//		Object[][] data = {{"ntombi1@gmail.com","Ntombi@1"},{"ntombi2@gmail.com","Ntombi@2"}};
		return data;
		
	}

	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials()
	{
		//LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();

		String ActualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String ExpectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMessage), "Expected warning message is not displayed");

	}

	@Test(priority = 3)
	public void verifyLoginWithInvalidEmailAndValidPassword()
	{

		loginPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clickOnLoginButton();

		String ActualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String ExpectedWarningMwssage = dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMwssage), "Expected warning message is not displayed");

	}

	@Test(priority = 4)
	public void verifyLoginWithValidEmailAndInvalidPassword()
	{

		loginPage.enterEmailAddress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clickOnLoginButton();

		String ActualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String ExpectedWarningMwssage =  dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMwssage), "Expected warning message is not displayed");
	}

	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials()
	{
		loginPage.clickOnLoginButton();

		String ActualWarningMessage = loginPage.retrieveEmailPasswordNotMatchingWarningMessageText();
		String ExpectedWarningMwssage =  dataProp.getProperty("emailPasswordNoMatchWarning");
		Assert.assertTrue(ActualWarningMessage.contains(ExpectedWarningMwssage), "Expected warning message is not displayed");
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
