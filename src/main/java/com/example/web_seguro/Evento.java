package com.example.web_seguro;

import java.util.List;

public class Evento {
    private Integer id;
    private String nombre;
    private Categoria categoria;
    private String fecha;
    private String ubicacion;
    private List<String> juegosRelacionados;
    private String descripcion;
    private String direccion;
    private String hora;
    private String organizadores;
    private List<String> servicios;
    private List<String> expositores;

    // Getters y Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public List<String> getJuegosRelacionados() { return juegosRelacionados; }
    public void setJuegosRelacionados(List<String> juegosRelacionados) { this.juegosRelacionados = juegosRelacionados; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getOrganizadores() { return organizadores; }
    public void setOrganizadores(String organizadores) { this.organizadores = organizadores; }

    public List<String> getServicios() { return servicios; }
    public void setServicios(List<String> servicios) { this.servicios = servicios; }

    public List<String> getExpositores() { return expositores; }
    public void setExpositores(List<String> expositores) { this.expositores = expositores; }
}
