package test;

import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;


public class LoginTest {

	WebDriver driver;
	JsonParser parserObj;
	JsonElement jsonEleObj;
	LoginPage loginPage;
	DashboardPage dashboardPage;

	
	@SuppressWarnings("deprecation")
	@BeforeMethod(alwaysRun = true)
	public void readJson() {
		
		try {
			FileReader reader = new FileReader("src\\main\\java\\testData\\TF_TestData.json");
			parserObj = new JsonParser();
			jsonEleObj = parserObj.parse(reader);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test(groups = {"smoke", "regression"})
	public void userShouldBeAbleToLogin() {

		driver = BrowserFactory.setup();

//		LoginPage loginPage = new LoginPage(driver);
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("UserName").getAsString());
		loginPage.insertPassword(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("Password").getAsString());
		loginPage.clickSigninButton();

		dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		Assert.assertEquals(dashboardPage.validateDashboardPage(), jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("ValidationTextLogin").getAsString(),
				"Dashboard page is not available!!");
		BrowserFactory.tearDown();
	}

	@Test(groups = {"regression"})
	public void validateAlertMessages() {

		driver = BrowserFactory.setup();
		loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.clickSigninButton();
		Assert.assertEquals(loginPage.getAlertMsg(), jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("AlertUserMsg").getAsString(), "Alert msg dosn't match!!");
		loginPage.accetpAlert();

		loginPage.insertUserName(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("UserName").getAsString());
		loginPage.clickSigninButton();
		Assert.assertEquals(loginPage.getAlertMsg(), jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("AlertPasswordMsg").getAsString(), "Alert msg dosn't match!!");
		loginPage.accetpAlert();
		BrowserFactory.tearDown();
	}
	
	@Test(groups = {"smoke"})
	public void testSmoke() {
		System.out.println("smoke test");
	}

}
