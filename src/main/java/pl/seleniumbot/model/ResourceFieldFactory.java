package pl.seleniumbot.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourceFieldFactory {

    public static ResourceField create(String webElementClass) {
        Pattern resourcePattern = Pattern.compile("good level colorLayer gid(\\d{1,2})+ buildingSlot(\\d{1,2})+ level(\\d{1,2})+");
        Matcher matcher = resourcePattern.matcher(webElementClass);
        return matcher.results().map(m -> ResourceField.builder()
                        .type(ResourceType.fromId(Integer.parseInt(m.group(1))))
                        .id(Integer.parseInt(m.group(2)))
                        .level(Integer.parseInt(m.group(3)))
                        .build())
                .findFirst()
                .orElseThrow();
    }
}
