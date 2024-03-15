package pl.seleniumbot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//TODO: inline with Building class
public class ResourceField {
    private ResourceType type;
    private int level;
    private int id;
}
