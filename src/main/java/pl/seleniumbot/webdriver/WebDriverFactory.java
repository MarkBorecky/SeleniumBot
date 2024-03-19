package pl.seleniumbot.webdriver;

import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {
    public FirefoxDriver createFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
        return new FirefoxDriver();
    }
}
