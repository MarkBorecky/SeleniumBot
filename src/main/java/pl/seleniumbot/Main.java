package pl.seleniumbot;

import pl.seleniumbot.configuration.AccountConfiguration;
import pl.seleniumbot.model.ResourceField;
import pl.seleniumbot.model.village.Village;
import pl.seleniumbot.webdriver.Construction;
import pl.seleniumbot.webdriver.TravianWebDriver;
import pl.seleniumbot.webdriver.TravianWebDriverFactory;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;


public class Main {

    public static void main(String[] args) {
        AccountConfiguration configuration = new AccountConfiguration();
        TravianWebDriverFactory webDriverFactory = new TravianWebDriverFactory();
        TravianWebDriver driver = webDriverFactory.createFirefoxDriver(configuration);

        driver.login();

        Map<String, Village> villages = driver.scanVillages();

        System.out.println(villages);

        List<Construction> constructionList = createBuildingQueueForResourceUpTo(10, villages.get("Wioska"));

        driver.build("WIOSKA", constructionList);

        driver.close();
    }

    private static List<Construction> createBuildingQueueForResourceUpTo(int aimedLevel, Village village) {
        return village.getResourceFields().values().stream()
                .flatMap(Collection::stream)
                .filter(field -> field.getLevel() < aimedLevel)
                .sorted(Comparator.comparingInt(ResourceField::getLevel))
                .flatMap(field -> IntStream.range(field.getLevel(), aimedLevel).mapToObj(startLevel -> Construction.builder()
                        .constructionId(field.getId())
                        .currentLevel(startLevel)
                        .levelAfterBuild(startLevel + 1)
                        .build())
                ).sorted(Comparator.comparingInt(Construction::getCurrentLevel))
                .toList();
    }
}
