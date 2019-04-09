package quarkus;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonPatcherTest {

    JsonPatcher patcher = new JsonPatcher();

    @Test
    void patchMyJson() {
        String result = patcher.patchMyJson("ola");
        assertEquals("{\"id\":\"321\",\"user\":{\"id\":\"17\",\"name\":\"ola\",\"phone\":\"87654321\"}}", result);
    }

}