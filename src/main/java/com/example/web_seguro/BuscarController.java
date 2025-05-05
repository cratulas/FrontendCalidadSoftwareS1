package com.example.web_seguro;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class BuscarController {

    private final TokenStore tokenStore;
    private final RestTemplate restTemplate;

    public BuscarController(TokenStore tokenStore, RestTemplate restTemplate) {
        this.tokenStore = tokenStore;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/buscar")
    public String buscarEventos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String juego,
            @RequestParam(required = false) String ubicacion,
            @RequestParam(required = false) String fecha,
            @RequestParam(required = false) String categoria,
            Model model) {

        String url = "http://localhost:8080/eventos/buscar";

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .uri(java.net.URI.create(url));

        if (nombre != null && !nombre.isBlank()) builder.queryParam("nombre", nombre);
        if (juego != null && !juego.isBlank()) builder.queryParam("juego", juego);
        if (ubicacion != null && !ubicacion.isBlank()) builder.queryParam("ubicacion", ubicacion);
        if (fecha != null && !fecha.isBlank()) builder.queryParam("fecha", fecha);
        if (categoria != null && !categoria.isBlank()) builder.queryParam("categoria", categoria);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", tokenStore.getToken());
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Evento[]> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                Evento[].class
        );

        model.addAttribute("eventos", response.getBody());
        return "buscar";
    }
}
