package pl.seleniumbot.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BuildingType {
    BAKERY(9),
    BRICKYARD(6),
    CLAY_PIT(2),
    CROPLAND(4),
    GRAIN_MILL(8),
    GRANARY(11),
    GREAT_GRANARY(39),
    GREAT_WAREHOUSE(38),
    IRON_FOUNDRY(7),
    IRON_MINE(3),
    SAWMILL(5),
    WAREHOUSE(10),
    WATERWORKS(45),
    WOODCUTTER(1),
    ACADEMY(22),
    SMITHY(13),
    BARRACKS(19),
    BLACKSMITH(12),
    CITY_WALL(31),
    EARTH_WALL(32),
    GREAT_BARRACKS(29),
    GREAT_STABLE(30),
    HEROS_MANSION(37),
    MAKESHIFT_WALL(43),
    PALISADE(33),
    RALLY_POINT(16),
    STABLE(20),
    STONE_WALL(42),
    STONEMASON(34),
    TOURNAMENT_SQUARE(14),
    TRAPPER(36),
    WATCHTOWER(47),
    WORKSHOP(21),
    BREWERY(35),
    COMMAND_CENTER(44),
    CRANNY(23),
    EMBASSY(18),
    HORSE_DRINKING_TROUGH(41),
    MAIN_BUILDING(15),
    MARKETPLACE(17),
    PALACE(26),
    RESIDENCE(25),
    TOWN_HALL(24),
    TRADE_OFFICE(28),
    TREASURY(27),
    WONDER_OF_THE_WORLD(40);
    public final int id;

    public static BuildingType getById(int id) {
        for (BuildingType type : BuildingType.values()) {
            if (type.id == id) {
                return type;
            }
        }
        throw new IllegalArgumentException("BuilingType with id %d doesn't exist".formatted(id));
    }
}
