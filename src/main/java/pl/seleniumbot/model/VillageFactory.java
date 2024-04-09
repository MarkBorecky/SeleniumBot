package pl.seleniumbot.model;

import lombok.RequiredArgsConstructor;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pl.seleniumbot.jsoup.MyDocument;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class VillageFactory {

    private final WebDriver driver;

    private static final Pattern PATTERN = Pattern.compile("good level colorLayer gid(\\d{1,2})+ buildingSlot(\\d{1,2})+  level(\\d{1,2})+");
    private static final String LUMBER_ID = "l1";
    private static final String CLAY_ID = "l2";
    private static final String IRON_ID = "l3";
    private static final String CROP_ID = "l4";
    private static final String FREE_CROP_PRODUCTION_ID = "stockBarFreeCrop";


    public Village createVilage() {

        driver.get("https://ts20.x2.europe.travian.com/dorf1.php");
        String resourceFieldContainerText = driver.findElement(By.id("resourceFieldContainer")).getText();
        MutableList<ResourceField> resourceFields = this.create(resourceFieldContainerText);


        //resourceStores
        WebElement stockBarElement = driver.findElement(By.id("stockBar"));
        VillageStock stock = this.createStock(stockBarElement.getText());

        driver.get("https://ts20.x2.europe.travian.com/dorf2.php");
        WebElement villageContent = driver.findElement(By.id("villageContent"));
        MutableList<Building> infrastructure = this.createFrom(villageContent.getText());

        //unitMovement

        //building



        return null;
    }

    public MutableList<Building> createFrom(String text) {
        Document document = Jsoup.parse(text);
        Elements els = document.getElementsByClass("buildingSlot");
        return Lists.mutable.fromStream(els.stream())
                .collect(el -> Building.builder()
                        .id(Integer.parseInt(el.attr("data-aid")))
                        .type(BuildingType.getById(Integer.parseInt(el.attr("data-gid"))))
                        .level(getLevel(el))
                        .build());
    }

    public MutableList<ResourceField> create(String resourceFieldContainerText) {
        Matcher matcher = PATTERN.matcher(resourceFieldContainerText);
        List<ResourceField> fields = matcher.results().map(m -> ResourceField.builder()
                        .type(ResourceType.fromId(Integer.parseInt(m.group(1))))
                        .id(Integer.parseInt(m.group(2)))
                        .level(Integer.parseInt(m.group(3)))
                        .build())
                .toList();
        return Lists.adapt(fields);
    }

    private int getLevel(Element el) {
        Element a = el.getElementsByTag("a").first();
        if (a == null || "emptyBuildingSlot".equals(a.attr("class"))) {
            return 0;
        }
        return Integer.parseInt(a.attr("data-level"));
    }

    public VillageStock createStock(String stockBarElementHtml) {
        MyDocument document = MyDocument.createFrom(stockBarElementHtml);
        //warehouse
        int warehouseCapacity = document.fetchFirstByClass("warehouse", "capacity", "value")
                .getHtmlValueAsNumber();

        //granary
        int granaryCaption = document.fetchFirstByClass("granary", "capacity", "value")
                .getHtmlValueAsNumber();

        int crop = document.findById(CROP_ID)
                .getHtmlValueAsNumber();

        int freeCrop = document.findById(FREE_CROP_PRODUCTION_ID)
                .getHtmlValueAsNumber();

        int lumber = document.findById(LUMBER_ID)
                .getHtmlValueAsNumber();

        int clay = document.findById(CLAY_ID)
                .getHtmlValueAsNumber();

        int iron = document.findById(IRON_ID)
                .getHtmlValueAsNumber();

        return VillageStock.builder()
                .warehouseCapacity(warehouseCapacity)
                .granaryCapacity(granaryCaption)
                .lumber(lumber)
                .clay(clay)
                .iron(iron)
                .crop(crop)
                .freeCrop(freeCrop)
                .build();
    }
}
