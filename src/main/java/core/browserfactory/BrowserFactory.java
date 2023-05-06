package core.browserfactory;

import core.driver.DriverCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static core.browserfactory.BrowserTypes.CHROME;
import static core.browserfactory.BrowserTypes.FIREFOX;

@SuppressWarnings("checkstyle:ConstantName")
public final class  BrowserFactory extends DriverCapabilities {
    public static final String DRIVER_NAME_FIREFOX = "webdriver.gecko.driver";
    public static final String FIREFOX_DRIVER_PATH = "/usr/local/bin/geckodriver";
    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    private static volatile BrowserFactory instance;

    private BrowserFactory() {
    }

    public static BrowserFactory getInstance() {
        if (instance == null) {
            synchronized (BrowserFactory.class) {
                if (instance == null) {
                    instance = new BrowserFactory();
                }
            }
        }
        return instance;
    }

    public static WebDriver getDriver() {
        return driverThread.get();
    }

    public WebDriver createDriverInstance(String browserName) {
        if (browserName != null) {
            if (CHROME.getBrowser().equalsIgnoreCase(browserName)) {
                WebDriverManager.chromedriver().setup();
                driverThread.set(new ChromeDriver(chromeOptions()));
            }
            else if (FIREFOX.getBrowser().equalsIgnoreCase(browserName)) {
                System.setProperty(DRIVER_NAME_FIREFOX, FIREFOX_DRIVER_PATH);
                driverThread.set(new FirefoxDriver());
            }
        }
        return getDriver();
    }

    public static void closeBrowser() {
        if (getDriver() != null)
            getDriver().quit();
    }
}