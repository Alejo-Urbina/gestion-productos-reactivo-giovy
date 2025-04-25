package com.gestion_productos_reactivo.demo.dto;

public class ProductoRespuesta {

    private Integer id;
    private String nombre;
    private Double precio;

    public ProductoRespuesta(String nombre, Double precio, Integer id) {
        this.nombre = nombre;
        this.precio = precio;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
