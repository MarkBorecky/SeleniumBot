package pl.seleniumbot.model.village;

import lombok.Builder;
import lombok.Value;
import pl.seleniumbot.model.Building;
import pl.seleniumbot.model.ResourceField;
import pl.seleniumbot.model.VillageStock;

import java.util.List;
import java.util.Queue;

@Builder
@Value
public class Village {
    String name;
    VillageStock stock;
    List<ResourceField> resourceFields;
    List<Building> buildings;
    Queue<UnderConstruction> constructionQueue;
}
