package pl.seleniumbot.model;

import lombok.Builder;

@Builder
public record VillageStock(
        int warehouseCapacity,
        int granaryCapacity,
        int crop,
        int freeCrop,
        int lumber,
        int clay,
        int iron
){}
