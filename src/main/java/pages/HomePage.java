package pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.DriverFactory;
import utility.Utility;


/**
 * @description: Page Object class for Login page
 */

public class HomePage {
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Declare elements on home page by @findBy annotation

    @FindBy(xpath = "//div[@class='banner-actions-container']/button")
    private List<WebElement> policiesButton;

    @FindBy(xpath = "//div[@id='onetrust-policy']//p[@id='onetrust-policy-text']")
    private WebElement Txtpolicy;
    @FindBy(xpath = "//button[normalize-space(.)='Menu']")
    private WebElement menu;
    @FindBy(xpath = "//button[@aria-label='Close'][@data-autoid='nav:siteNavCloseIcon']")
    private WebElement btnClose;
    @FindBy(xpath = "//button[normalize-space(.)='Our Cars']")
    private WebElement btnourCars;
    @FindBy(xpath = "//button[@data-autoid='nav:sideNavLinksMenuDrawer']")
    private List<WebElement> btnMenuItems;
    @FindBy(xpath = "//div[@role='tablist']/button/p")
    private List<WebElement> carsCategory;

    // Verify Title of the Page match with property File
    public void verifyTitle() throws InterruptedException {
        Thread.sleep(5000L);
        Assert.assertTrue(DriverFactory.driver.getTitle().equalsIgnoreCase(Utility.getProperty("title")));
    }

    /**
     * Verify Policy paragraph
     */
    public void verifyPolicyDetails(String policyText) throws InterruptedException {
        Thread.sleep(6000L);
        String actText = Txtpolicy.getText();
        Assert.assertTrue(actText.contains(policyText));
    }

    /**
     * Verify button are present
     * Click on Accept button
     */
    public void AcceptThePolicy() {
        String[] actButtonName = {"ACCEPT", "COOKIE SETTINGS"};
        for (int i = 0; i < actButtonName.length; i++) {
            Assert.assertEquals(actButtonName[i], policiesButton.get(i).getText().trim());
        }
        for (WebElement ele : policiesButton) {
            if (ele.getText().trim().equalsIgnoreCase("ACCEPT")) {
                ele.click();
            }
        }
    }

    //Login assertion
    public void validatePage() {
        if (DriverFactory.driver.findElement(By.xpath("//div[@data-component='ModelIntro']")).isDisplayed()) {
            System.out.println("Volvo Cars Home Page Login Succefully");
        } else {
            System.out.println("Unable to launch Volvo Car page");
        }
    }

    public void click_on_Menu() throws InterruptedException {
        menu.click();
        Thread.sleep(5000L);
    }

    public void click_On_close() throws InterruptedException {
        if (btnClose.isDisplayed()) {
            btnClose.click();
            Thread.sleep(3000L);
        }
    }

    public void validateMenuItems(String menuItems) {
        String[] strMenu = menuItems.split(",");
        for (int i = 0; i < strMenu.length; i++) {
            String actMenu = btnMenuItems.get(i).getText().trim();
            Assert.assertTrue(actMenu.equalsIgnoreCase(strMenu[i]));
        }
    }

    public void click_on_ourCars() throws InterruptedException {
        btnourCars.click();
        Thread.sleep(5000L);
    }

    public void validateCarCategories(String category) {
        String[] stritems = category.split(",");
        for (int i = 0; i < stritems.length; i++) {
            String actCategory = carsCategory.get(i).getText().trim();
            Assert.assertTrue(actCategory.equalsIgnoreCase(stritems[i]));
        }
    }
}
