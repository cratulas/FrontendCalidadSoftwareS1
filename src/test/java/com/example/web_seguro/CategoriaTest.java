package com.example.web_seguro;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CategoriaTest {

    @Test
    public void testConstructorVacioYSetters() {
        Categoria categoria = new Categoria();
        categoria.setNombre("Estrategia");
        assertEquals("Estrategia", categoria.getNombre());
    }

    @Test
    public void testConstructorConParametros() {
        Categoria categoria = new Categoria("Arcade");
        assertEquals("Arcade", categoria.getNombre());
    }
}
