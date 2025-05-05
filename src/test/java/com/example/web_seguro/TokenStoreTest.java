package com.example.web_seguro;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TokenStoreTest {

    @Test
    void testSetAndGetToken() {
        TokenStore tokenStore = new TokenStore();
        tokenStore.setToken("abc123");
        assertEquals("abc123", tokenStore.getToken());
    }

    @Test
    void testClearToken() {
        TokenStore tokenStore = new TokenStore();
        tokenStore.setToken("xyz");
        tokenStore.setToken(null);
        assertNull(tokenStore.getToken());
    }
}
