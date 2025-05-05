package com.example.web_seguro;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

@WebMvcTest(HomeController.class)
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenStore tokenStore;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testHomePageLoads() throws Exception {
        Evento[] eventos = new Evento[0]; // simulamos respuesta vacía
        when(tokenStore.getToken()).thenReturn("Bearer test-token");

        ResponseEntity<Evento[]> responseEntity = new ResponseEntity<>(eventos, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(Evento[].class)))
            .thenReturn(responseEntity);

        mockMvc.perform(MockMvcRequestBuilders.get("/home"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("eventos"))
                .andExpect(view().name("home"));
    }

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testGreeting() throws Exception {
        String name = "Tester";
        String expectedResponse = "Hola, Tester!";
        when(tokenStore.getToken()).thenReturn("Bearer test-token");


        ResponseEntity<String> responseEntity = new ResponseEntity<>(expectedResponse, HttpStatus.OK);

        when(restTemplate.exchange(
                contains("greetings"),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(String.class)))
            .thenReturn(responseEntity);

        mockMvc.perform(MockMvcRequestBuilders.get("/greetings").param("name", name))
                .andExpect(status().isOk())
                .andExpect(view().name("Greetings"))
                .andExpect(model().attribute("name", expectedResponse));
    }

}
