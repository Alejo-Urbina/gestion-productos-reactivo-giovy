package com.gestion_productos_reactivo.demo.repositorio;

import com.gestion_productos_reactivo.demo.dto.ProductoRespuesta;
import com.gestion_productos_reactivo.demo.dto.ProductoSolicitud;
import com.gestion_productos_reactivo.demo.excepciones.ProductoExcepcion;
import com.gestion_productos_reactivo.demo.repositorio.entidad.ProductoEntidad;
import com.gestion_productos_reactivo.demo.repositorio.interfaces.ProductoRepositorio;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoRepositorioImpl implements ProductoRepositorio {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public ProductoRespuesta crearProducto(ProductoSolicitud productoSolicitud) {
        ProductoEntidad entidad = new ProductoEntidad(productoSolicitud.getNombre(), productoSolicitud.getPrecio());
        em.persist(entidad);
        return mapearRespuesta(entidad);
    }

    @Override
    public ProductoRespuesta consultarProducto(Integer id) {
        ProductoEntidad entidad = em.find(ProductoEntidad.class, Long.valueOf(id));
        if(entidad == null){
            throw new ProductoExcepcion("No se encuentra el producto");
        }
        return mapearRespuesta(entidad);
    }

    @Override
    @Transactional
    public String eliminarProducto(Integer id) {
        ProductoEntidad entidad = em.find(ProductoEntidad.class, Long.valueOf(id));
        if (entidad == null) {
            throw new ProductoExcepcion("No se encuentra el producto a eliminar");
        }
        em.remove(entidad);
        return "Producto eliminado exitosamente";
    }

    public ProductoRespuesta mapearRespuesta(ProductoEntidad productoEntidad){
        return new ProductoRespuesta(productoEntidad.getNombre(), productoEntidad.getPrecio(), productoEntidad.getId());
    }
}
