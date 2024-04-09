package pl.seleniumbot;

import org.assertj.core.api.SoftAssertions;
import org.eclipse.collections.api.list.MutableList;
import org.junit.jupiter.api.Test;
import pl.seleniumbot.model.ResourceField;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pl.seleniumbot.model.ResourceType.*;

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
}
