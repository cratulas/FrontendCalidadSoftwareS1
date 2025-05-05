package com.example.web_seguro;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.client.RestTemplate;

public class CustomAuthenticationProviderTest {

    @Mock
    private TokenStore tokenStore;

    @Mock
    private RestTemplate restTemplate;

    private CustomAuthenticationProvider authenticationProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authenticationProvider = new CustomAuthenticationProvider(tokenStore, restTemplate);
    }

    @Test
    void testAuthenticateSuccess() {
        String username = "testuser";
        String password = "testpass";

        UsernamePasswordAuthenticationToken inputAuth =
                new UsernamePasswordAuthenticationToken(username, password);

        ResponseEntity<String> response = new ResponseEntity<>("mocked-jwt-token", HttpStatus.OK);
        when(restTemplate.postForEntity(anyString(), any(), eq(String.class))).thenReturn(response);

        Authentication result = authenticationProvider.authenticate(inputAuth);

        assertNotNull(result);
        assertEquals(username, result.getName());
        assertEquals(password, result.getCredentials());
        assertTrue(result.getAuthorities().stream().anyMatch(
                auth -> auth.getAuthority().equals("ROLE_USER")));

        verify(tokenStore).setToken("mocked-jwt-token");
    }

    @Test
    void testAuthenticateFailsWithInvalidCredentials() {
        String username = "testuser";
        String password = "wrongpass";

        UsernamePasswordAuthenticationToken inputAuth =
                new UsernamePasswordAuthenticationToken(username, password);

        ResponseEntity<String> response = new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        when(restTemplate.postForEntity(anyString(), any(), eq(String.class))).thenReturn(response);

        assertThrows(BadCredentialsException.class, () -> {
            authenticationProvider.authenticate(inputAuth);
        });

        verify(tokenStore, never()).setToken(anyString());
    }

    @Test
    void testSupportsCorrectAuthenticationClass() {
        assertTrue(authenticationProvider.supports(UsernamePasswordAuthenticationToken.class));
        assertFalse(authenticationProvider.supports(Authentication.class));
    }
}
