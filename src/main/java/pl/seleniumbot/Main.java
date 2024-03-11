package pl.seleniumbot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import pl.seleniumbot.model.ResourceFieldFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static pl.seleniumbot.Utils.parseToSeconds;
import static pl.seleniumbot.Utils.sleep;


public class Main {

    static final String url = "https://ts20.x2.europe.travian.com";
    static final String login = "konfucjusz";
    static final String password = "SuomenMestari4";
    static Map<Integer, WebElement> mapOfResourceFields;

    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
            driver = new FirefoxDriver();
            login(driver);
//            goToResources(driver);
            mapOfResourceFields = scanVilage(driver);

//            for (int i=2; i<=18; i++) {
//            buildResourceFiledd(driver, 16);
//            }

        } catch (Exception e) {
//            e.printStackTrace();
        } finally {
//            driver.close();
//            System.exit(0);
        }
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

    private static Map<Integer, WebElement> scanVilage(WebDriver driver) {

        String out = "https://ts20.x2.europe.travian.com/dorf1.php";
        driver.get(out);
        List<WebElement> fields = driver.findElement(By.id("resourceFieldContainer"))
                .findElements(By.className("good"));
//        fields.stream()
//                .map(WebElement::getClass)
//                .map(ResourceFieldFactory::create).toList();


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
                elements.get(0).click();
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    private static void login(WebDriver driver) {
        driver.get(url);
        driver.manage().window().maximize();
        Map<String, WebElement> formFields = driver.findElements(By.className("text")).stream()
                .filter(webElement -> List.of("name", "password").contains(webElement.getAttribute("name")))
                .collect(Collectors.toMap(webElement -> webElement.getAttribute("name"), Function.identity()));

        formFields.get("name").sendKeys(login);
        formFields.get("password").sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }
}
