package com.gestion_productos_reactivo.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion_productos_reactivo.demo.dto.ProductoSolicitud;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductoIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearProducto() throws Exception {
        ProductoSolicitud solicitud = new ProductoSolicitud();
        solicitud.setNombre("Agua");
        solicitud.setPrecio(15.5);

        mockMvc.perform(post("/producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(solicitud)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Agua"))
                .andExpect(jsonPath("$.precio").value(15.5))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void testConsultarProducto() throws Exception {
        ProductoSolicitud solicitud = new ProductoSolicitud();
        solicitud.setNombre("Pan");
        solicitud.setPrecio(2.0);

        String response = mockMvc.perform(post("/producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(solicitud)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        int id = objectMapper.readTree(response).get("id").asInt();

        mockMvc.perform(get("/producto/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Pan"))
                .andExpect(jsonPath("$.precio").value(2.0))
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    void testEliminarProducto() throws Exception {
        ProductoSolicitud solicitud = new ProductoSolicitud();
        solicitud.setNombre("Galletas");
        solicitud.setPrecio(5.0);

        String response = mockMvc.perform(post("/producto")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(solicitud)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        int id = objectMapper.readTree(response).get("id").asInt();

        mockMvc.perform(delete("/producto/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string("Producto eliminado exitosamente"));
    }
}