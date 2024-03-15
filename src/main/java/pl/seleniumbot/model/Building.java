package pl.seleniumbot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//TODO: inline with ResourceFiled class
public class Building {
    private BuildingType type;
    private int level;
    private int id;
}
