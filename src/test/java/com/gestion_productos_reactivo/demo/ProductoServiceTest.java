package com.gestion_productos_reactivo.demo;


import com.gestion_productos_reactivo.demo.dto.ProductoRespuesta;
import com.gestion_productos_reactivo.demo.dto.ProductoSolicitud;
import com.gestion_productos_reactivo.demo.repositorio.interfaces.ProductoRepositorio;
import com.gestion_productos_reactivo.demo.servicio.ProductoServicioImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductoServiceTest {

    @Mock
    private ProductoRepositorio repositorio;

    @InjectMocks
    private ProductoServicioImpl servicio;

    public ProductoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearProducto() {
        ProductoSolicitud solicitud = new ProductoSolicitud();
        solicitud.setNombre("Gaseosa");
        solicitud.setPrecio(25.2);

        ProductoRespuesta respuestaEsperada = new ProductoRespuesta("Gaseosa", 25.2, 1);
        when(repositorio.crearProducto(solicitud)).thenReturn(respuestaEsperada);

        ProductoRespuesta resultado = servicio.crearProducto(solicitud);

        assertEquals(respuestaEsperada.getNombre(), resultado.getNombre());
        assertEquals(respuestaEsperada.getPrecio(), resultado.getPrecio());
        assertEquals(respuestaEsperada.getId(), resultado.getId());

        verify(repositorio).crearProducto(solicitud);
    }

    @Test
    void testConsultarProducto() {
        int id = 1;
        ProductoRespuesta respuesta = new ProductoRespuesta("Gaseosa", 25.2, id);
        when(repositorio.consultarProducto(id)).thenReturn(respuesta);

        ProductoRespuesta resultado = servicio.consultarProducto(id);

        assertEquals("Gaseosa", resultado.getNombre());
        assertEquals(25.2, resultado.getPrecio());
        assertEquals(id, resultado.getId());

        verify(repositorio).consultarProducto(id);
    }

    @Test
    void testEliminarProducto() {
        int id = 1;
        when(repositorio.eliminarProducto(id)).thenReturn("Producto eliminado exitosamente");

        String resultado = servicio.eliminarProducto(id);

        assertEquals("Producto eliminado exitosamente", resultado);
        verify(repositorio).eliminarProducto(id);
    }
}