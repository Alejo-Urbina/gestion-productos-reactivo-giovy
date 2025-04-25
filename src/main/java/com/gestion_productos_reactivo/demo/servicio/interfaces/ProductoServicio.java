package com.gestion_productos_reactivo.demo.servicio.interfaces;

import com.gestion_productos_reactivo.demo.dto.ProductoRespuesta;
import com.gestion_productos_reactivo.demo.dto.ProductoSolicitud;

public interface ProductoServicio {

    ProductoRespuesta crearProducto(ProductoSolicitud productoSolicitud);

    ProductoRespuesta consultarProducto(Integer id);

    String eliminarProducto(Integer id);

}
