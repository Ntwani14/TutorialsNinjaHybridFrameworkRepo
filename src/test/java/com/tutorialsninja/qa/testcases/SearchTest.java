package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

//Updated Comment - Added more details

public class SearchTest extends Base{
	
	public WebDriver driver;
	SearchPage searchPage;
	
	public SearchTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup() {
	
		driver = initilizeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct()
	{
		HomePage homePage = new HomePage(driver);
		
		homePage.enterProductTextToBeSearched(dataProp.getProperty("validProduct"));
		searchPage = homePage.clickOnSearchButton();
		
//		SearchPage searchPage = new SearchPage(driver);
		
		boolean link = searchPage.displayStatusOfHPValidProduct();
		Assert.assertTrue(link,"Valid product is not displayed");
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct()
	{
		HomePage homePage = new HomePage(driver);
		homePage.enterProductTextToBeSearched(dataProp.getProperty("invalidProduct"));
		searchPage = homePage.clickOnSearchButton();
		
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		//Failing this intentionally just to see the failed message from listeners
		//Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noProductTextInSearchResults"),"Message is not displayed in search result");
		Assert.assertEquals(actualSearchMessage, "abcd","Message is not displayed in search result");
		
	
	}
	
	@Test(priority = 3, dependsOnMethods = {"verifySearchWithValidProduct", "verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct()
	{
		
		HomePage homePage = new HomePage(driver);
		searchPage = homePage.clickOnSearchButton();
		
		String actualSearchMessage = searchPage.retrieveNoProductMessageText();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noProductTextInSearchResults"),"Message is not displayed in search result");
	
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
