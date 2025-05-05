package com.example.web_seguro;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
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

@WebMvcTest(EventoController.class)
public class EventoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenStore tokenStore;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    @WithMockUser(username = "testuser", roles = {"USER"})
    public void testDetalleEvento() throws Exception {
        Evento mockEvento = new Evento();
        mockEvento.setId(1);
        mockEvento.setNombre("Test Evento");

        when(tokenStore.getToken()).thenReturn("Bearer test-token");

        ResponseEntity<Evento> response = new ResponseEntity<>(mockEvento, HttpStatus.OK);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(Evento.class)))
            .thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/evento/1"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("evento"))
                .andExpect(view().name("detalle"));
    }
}
