package pl.seleniumbot.model;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.factory.Lists;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourceFieldFactory {

    public static final Pattern PATTERN = Pattern.compile("good level colorLayer gid(\\d{1,2})+ buildingSlot(\\d{1,2})+  level(\\d{1,2})+");

    public static MutableList<ResourceField> create(String webElementClass) {
        Pattern resourcePattern = PATTERN;
        Matcher matcher = resourcePattern.matcher(webElementClass);
        var list = matcher.results().map(m -> ResourceField.builder()
                        .type(ResourceType.fromId(Integer.parseInt(m.group(1))))
                        .id(Integer.parseInt(m.group(2)))
                        .level(Integer.parseInt(m.group(3)))
                        .build())
                .toList();
        return Lists.adapt(list);
    }
}
