package com.perfulandia.productservice.controller;

import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.service.ProductoService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

//Anotación que indica que solo probará el videjuegoController
@WebMvcTest(ProductoControllerTest.class)
public class ProductoControllerTest{
    //Crear hhtp pra poder realizar pruebas unitarias injectar
    @Autowired
    private MockMvc mockMvc;

    //simulacion del servicio para evitar acceder a datos reales
    @MockitoBean
    private ProductoService service;


    //ObjectMapper comvierte los datos de json a texto y vise versa
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Testing Controller 1 - Mostrar Productos")
    void testGetAll() throws Exception{
        when(service.listar()).thenReturn(List.of(new Producto(1,"CR7",46900,15)));
        //2.-Ejecutar una peticion get falsa
        mockMvc.perform(get("/api/productos/mostrar/productos"))
                //lo que esperamos en esa peticion
                .andExpect(status().isOk())//codigo 200
                //4.- verificacion que el primer elemento JSON sea el producto CR7
                .andExpect(jsonPath("$[0].nombre").value("Pepe"));
    }

    //POST
    @Test
    @DisplayName("Testing controller 2-Guardar Productos")
    void testPost() throws Exception {
        Producto producto = new Producto(1,"CR7",46900,15);
        //2 Simular con mockito el guardar este producto y me devuelve uno con el id ya asignado
        when(service.guardar(any())).thenReturn(new Producto(1,"CR7",46900,15));
        //3
        mockMvc.perform(post("/api/productos")
                        .contentType("application/json")// indicar que el contenido es JSON
                        .content(mapper.writeValueAsString(producto)))//convertimos el obejto JSON
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Mario"));
    }
}
