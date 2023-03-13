package utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {

    public static RemoteWebDriver driver = null;

    /**
     * Create a driver instance or returns existing driver
     ***/
    public static RemoteWebDriver get_driver_instance() {
        System.out.println("Creating driver");
//        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // open Browser in maximized mode
        options.addArguments("disable-infobars"); // disabling infobars
        options.addArguments("--disable-extensions"); // disabling extensions
        options.addArguments("--disable-gpu"); // applicable to windows os only
        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        options.addArguments("--no-sandbox"); // Bypass OS security model
        try{
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
        }catch (Exception e){
            e.printStackTrace();
        }
        return driver;
    }
}
