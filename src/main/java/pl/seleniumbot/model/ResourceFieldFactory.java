package pl.seleniumbot.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourceFieldFactory {

    public static ResourceField create(String webElementClass) {
        String text2 = "good level colorLayer gid4 buildingSlot8 level0";
        Pattern resourcePattern = Pattern.compile("good level colorLayer gid([0-9]{1,2})+ buildingSlot([0-9]{1,2})+ level([0–9]{1,2})+");
        Matcher matcher = resourcePattern.matcher(text2);
        return matcher.results().map(m -> ResourceField.builder()
                        .id(Integer.parseInt(m.group(2)))
                        .level(Integer.parseInt(m.group(3)))
                        .type(ResourceType.fromId(Integer.parseInt(m.group(1))))
                        .build())
                .findFirst()
                .orElseThrow();
    }
}
