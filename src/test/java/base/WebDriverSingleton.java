package base;

import enumerators.AvailableBrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverSingleton {


    public WebDriver getDriver() {
        return driver;
    }

    private WebDriver driver;
    private AvailableBrowserType desiredBrowserType;

    private static WebDriverSingleton instance;

    void initialize() {
        desiredBrowserType = getDesiredBrowserType();
//        initializeSeleniumServer();
//        initializeFirefox();
        initializeChrome();
    }

    public static WebDriverSingleton getInstance() {
        if (instance == null) {
            instance = new WebDriverSingleton();
        }
        return instance;
    }

//    public static WebDriver getWebdriverInstance() {
//        System.out.println("driver: " + driver);
//        if (driver == null) {
//            initialize();
//        }
//        return driver;
//    }

    private void initializeChrome() {
        System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver_91_macos");
        driver = new ChromeDriver();
    }

    private void initializeFirefox() {
        System.setProperty("webdriver.gecko.driver", "src/test/drivers/geckodriver_0.29.1_macos");
        driver = new FirefoxDriver();
    }

    private void initializeSeleniumServer() {
        URL url = null;
        try {
            url = new URL("http://localhost:4444/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        driver = new RemoteWebDriver(url, getDesiredCapabilities());
    }

    private DesiredCapabilities getDesiredCapabilities() {

        DesiredCapabilities desiredCapabilities = null;

        switch (desiredBrowserType) {
            case CHROME:{
                desiredCapabilities = DesiredCapabilities.chrome();
                break;
            }
            case FIREFOX:{
                desiredCapabilities = DesiredCapabilities.firefox();
                break;
            }
        }
        return desiredCapabilities;
    }

    private AvailableBrowserType getDesiredBrowserType() {
        String browserType = "firefox";
        if (System.getProperty("browserType") != null) {
            browserType = System.getProperty("browserType");
        }
        AvailableBrowserType desiredBrowserType;
        try{
            desiredBrowserType = AvailableBrowserType.valueOf(browserType.toUpperCase());
        } catch (IllegalArgumentException i) {
            throw new IllegalArgumentException("Browser " + browserType + " not supported");
        }
        return desiredBrowserType;
    }
}
