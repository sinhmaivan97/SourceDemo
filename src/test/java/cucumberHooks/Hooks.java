package cucumberHooks;

import commons.BrowserNotSupportedException;
import constants.EnvironmentType;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class Hooks {
    private static WebDriver driver;

    public synchronized static WebDriver openAndQuitBrowser() {
        String browser = System.getProperty("BROWSER");

        if (driver == null) {

            try {
                if (browser == null) {
                    browser = System.getenv("BROWSER");
                    // Select the browser to run the test script(chrome, h_chrome, edge, h_edge)
                    browser = "chrome";
                }

                switch (browser) {
                    case "chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions options = new ChromeOptions();
                        options.addArguments("--remote-allow-origins=*");
                        options.addArguments("start-maximized");
                        driver = new ChromeDriver(options);
                        break;

                    case "h_chrome":
                        WebDriverManager.chromedriver().setup();
                        ChromeOptions chromeOptions = new ChromeOptions();
                        chromeOptions.addArguments("--remote-allow-origins=*");
                        chromeOptions.addArguments("--headless");
                        driver = new ChromeDriver(chromeOptions);
                        break;

                    case "edge":
                        EdgeOptions edgeOptions = new EdgeOptions();
                        edgeOptions.addArguments("--remote-allow-origins=*");
                        edgeOptions.addArguments("start-maximized");
                        driver = new EdgeDriver(edgeOptions);
                        break;

                    case "h_edge":
                        EdgeOptions hedgeOptions = new EdgeOptions();
                        hedgeOptions.addArguments("--remote-allow-origins=*");
                        hedgeOptions.addArguments("--headless");
                        driver = new EdgeDriver(hedgeOptions);
                        break;

                    default:
                        throw new BrowserNotSupportedException(browser);
                }
            } finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanup()));
            }
            // Select the environment to run the test script(DEV, TEST, PRODUCTION)
            driver.get(getEnvironmentUrl("DEV"));
            driver.manage().window().maximize();
        }
        return driver;
    }

    private static String getEnvironmentUrl(String serverName) {
        String envUrl = null;
        EnvironmentType environment = EnvironmentType.valueOf(serverName.toUpperCase());
        if (environment == EnvironmentType.DEV) {
            envUrl = "https://cloud.google.com/";
        } else if (environment == EnvironmentType.TEST) {
            envUrl = "https://cloud.google.com/";
        } else if (environment == EnvironmentType.PRODUCTION) {
            envUrl = "https://cloud.google.com/";
        }
        return envUrl;
    }

    static class BrowserCleanup implements Runnable {
        @Override
        public void run() {
            try {
                if (driver != null) {
                    openAndQuitBrowser().quit();
                }
            } catch (UnreachableBrowserException e) {
                System.out.println("Can not close the browser");
            }
        }
    }

    @After
    public void afterStep(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
    }
}
