package pl.seleniumbot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.seleniumbot.configuration.Account;
import pl.seleniumbot.configuration.Configuration;
import pl.seleniumbot.model.VillageFactory;
import pl.seleniumbot.webdriver.WebDriverFactory;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Main {

    static Map<Integer, WebElement> mapOfResourceFields;

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        WebDriver driver = webDriverFactory.createFirefoxDriver();
        login(driver, configuration.getAccount());
        VillageFactory villageFactory = new VillageFactory(driver);
        villageFactory.createVilage();

        driver.close();
    }


    private static void login(WebDriver driver, Account account) {
        driver.get(account.getUrl());
        driver.manage().window().maximize();
        Map<String, WebElement> formFields = driver.findElements(By.className("text")).stream()
                .filter(webElement -> List.of("name", "password").contains(webElement.getAttribute("name")))
                .collect(Collectors.toMap(webElement -> webElement.getAttribute("name"), Function.identity()));

        formFields.get("name").sendKeys(account.getLogin());
        formFields.get("password").sendKeys(account.getPassword());
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
}
