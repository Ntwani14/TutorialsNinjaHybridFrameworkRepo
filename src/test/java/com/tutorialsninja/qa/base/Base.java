package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsninja.qa.utils.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	
	//Global declaration/initializations
	WebDriver driver;
	
	public Properties prop;
	public Properties dataProp;
	String projectPath = System.getProperty("user.dir");
	
	//or write the loadProperties method every time instead of using the constructors (Base and login) 
	
	//Constructor 
	public Base()
	{
		prop = new Properties();
		File propFile = new File(projectPath + "/src/main/java/com/tutorialsninja/qa/config/config.properties");
		
		try {
		FileInputStream fis = new FileInputStream(propFile);
		prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		dataProp = new Properties();
		File dataPropFile = new File(projectPath + "/src/main/java/com/tutorialsninja/qa/testdata//testdata.properties");
		try {
		FileInputStream dataFis = new FileInputStream(dataPropFile);
		dataProp.load(dataFis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver initilizeBrowserAndOpenApplicationURL(String browserName)
	{

		if(browserName.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			

		}else if(browserName.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

		}else if(browserName.equalsIgnoreCase("edge")) {

			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		}else
		{
			System.out.println("Browser not found");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

}
