package pl.seleniumbot;

import pl.seleniumbot.configuration.AccountConfiguration;
import pl.seleniumbot.model.village.Village;
import pl.seleniumbot.webdriver.Construction;
import pl.seleniumbot.webdriver.TravianWebDriver;
import pl.seleniumbot.webdriver.TravianWebDriverFactory;

import java.util.Map;


public class Main {

    public static void main(String[] args) {
        AccountConfiguration configuration = new AccountConfiguration();
        TravianWebDriverFactory webDriverFactory = new TravianWebDriverFactory();
        TravianWebDriver driver = webDriverFactory.createFirefoxDriver(configuration);

        driver.login();

        Map<String, Village> villages = driver.scanVillages();

        System.out.println(villages);

        Construction constructionList = Construction.builder()
                .constructionId(15)
                .build();
        driver.build("WIOSKA", constructionList);

        driver.close();
    }
}
