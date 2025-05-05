package com.example.web_seguro;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class WebSeguroApplicationMainTest {

    @Test
    void testMain() {
        assertDoesNotThrow(() -> WebSeguroApplication.main(new String[] {}));
    }
}
