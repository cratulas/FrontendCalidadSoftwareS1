package com.example.web_seguro;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
public class HomeController {

    private final TokenStore tokenStore;
    private final RestTemplate restTemplate;

    public HomeController(TokenStore tokenStore, RestTemplate restTemplate) {
        this.tokenStore = tokenStore;
        this.restTemplate = restTemplate;
    }

    @GetMapping({"/", "/home"})
    public String home(@RequestParam(name = "name", required = false, defaultValue = "Comunidad Gamer") String name,
                       Model model) {
        String url = "http://localhost:8080/eventos";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", tokenStore.getToken());
        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<Evento[]> response = restTemplate.exchange(url, HttpMethod.GET, entity, Evento[].class);
        Evento[] eventos = response.getBody();

        model.addAttribute("name", name);
        model.addAttribute("eventos", eventos);

        return "home";
    }

    @GetMapping("/greetings")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "Juan González") String name,
                           Model model) {
        String url = "http://localhost:8080/greetings";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", tokenStore.getToken());
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .uri(java.net.URI.create(url))
                .queryParam("name", name);
        ResponseEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class
        );

        model.addAttribute("name", response.getBody());
        return "Greetings";
    }
}
