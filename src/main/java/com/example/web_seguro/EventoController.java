package com.example.web_seguro;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class EventoController {

    private final TokenStore tokenStore;
    private final RestTemplate restTemplate;

    public EventoController(TokenStore tokenStore, RestTemplate restTemplate) {
        this.tokenStore = tokenStore;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/evento/{id}")
    public String detalleEvento(@PathVariable("id") Integer id, Model model) {
        String url = "http://localhost:8080/eventos/" + id;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", tokenStore.getToken());
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Evento> response = restTemplate.exchange(url, HttpMethod.GET, entity, Evento.class);

        model.addAttribute("evento", response.getBody());
        return "detalle";
    }
}
