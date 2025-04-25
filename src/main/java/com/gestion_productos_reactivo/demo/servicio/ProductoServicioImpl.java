package com.gestion_productos_reactivo.demo.servicio;

import com.gestion_productos_reactivo.demo.dto.ProductoRespuesta;
import com.gestion_productos_reactivo.demo.dto.ProductoSolicitud;
import com.gestion_productos_reactivo.demo.repositorio.interfaces.ProductoRepositorio;
import com.gestion_productos_reactivo.demo.servicio.interfaces.ProductoServicio;
import org.springframework.stereotype.Service;

@Service
public class ProductoServicioImpl implements ProductoServicio {

    private final ProductoRepositorio repositorio;

    public ProductoServicioImpl(ProductoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public ProductoRespuesta crearProducto(ProductoSolicitud productoSolicitud) {
        return repositorio.crearProducto(productoSolicitud);
    }

    @Override
    public ProductoRespuesta consultarProducto(Integer id) {
        return repositorio.consultarProducto(id);
    }

    @Override
    public String eliminarProducto(Integer id) {
        return repositorio.eliminarProducto(id);
    }
}
