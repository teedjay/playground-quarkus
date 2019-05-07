package quarkus.json;

import org.junit.jupiter.api.Test;
import quarkus.json.JsonPatcher;

import static org.junit.jupiter.api.Assertions.*;

class JsonPatcherTest {

    // testing functionality directly (not thru jax-rs container)
    JsonPatcher patcher = new JsonPatcher();

    @Test
    void patchMyJson() {
        String result = patcher.patchMyJson("ola");
        assertEquals("{\"id\":\"321\",\"user\":{\"id\":\"17\",\"name\":\"ola\",\"phone\":\"87654321\"}}", result);
    }

}