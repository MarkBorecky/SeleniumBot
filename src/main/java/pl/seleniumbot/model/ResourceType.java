package pl.seleniumbot.model;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum ResourceType {
    IRON(3),
    CROP(4),
    CLAY(2),
    LUMBER(1);

    private final int id;

    public static ResourceType fromId(int id) {
        return Arrays.stream(ResourceType.values()).filter(resourceType -> resourceType.id == id)
                .findFirst()
                .orElseThrow();
    }
}
