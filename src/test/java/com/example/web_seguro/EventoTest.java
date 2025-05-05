package com.example.web_seguro;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class EventoTest {

    @Test
    public void testGettersAndSetters() {
        Evento evento = new Evento();

        Integer id = 1;
        String nombre = "Gaming Fest";
        Categoria categoria = new Categoria("Shooter");
        String fecha = "2025-08-15";
        String ubicacion = "Santiago";
        List<String> juegos = Arrays.asList("Call of Duty", "Valorant");
        String descripcion = "Torneo nacional";
        String direccion = "Av. Siempre Viva 123";
        String hora = "10:00";
        String organizadores = "Comunidad Gamer";
        List<String> servicios = Arrays.asList("Wifi", "Estacionamiento");
        List<String> expositores = Arrays.asList("AMD", "Logitech");

        evento.setId(id);
        evento.setNombre(nombre);
        evento.setCategoria(categoria);
        evento.setFecha(fecha);
        evento.setUbicacion(ubicacion);
        evento.setJuegosRelacionados(juegos);
        evento.setDescripcion(descripcion);
        evento.setDireccion(direccion);
        evento.setHora(hora);
        evento.setOrganizadores(organizadores);
        evento.setServicios(servicios);
        evento.setExpositores(expositores);

        assertEquals(id, evento.getId());
        assertEquals(nombre, evento.getNombre());
        assertEquals(categoria, evento.getCategoria());
        assertEquals(fecha, evento.getFecha());
        assertEquals(ubicacion, evento.getUbicacion());
        assertEquals(juegos, evento.getJuegosRelacionados());
        assertEquals(descripcion, evento.getDescripcion());
        assertEquals(direccion, evento.getDireccion());
        assertEquals(hora, evento.getHora());
        assertEquals(organizadores, evento.getOrganizadores());
        assertEquals(servicios, evento.getServicios());
        assertEquals(expositores, evento.getExpositores());
    }
}
