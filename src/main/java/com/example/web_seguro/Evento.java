package com.example.web_seguro;

import java.util.List;

public class Evento {
    private Integer id;
    private String nombre;
    private String fecha;
    private String ubicacion;
    private List<String> juegosRelacionados;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public List<String> getJuegosRelacionados() { return juegosRelacionados; }
    public void setJuegosRelacionados(List<String> juegosRelacionados) { this.juegosRelacionados = juegosRelacionados; }
}
