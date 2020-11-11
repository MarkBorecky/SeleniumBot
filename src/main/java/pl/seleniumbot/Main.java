/* mbor1 created on 11.11.2020 
inside the package - pl.seleniumbot */
package pl.seleniumbot;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static pl.seleniumbot.Utils.parseToSeconds;
import static pl.seleniumbot.Utils.sleep;


public class Main {

    static final String url = "https://tx3.travian.com/dorf1.php";
    static final String login = "konfucjusz";
    static final String password = "SuomenMestari4";
    static Map<Integer, WebElement> mapOfResourceFields;

    public static void main(String[] args) {
        WebDriver driver = null;
        try {
            driver = new FirefoxDriver();
            login(driver);
            goToResources(driver);
            mapOfResourceFields = getMapOfResourceFields(driver);

//            for (int i=2; i<=18; i++) {
                buildResourceFiledd(driver, 16);
//            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new IllegalArgumentException();
        } finally {
//            driver.close();
//            System.exit(0);
        }
    }

    private static void buildResourceFiledd(WebDriver driver, int id) {
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

    private static Map<Integer, WebElement> getMapOfResourceFields(WebDriver driver) {
        List<WebElement> elements = driver.findElement(By.id("resourceFieldContainer"))
                .findElements(By.className("good"));
        Map<Integer, WebElement> map = new HashMap<>();
        // <div onclick="window.location.href='/build.php?id=6'" class="good level colorLayer gid2 buildingSlot6  level0"><div class="labelLayer"></div></div>
        for (WebElement el : elements) {
            String[] classes = el.getAttribute("class").split(" ");
            // good level colorLayer gid1 buildingSlot1  level2
            Integer id = null;
            for (String cl: classes) {
                // Szukam Stringa który zawiera słowa buildingSlot
                if (cl.contains("buildingSlot")){
                    // wycinam z niego przedrostek, tak żeby została tylko liczba
                    String substring = cl.substring(12);
                    // parsuje Stringa np. "1" na int 1
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
        // podaję url na który przeglądarka ma przejsć
        // można też użyć metody navigate
        driver.get(url);

        // maksymalizuję okno
        // można symulować praktycznie każde działanie użytkownika łącznie ze scrollowaniem, przełączaniem kart i robieniem screenshotów
        driver.manage().window().maximize();

        /* Pobieram tytul strony
        *  w strukturze html czyli w modelu DOM
        *  tytuł zapisany jest w headzie
        *  <html>
        *    <head>
        *      <title>TYTUŁ</title>
        *    </head>
        *    <body>
        *       ...
        *    </body>
        *  </html>
        * */
        String appTitle = driver.getTitle();

        /*
            Pobieram listę elementów ze struktury DOM
            które mają atrybut class z wartością 'text'

            np.
                <input type="text" name="name" value="" class="text">
                <input type="password" maxlength="100" name="password" value="" class="text">
         */
        List<WebElement> inputs = driver.findElements(By.className("text"));
        for (WebElement el : inputs) {
            // Sprawdzam wartość atrybutu name i wpisuję login i hasło w odpowiedni input
            if (el.getAttribute("name") != null)
                switch (el.getAttribute("name")) {
                    case "name" -> el.sendKeys(login);
                    case "password" -> el.sendKeys(password);
                }
        }

        // Wyszukuje checkbox do wyboru słabszej grafiki i symujuję wciśnięcie spacji
        List<WebElement> check = driver.findElements(By.className("check"));
        if (check.size() == 1)
            check.get(0).sendKeys(Keys.SPACE);

        // Wyszukuję przycisk o id s1 (przycisk z napisem login) i symuluję kliknięcie
        driver.findElement(By.id("s1")).click();
    }
}
