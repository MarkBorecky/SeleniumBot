package pl.seleniumbot.model;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class InfrastructureFactory {

    public MutableList<Building> createFrom(String text) {
        Document doc = Jsoup.parse(text);
        Elements els = doc.getElementsByClass("buildingSlot");
        return Lists.mutable.fromStream(els.stream())
                .collect(el -> Building.builder()
                        .id(Integer.parseInt(el.attr("data-aid")))
                        .type(BuildingType.getById(Integer.parseInt(el.attr("data-gid"))))
                        .level(getLevel(el))
                        .build());
    }

    private int getLevel(Element el) {
        Element a = el.getElementsByTag("a").first();
        if (a == null || "emptyBuildingSlot".equals(a.attr("class"))) {
            return 0;
        }
        return Integer.parseInt(a.attr("data-level"));
    }
}
