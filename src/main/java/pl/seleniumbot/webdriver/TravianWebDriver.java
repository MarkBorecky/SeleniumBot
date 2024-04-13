package pl.seleniumbot.webdriver;

import pl.seleniumbot.model.village.Village;

import java.util.List;

public interface TravianWebDriver {
    void login();

    void close();

    List<Village> scanVillages();
}
