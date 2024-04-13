package pl.seleniumbot.webdriver;

import lombok.RequiredArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.seleniumbot.configuration.AccountConfiguration;
import pl.seleniumbot.model.VillageFactory;
import pl.seleniumbot.model.village.Village;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class TravianWebDriverImpl implements TravianWebDriver {
    private final WebDriver driver;
    private final AccountConfiguration configuration;

    @Override
    public void login() {
        driver.get(configuration.getUrl());
        driver.manage().window().maximize();
        Map<String, WebElement> formFields = driver.findElements(By.className("text")).stream()
                .filter(webElement -> List.of("name", "password").contains(webElement.getAttribute("name")))
                .collect(Collectors.toMap(webElement -> webElement.getAttribute("name"), Function.identity()));

        formFields.get("name").sendKeys(configuration.getLogin());
        formFields.get("password").sendKeys(configuration.getPassword());
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public List<Village> scanVillages() {
        driver.get(STR."\{configuration.getUrl()}/dorf1.php");
        String resourceFieldHtml = driver.findElement(By.id("resourceFieldContainer")).getText();
        String stockBarHtml = driver.findElement(By.id("stockBar")).getText();

        driver.get(STR."\{configuration.getUrl()}/dorf2.php");
        String villageCenterHtml = driver.findElement(By.id("villageCenterFactory")).getText();

        var villageFactory = new VillageFactory();

        Village village = villageFactory.withResourceFields(resourceFieldHtml)
                .withStockBar(stockBarHtml)
                .withVillageCenterFactory(villageCenterHtml);


        return List.of(village);
    }
}
