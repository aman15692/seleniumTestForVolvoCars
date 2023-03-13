package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Utility {

    static WebDriverWait wait;
    public static WebDriver driver;
    static Properties prop = new Properties();

    public static void loadPropertyFile() {

        try {
            prop.load(new FileInputStream("./config/configuration.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getProperty(String key) {

        loadPropertyFile();
        return prop.getProperty(key);

    }

    public static void pageRefresh() {
        DriverFactory.get_driver_instance().navigate().refresh();
    }


    public static void webDriverWait(WebElement we) {
        wait.until(ExpectedConditions.presenceOfElementLocated((By) we));
    }

    public static void waitUntilPresent(WebElement element) {
        WebDriverWait newWait = new WebDriverWait(driver, 30);
        newWait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void screenShot(String fileName) {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
