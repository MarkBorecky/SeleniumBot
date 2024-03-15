package pl.seleniumbot.model;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class InfrastructureFactory {
    private static final By.ByClassName buildingSlot = new By.ByClassName("buildingSlot");
    private static final By.ByTagName a = new By.ByTagName("a");

    public static MutableList<Building> create(WebElement villageContentText) {
//        return Lists.adapt(villageContentText.findElements(buildingSlot))
//                .collect(el -> Building.builder()
//                        .id(Integer.parseInt(el.getAttribute("data-aid")))
//                        .type(el.getAttribute("data-gid"))
//                        .level(getLevel(el))
//                        .build());
        return null;
    }

    private static int getLevel(WebElement el) {
        WebElement anchor = el.findElement(a);
        if ("emptyBuildingSlot".equals(anchor.getAttribute("class"))) {
            return 0;
        }
        return Integer.parseInt(anchor.getAttribute("data-level"));
    }

    public static MutableList<Building> create2(String text) {
//        Document doc = Jsoup.parse(text);
//        Elements els = doc.getElementsByClass("buildingSlot");
//        return Lists.mutable.fromStream(els.stream())
//                .collect(el -> Building.builder()
//                        .id(Integer.parseInt(el.attr("data-aid")))
//                        .type(el.attr("data-gid"))
//                        .level(getLevel(el))
//                        .build());
        return null;
    }

    private static int getLevel(Element el) {
        Element a = el.getElementsByTag("a").first();
        if (a == null || "emptyBuildingSlot".equals(a.attr("class"))) {
            return 0;
        }
        return Integer.parseInt(a.attr("data-level"));
    }
}
