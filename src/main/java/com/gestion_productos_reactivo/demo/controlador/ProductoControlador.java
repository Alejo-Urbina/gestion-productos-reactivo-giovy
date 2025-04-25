package com.gestion_productos_reactivo.demo.controlador;

import com.gestion_productos_reactivo.demo.dto.ProductoRespuesta;
import com.gestion_productos_reactivo.demo.dto.ProductoSolicitud;
import com.gestion_productos_reactivo.demo.servicio.interfaces.ProductoServicio;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class ProductoControlador {

    private final ProductoServicio servicio;

    public ProductoControlador(ProductoServicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ProductoRespuesta crearProducto(@RequestBody ProductoSolicitud productoSolicitud){
        return servicio.crearProducto(productoSolicitud);
    }

    @GetMapping("/{id}")
    public ProductoRespuesta consultarProducto(@PathVariable("id") Integer id){
        return servicio.consultarProducto(id);
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable("id") Integer id){
        return servicio.eliminarProducto(id);
    }

}
