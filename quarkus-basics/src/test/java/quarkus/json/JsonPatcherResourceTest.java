package quarkus.json;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonPatcherResourceTest {

    // testing functionality directly (not thru jax-rs container)
    JsonPatcherResource patcher = new JsonPatcherResource();

    @Test
    void patchMyJson() {
        String result = patcher.patchMyJson("ola");
        assertEquals("{\"id\":\"321\",\"user\":{\"id\":\"17\",\"name\":\"ola\",\"phone\":\"87654321\"}}", result);
    }

}