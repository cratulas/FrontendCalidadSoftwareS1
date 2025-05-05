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

@WebMvcTest(BuscarController.class)
public class BuscarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenStore tokenStore;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    @WithMockUser(username = "tester", roles = {"USER"})
    public void testBuscarEventosSinParametros() throws Exception {
        Evento[] eventos = new Evento[0]; // Simula respuesta vacía

        when(tokenStore.getToken()).thenReturn("Bearer fake-token");
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(Evento[].class)))
            .thenReturn(new ResponseEntity<>(eventos, HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders.get("/buscar"))
                .andExpect(status().isOk())
                .andExpect(view().name("buscar"))
                .andExpect(model().attributeExists("eventos"));
    }


    @Test
    @WithMockUser(username = "tester", roles = {"USER"})
    public void testBuscarEventosConTodosLosParametros() throws Exception {
        Evento[] eventos = new Evento[0];

        when(tokenStore.getToken()).thenReturn("Bearer fake-token");
        when(restTemplate.exchange(contains("/eventos/buscar?nombre=Evento&juego=FIFA&ubicacion=Santiago&fecha=2025-05-10&categoria=Competencia"),
                eq(HttpMethod.GET), any(HttpEntity.class), eq(Evento[].class)))
            .thenReturn(new ResponseEntity<>(eventos, HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders.get("/buscar")
                .param("nombre", "Evento")
                .param("juego", "FIFA")
                .param("ubicacion", "Santiago")
                .param("fecha", "2025-05-10")
                .param("categoria", "Competencia"))
            .andExpect(status().isOk())
            .andExpect(view().name("buscar"))
            .andExpect(model().attributeExists("eventos"));
    }

    @Test
    @WithMockUser(username = "tester", roles = {"USER"})
    public void testBuscarEventosConParametrosParciales() throws Exception {
        Evento[] eventos = new Evento[0];

        when(tokenStore.getToken()).thenReturn("Bearer fake-token");
        when(restTemplate.exchange(contains("/eventos/buscar?nombre=LAN&juego=Valorant"),
                eq(HttpMethod.GET), any(HttpEntity.class), eq(Evento[].class)))
            .thenReturn(new ResponseEntity<>(eventos, HttpStatus.OK));

        mockMvc.perform(MockMvcRequestBuilders.get("/buscar")
                .param("nombre", "LAN")
                .param("juego", "Valorant"))
            .andExpect(status().isOk())
            .andExpect(view().name("buscar"))
            .andExpect(model().attributeExists("eventos"));
}

@Test
@WithMockUser(username = "tester", roles = {"USER"})
public void testBuscarEventosConNombreYJuego() throws Exception {
    Evento[] eventos = new Evento[0];

    when(tokenStore.getToken()).thenReturn("Bearer fake-token");
    when(restTemplate.exchange(
            contains("/eventos/buscar?nombre=Conferencia&juego=LoL"),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(Evento[].class)))
        .thenReturn(new ResponseEntity<>(eventos, HttpStatus.OK));

    mockMvc.perform(MockMvcRequestBuilders.get("/buscar")
            .param("nombre", "Conferencia")
            .param("juego", "LoL"))
        .andExpect(status().isOk())
        .andExpect(view().name("buscar"))
        .andExpect(model().attributeExists("eventos"));
}


@Test
@WithMockUser(username = "tester", roles = {"USER"})
public void testBuscarEventosSoloFecha() throws Exception {
    Evento[] eventos = new Evento[0];

    when(tokenStore.getToken()).thenReturn("Bearer fake-token");
    when(restTemplate.exchange(
            contains("/eventos/buscar?fecha=2025-05-15"),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(Evento[].class)))
        .thenReturn(new ResponseEntity<>(eventos, HttpStatus.OK));

    mockMvc.perform(MockMvcRequestBuilders.get("/buscar")
            .param("fecha", "2025-05-15"))
        .andExpect(status().isOk())
        .andExpect(view().name("buscar"))
        .andExpect(model().attributeExists("eventos"));
}
@Test
@WithMockUser(username = "tester", roles = {"USER"})
public void testBuscarEventosSoloUbicacion() throws Exception {
    Evento[] eventos = new Evento[0];

    when(tokenStore.getToken()).thenReturn("Bearer fake-token");
    when(restTemplate.exchange(
            contains("/eventos/buscar?ubicacion=Valparaiso"),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(Evento[].class)))
        .thenReturn(new ResponseEntity<>(eventos, HttpStatus.OK));

    mockMvc.perform(MockMvcRequestBuilders.get("/buscar")
            .param("ubicacion", "Valparaiso"))
        .andExpect(status().isOk())
        .andExpect(view().name("buscar"))
        .andExpect(model().attributeExists("eventos"));
}

@Test
@WithMockUser(username = "tester", roles = {"USER"})
public void testBuscarEventosSoloCategoria() throws Exception {
    Evento[] eventos = new Evento[0];

    when(tokenStore.getToken()).thenReturn("Bearer fake-token");
    when(restTemplate.exchange(
            contains("/eventos/buscar?categoria=LAN"),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(Evento[].class)))
        .thenReturn(new ResponseEntity<>(eventos, HttpStatus.OK));

    mockMvc.perform(MockMvcRequestBuilders.get("/buscar")
            .param("categoria", "LAN"))
        .andExpect(status().isOk())
        .andExpect(view().name("buscar"))
        .andExpect(model().attributeExists("eventos"));
}


@Test
@WithMockUser(username = "tester", roles = {"USER"})
public void testBuscarEventosNombreVacioEspacios() throws Exception {
    Evento[] eventos = new Evento[0];

    when(tokenStore.getToken()).thenReturn("Bearer fake-token");
    when(restTemplate.exchange(
            contains("/eventos/buscar"), // sin query param "nombre"
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(Evento[].class)))
        .thenReturn(new ResponseEntity<>(eventos, HttpStatus.OK));

    mockMvc.perform(MockMvcRequestBuilders.get("/buscar")
            .param("nombre", "   "))
        .andExpect(status().isOk())
        .andExpect(view().name("buscar"))
        .andExpect(model().attributeExists("eventos"));
}

@Test
@WithMockUser(username = "tester", roles = {"USER"})
public void testBuscarEventosJuegoEnBlanco() throws Exception {
    Evento[] eventos = new Evento[0];

    when(tokenStore.getToken()).thenReturn("Bearer fake-token");
    when(restTemplate.exchange(
            contains("/eventos/buscar"),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(Evento[].class)))
        .thenReturn(new ResponseEntity<>(eventos, HttpStatus.OK));

    mockMvc.perform(MockMvcRequestBuilders.get("/buscar")
            .param("juego", "   "))
        .andExpect(status().isOk())
        .andExpect(view().name("buscar"))
        .andExpect(model().attributeExists("eventos"));
}

@Test
@WithMockUser(username = "tester", roles = {"USER"})
public void testBuscarEventosUbicacionEnBlanco() throws Exception {
    Evento[] eventos = new Evento[0];

    when(tokenStore.getToken()).thenReturn("Bearer fake-token");
    when(restTemplate.exchange(
            contains("/eventos/buscar"),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(Evento[].class)))
        .thenReturn(new ResponseEntity<>(eventos, HttpStatus.OK));

    mockMvc.perform(MockMvcRequestBuilders.get("/buscar")
            .param("ubicacion", "   "))
        .andExpect(status().isOk())
        .andExpect(view().name("buscar"))
        .andExpect(model().attributeExists("eventos"));
}
@Test
@WithMockUser(username = "tester", roles = {"USER"})
public void testBuscarEventosFechaEnBlanco() throws Exception {
    Evento[] eventos = new Evento[0];

    when(tokenStore.getToken()).thenReturn("Bearer fake-token");
    when(restTemplate.exchange(
            contains("/eventos/buscar"),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(Evento[].class)))
        .thenReturn(new ResponseEntity<>(eventos, HttpStatus.OK));

    mockMvc.perform(MockMvcRequestBuilders.get("/buscar")
            .param("fecha", "   "))
        .andExpect(status().isOk())
        .andExpect(view().name("buscar"))
        .andExpect(model().attributeExists("eventos"));
}
@Test
@WithMockUser(username = "tester", roles = {"USER"})
public void testBuscarEventosCategoriaEnBlanco() throws Exception {
    Evento[] eventos = new Evento[0];

    when(tokenStore.getToken()).thenReturn("Bearer fake-token");
    when(restTemplate.exchange(
            contains("/eventos/buscar"),
            eq(HttpMethod.GET),
            any(HttpEntity.class),
            eq(Evento[].class)))
        .thenReturn(new ResponseEntity<>(eventos, HttpStatus.OK));

    mockMvc.perform(MockMvcRequestBuilders.get("/buscar")
            .param("categoria", "   "))
        .andExpect(status().isOk())
        .andExpect(view().name("buscar"))
        .andExpect(model().attributeExists("eventos"));
}

}
