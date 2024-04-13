package pl.seleniumbot;

import pl.seleniumbot.configuration.AccountConfiguration;
import pl.seleniumbot.model.village.Village;
import pl.seleniumbot.webdriver.TravianWebDriver;
import pl.seleniumbot.webdriver.TravianWebDriverFactory;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        AccountConfiguration configuration = new AccountConfiguration();
        TravianWebDriverFactory webDriverFactory = new TravianWebDriverFactory();
        TravianWebDriver driver = webDriverFactory.createFirefoxDriver(configuration);

        driver.login();

        List<Village> villages = driver.scanVillages();

        System.out.println(villages);

        driver.close();
    }
}
