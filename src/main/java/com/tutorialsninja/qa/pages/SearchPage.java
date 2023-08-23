package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	WebDriver driver;
	
	@FindBy(linkText = "HP LP3065")
	private WebElement validHPParoduct;
	
	
	@FindBy(xpath = "//div[@id='content']/h2/following-sibling::p")
	private WebElement NoProductMessage;
	
	public SearchPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean displayStatusOfHPValidProduct()
	{
		boolean displayStaus = validHPParoduct.isDisplayed();
		
		return displayStaus;
	}
	
	public String retrieveNoProductMessageText()
	{
		String noProductMessageText = NoProductMessage.getText(); 
		
		return noProductMessageText;
	}

}
