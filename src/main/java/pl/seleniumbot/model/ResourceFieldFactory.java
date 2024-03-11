package pl.seleniumbot.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourceFieldFactory {

    public static final Pattern RESOURCE_PATTERN = Pattern.compile("good level colorLayer gid(\\d{1,2})+ buildingSlot(\\d{1,2})+  level(\\d{1,2})+");

    public static ResourceField create(String webElementClass) {
        Matcher matcher = RESOURCE_PATTERN.matcher(webElementClass);
        return matcher.results().map(m -> ResourceField.builder()
                        .type(ResourceType.fromId(Integer.parseInt(m.group(1))))
                        .id(Integer.parseInt(m.group(2)))
                        .level(Integer.parseInt(m.group(3)))
                        .build())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("String %s can't be parsed".formatted(webElementClass)));
    }
}
