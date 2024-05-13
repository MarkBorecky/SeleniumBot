package pl.seleniumbot.webdriver;

import lombok.AllArgsConstructor;
import org.openqa.selenium.WebElement;

@AllArgsConstructor
public class MyElement {
    private final WebElement element;

    public String innerHtml() {
        return this.element.getAttribute("innerHTML");
    }
}
