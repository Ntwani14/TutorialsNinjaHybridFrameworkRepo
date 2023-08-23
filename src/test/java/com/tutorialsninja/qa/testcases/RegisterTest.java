package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base{

	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public RegisterTest()
	{
		super();
	}

	@BeforeMethod
	public void setup()
	{

		driver = initilizeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.selectRegisterOption();
	}

	@Test(priority = 1)
	public void verifyRegisteringAcountWithMandatoryFields()
	{
		
		//RegisterPage registerPage = new RegisterPage(driver);
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.comfirmEnteringPassword(prop.getProperty("validPassword"));
		registerPage.selectPrivancyPolicy();
		accountSuccessPage = registerPage.clickContinueButton();

		//AccountSuccessPage accountSuccessPage = new AccountSuccessPage(driver);
		
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Acount success page is not displayed");

	}

	@Test(priority = 2)
	public void verifyRegisteringAccountByProvidingAllFields()
	{
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.comfirmEnteringPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivancyPolicy();
		accountSuccessPage = registerPage.clickContinueButton();
		
		String actualSuccessHeading = accountSuccessPage.retrieveAccountSuccessHeading();
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Acount success page is not displayed");

	}

	@Test(priority = 3)
	public void verifyRegisteringAcountWithExistingEmailAddress()
	{
		
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.enterEmailAddress(prop.getProperty("validEmail"));
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.comfirmEnteringPassword(prop.getProperty("validPassword"));
		registerPage.selectYesNewsletterOption();
		registerPage.selectPrivancyPolicy();
		registerPage.clickContinueButton();

		String actualWarning = registerPage.retrieveDuplicateEmailAddressWarning();
		Assert.assertTrue(actualWarning.contains(dataProp.getProperty("duplicateEmailWarning")),"Warning message is not displayed");

	}

	@Test(priority = 4)
	public void verifyRegisteringAcountWithoutFillingAnyDetails()
	{
		registerPage.clickContinueButton();

		String actualPrivancyPolicyWarning = registerPage.retrievePrivacyPolicyWarning();
		Assert.assertTrue(actualPrivancyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")),"Privancy Policy Warning message not displayed");

		String actualFirstNameWarning = registerPage.retrieveFirstNameWarning();
		Assert.assertTrue(actualFirstNameWarning.contains(dataProp.getProperty("firstNameWarning")), "First Name Warning is not displayed");

		String actualLastNameWarning = registerPage.retrieveLastNameWarning();
		Assert.assertEquals(actualLastNameWarning,dataProp.getProperty("lastNameWarning"), "Last Name Warning is not displayed");

		String actualEmailWarning = registerPage.retrieveEmailAddressWarning();
		Assert.assertEquals(actualEmailWarning,dataProp.getProperty("emailWarning"), "Email Warning is not displayed");

		String actualTelephoneWarning = registerPage.retrieveTelephoneWarning();
		Assert.assertEquals(actualTelephoneWarning,dataProp.getProperty("telephoneWarning"), "Telephone Warning is not displayed");

		String actualPasswordWarning = registerPage.retrievePasswordWarning();
		Assert.assertEquals(actualPasswordWarning,dataProp.getProperty("passwordWarning"), "Password Warning is not displayed");

	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
