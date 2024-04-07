package pl.seleniumbot;

import org.eclipse.collections.api.list.MutableList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.seleniumbot.configuration.Account;
import pl.seleniumbot.configuration.Configuration;
import pl.seleniumbot.model.Building;
import pl.seleniumbot.model.InfrastructureFactory;
import pl.seleniumbot.model.ResourceField;
import pl.seleniumbot.model.ResourceFieldFactory;
import pl.seleniumbot.webdriver.WebDriverFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static pl.seleniumbot.Utils.parseToSeconds;
import static pl.seleniumbot.Utils.sleep;


public class Main {

    static Map<Integer, WebElement> mapOfResourceFields;

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        WebDriverFactory webDriverFactory = new WebDriverFactory();
        WebDriver driver = webDriverFactory.createFirefoxDriver();
        login(driver, configuration.getAccount());
        mapOfResourceFields = scanVillage(driver);

        driver.close();
    }

    private static void buildResourceFiled(WebDriver driver, int id) {
        goToResources(driver);
        build(driver, id);
    }

    private static void build(WebDriver driver, int i) {
        WebElement element = mapOfResourceFields.get(i);
        element.click();
        // tutaj używam metody findElement bez s
        // zwróci ona tylko jeden element a nie listę jak poprzednio
        WebElement section1 = driver.findElement(By.className("section1"));
        String duration = section1.findElement(By.className("duration")).getText();
        int seconds = parseToSeconds(duration);
        System.out.println("Budowa zostanie ukończona za " + duration + "!");

        // można też wyszukać element po tagu np <button>
        section1.findElement(By.tagName("button")).click();
        sleep(seconds);
    }

    private static Map<Integer, WebElement> scanVillage(WebDriver driver) {

        driver.get("https://ts20.x2.europe.travian.com/dorf1.php");
        String resourceFieldContainerText = driver.findElement(By.id("resourceFieldContainer")).getText();
        MutableList<ResourceField> resourceFields = ResourceFieldFactory.create(resourceFieldContainerText);

        driver.get("https://ts20.x2.europe.travian.com/dorf2.php");
        WebElement villageContent = driver.findElement(By.id("villageContent"));
        InfrastructureFactory infrastructureFactory = new InfrastructureFactory();
        MutableList<Building> infrastructure = infrastructureFactory.createFrom(villageContent.getText());

        String in = "https://ts20.x2.europe.travian.com/dorf2.php";
        List<WebElement> elements = driver.findElements(By.id("resourceFieldContainer"));

        Map<Integer, WebElement> map = new HashMap<>();
        for (WebElement el : elements) {
            String[] classes = el.getAttribute("class").split(" ");
            Integer id = null;
            for (String cl : classes) {
                if (cl.contains("buildingSlot")) {
                    String substring = cl.substring(12);
                    id = Integer.valueOf(substring);
                }
            }
            map.put(id, el);
        }
        return map;
    }

    private static void goToResources(WebDriver driver) {
        if (!driver.getCurrentUrl().contains("dorf1.php")) {
            List<WebElement> elements = driver.findElements(By.className("resourceView"));
            if (elements.size() == 1) {
                elements.getFirst().click();
            } else {
                throw new IllegalArgumentException();
            }
        }
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
