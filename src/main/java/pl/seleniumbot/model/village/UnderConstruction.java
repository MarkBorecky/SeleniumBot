package pl.seleniumbot.model.village;

import lombok.Value;
import pl.seleniumbot.model.Building;

import java.time.LocalDateTime;

@Value
public class UnderConstruction {
    Building building;
    LocalDateTime endOfConstruction;
    int maintenance;
}
