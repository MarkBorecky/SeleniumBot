package pl.seleniumbot.webdriver;

import pl.seleniumbot.model.village.Village;

import java.util.Map;

public interface TravianWebDriver {
    void login();

    void close();

    Map<String, Village> scanVillages();

    void build(String villageName, Construction construction);
}
