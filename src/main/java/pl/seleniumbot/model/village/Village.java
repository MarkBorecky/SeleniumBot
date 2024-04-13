package pl.seleniumbot.model.village;

import lombok.Builder;
import lombok.Value;
import pl.seleniumbot.model.Building;
import pl.seleniumbot.model.ResourceField;
import pl.seleniumbot.model.VillageStock;

import java.util.List;

@Builder
@Value
public class Village {
    List<ResourceField> resourceFields;
    VillageStock stock;
    List<Building> buildings;
}
