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

        try {
            Thread.sleep(1000);
            formFields.get("name").sendKeys(configuration.getLogin());
            System.out.println(configuration.getLogin());
            Thread.sleep(1000);
            formFields.get("password").sendKeys(configuration.getPassword());
            System.out.println(configuration.getPassword());
            Thread.sleep(1000);
            driver.findElement(By.xpath("//button[@type='submit']")).click();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public Map<String, Village> scanVillages() {
        driver.get(STR."\{configuration.getUrl()}/dorf1.php");
        String resourceFieldHtml = findElementById("resourceFieldContainer").innerHtml();
        String stockBarHtml = findElementById("stockBar").innerHtml();

        driver.get(STR."\{configuration.getUrl()}/dorf2.php");
        String villageCenterHtml = driver.findElement(By.id("villageContent")).getText();

        var villageFactory = new VillageFactory();

        Village village = villageFactory.build()
                .withResourceFields(resourceFieldHtml)
                .withStockBar(stockBarHtml)
                .withVillageCenterFactory(villageCenterHtml);

        return Map.of(village.getName(), village);
    }

    private MyElement findElementById(String id) {
        return new MyElement(driver.findElement(By.id(id)));
    }

    @Override
    public void build(String villageName, Construction construction) {
//        Village village = this.villages.get(villageName);

        String url = STR."\{configuration.getUrl()}/build.php?id=\{construction.getConstructionId()}";
        driver.get(url);
        WebElement buildButton = driver.findElement(By.className("textButtonV1"));
        buildButton.click();
    }
}
