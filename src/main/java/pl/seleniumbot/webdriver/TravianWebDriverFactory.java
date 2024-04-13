package pl.seleniumbot.webdriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import pl.seleniumbot.configuration.AccountConfiguration;

public class TravianWebDriverFactory {
    public TravianWebDriver createFirefoxDriver(AccountConfiguration configuration) {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        FirefoxDriver firefoxDriver = new FirefoxDriver();
        return new TravianWebDriverImpl(firefoxDriver, configuration);
    }
}
