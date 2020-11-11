/* mbor1 created on 11.11.2020 
inside the package - pl.seleniumbot */
package pl.seleniumbot;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.security.Key;
import java.util.List;


public class Main {

    static final String url = "https://tx3.travian.com/dorf1.php";
    static final String login = "konfucjusz";
    static final String password = "SuomenMestari4";

    public static void main(String[] args) {

        // Tworze encje driver
        WebDriver driver = new FirefoxDriver();

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


//        driver.close();
//        System.exit(0);
    }
}
