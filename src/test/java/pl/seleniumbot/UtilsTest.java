/* mbor1 created on 11.11.2020 
inside the package - pl.seleniumbot */

package pl.seleniumbot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.seleniumbot.model.ResourceField;
import pl.seleniumbot.model.ResourceFieldFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.seleniumbot.model.ResourceType.CROP;
import static pl.seleniumbot.model.ResourceType.LUMBER;

public class UtilsTest {

    @Test
    void shouldBe100() {
        String duration = "00:01:40";
        assertEquals(100, Utils.parseToSeconds(duration));
    }

    @Test
    void shouldBe3661() {
        String duration = "01:01:01";
        assertEquals(3661, Utils.parseToSeconds(duration));
    }

    @Test
    void shouldBe86400() {
        String duration = "24:00:00";
        assertEquals(86400, Utils.parseToSeconds(duration));
    }

    @Test
    void shouldReadResourceFieldFromString() {
        ResourceField resourceField = ResourceFieldFactory.create("good level colorLayer gid4 buildingSlot28 level19");
        Assertions.assertThat(resourceField).usingRecursiveComparison()
                .isEqualTo(ResourceField.builder()
                        .type(CROP)
                        .id(28)
                        .level(19)
                        .build());
    }
}
