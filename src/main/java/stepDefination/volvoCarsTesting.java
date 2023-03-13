package stepDefination;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import cucumber.api.java.en.Then;
import org.apache.log4j.PropertyConfigurator;

import com.cucumber.listener.Reporter;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.HomePage;
import utility.DriverFactory;
import utility.Utility;


public class volvoCarsTesting {
	
	public RemoteWebDriver driver;
	HomePage launch;

	static Logger logger = Logger.getLogger(volvoCarsTesting.class.getName());


	@Before
	public void setUp() {
		Reporter.assignAuthor("Aman Kumar");
		logger.info("Instantiating chrome driver");
		driver = DriverFactory.get_driver_instance();
		launch = new HomePage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	
	@After
	public void tearDown() {
		String userPath = System.getProperty("user.dir");
		String extentPath = Utility.getProperty("reportConfigPath");
	    Reporter.loadXMLConfig(new File(userPath+extentPath));
	    Reporter.setSystemInfo("User Name", Utility.getProperty("uname"));
	    Reporter.setSystemInfo("Time Zone", System.getProperty("user.timezone"));
	    Reporter.setSystemInfo("Machine", "Windows 11" + "64 Bit");
	    Reporter.setSystemInfo("Selenium", "3.141.59");
	    Reporter.setSystemInfo("Maven", "3.9.0");
	    Reporter.setSystemInfo("Java Version", "1.8.0_202");
	    driver.close();
	}

	
	@Given("^user navigates to the volvocars application$")
	public void launchVolvoCarsApplication() throws Throwable {
		Reporter.addStepLog("Launch to volvo cars home page");
		driver.get(Utility.getProperty("url"));
	}

	@When("^user log-in to the application then verify policy and aceept cookies$")
	public void userLoginAndAcceptCookies() throws Throwable {
		Reporter.addStepLog("Verify Policy Details");
		launch.verifyPolicyDetails(Utility.getProperty("policyText"));
		Reporter.addStepLog("Verify Policy Buttons and Accept The Policy");
		launch.AcceptThePolicy();
		Reporter.addStepLog("Verify Title of the Page");
		launch.verifyTitle();
	}

	@Then("^user is on volvo home page")
	public void userIsOnvolvoHomePage(){
		Reporter.addStepLog("volvo cars launch successfully");
		launch.validatePage();
	}

	@Then("^user click on menu and verify all menu items")
	public void validateMenuItems() throws InterruptedException {
		Reporter.addStepLog("click on menu button");
		launch.click_on_Menu();
		Reporter.addStepLog("validate all menu items");
		launch.validateMenuItems("Buy,Own,About Volvo,Explore,More");
		launch.click_On_close();
	}

	@Then("^user click on our cars and verify all cars category")
	public void validateCarsCategories() throws InterruptedException {
		Reporter.addStepLog("click on Our Cars button");
		launch.click_on_ourCars();
		Reporter.addStepLog("validate all car categories");
		launch.validateCarCategories("Electric,Pure electric,Hybrids,Plug-in hybrids,Mild hybrids,Mild hybrids | Micro hybrids");
	}
}
