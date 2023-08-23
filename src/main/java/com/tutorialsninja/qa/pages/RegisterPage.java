package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	//Objects
	@FindBy(id = "input-firstname")
	private WebElement firstNameField;
	
	@FindBy(name = "lastname")
	WebElement lastNameField;
	
	@FindBy(id = "input-email")
	private WebElement emailAddressField;
	
	@FindBy(name = "telephone")
	private WebElement telephoneField;
	
	@FindBy(id = "input-password")
	private WebElement passwordField;
	
	@FindBy(name = "confirm")
	private WebElement comfirmPasswordField;
	
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement newsletterOption;
	
	@FindBy(name = "agree")
	private WebElement privancyPolicyField;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	
	//warnings
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailAddressWarning; 
	
	@FindBy(xpath = "//div[contains(@class,'alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath = "//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath = "//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath = "//input[@name='email']/following-sibling::div")
	private WebElement emailAddressWarning;
	
	@FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath = "//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;

	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Action methods
	public void enterFirstName(String firstNameText)
	{
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText)
	{
		lastNameField.sendKeys(lastNameText);
	}
	public void enterEmailAddress(String emailAddressText)
	{
		emailAddressField.sendKeys(emailAddressText);
	}
	
	public void enterTelephone(String telephoneText)
	{
		telephoneField.sendKeys(telephoneText);
	}
	
	public void enterPassword(String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
	public void comfirmEnteringPassword(String comfirmPasswordText)
	{
		comfirmPasswordField.sendKeys(comfirmPasswordText);
	}
	
	public void selectPrivancyPolicy()
	{
		privancyPolicyField.click();
	}
	
	public AccountSuccessPage clickContinueButton()
	{
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectYesNewsletterOption()
	{
		newsletterOption.click();
	}
	
	public String retrieveDuplicateEmailAddressWarning()
	{
		String duplicateEmailAddressWarningText = duplicateEmailAddressWarning.getText();
		
		return duplicateEmailAddressWarningText;
	}
	
	public String retrievePrivacyPolicyWarning()
	{
		String privacyPolicWarningText = privacyPolicyWarning.getText();
		
		return privacyPolicWarningText;
	}
	
	public String retrieveFirstNameWarning()
	{
		String firstNameWarningText = firstNameWarning.getText();
		
		return firstNameWarningText;
	}
	
	public String retrieveLastNameWarning()
	{
		String lastNameWarningText = lastNameWarning.getText();
		
		return lastNameWarningText;
	}
	
	public String retrieveEmailAddressWarning()
	{
		String emailAddressWarningText = emailAddressWarning.getText();
		
		return emailAddressWarningText;
	}
	
	public String retrieveTelephoneWarning()
	{
		String telephoneWarningText = telephoneWarning.getText();
		
		return telephoneWarningText;
	}
	
	public String retrievePasswordWarning()
	{
		String passwordWarningText = passwordWarning.getText();
		
		return passwordWarningText;
	}
	
}
