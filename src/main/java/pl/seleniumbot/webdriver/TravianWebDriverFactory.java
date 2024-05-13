package pl.seleniumbot.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pl.seleniumbot.configuration.AccountConfiguration;

public class TravianWebDriverFactory {
    public TravianWebDriver createFirefoxDriver(AccountConfiguration configuration) {
        WebDriver webDriver = getFirefoxDriver();
        return new TravianWebDriverImpl(webDriver, configuration);
    }

    private static FirefoxDriver getFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        return new FirefoxDriver();
    }
}
