package com.perfulandia.pedidos.controller;

import com.perfulandia.pedidos.model.pedidoModel;
import com.perfulandia.pedidos.service.pedidoService;

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

@WebMvcTest(PedidoControllerTest.class)
public class PedidoControllerTest {
    //Crear hhtp pra poder realizar pruebas unitarias injectar
    @Autowired
    private MockMvc mockMvc;

    //simulacion del servicio para evitar acceder a datos reales
    @MockitoBean
    private pedidoService service;


    //ObjectMapper comvierte los datos de json a texto y vise versa
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Testing Controller 1 - Mostrar Pedidos")
    void testGetAll() throws Exception{
        when(service.listar()).thenReturn(List.of(new pedidoModel(1,"21466878-k","CR7","Perfume cítrico","En camino")));
        //2.-Ejecutar una peticion get falsa
        mockMvc.perform(get("/api/pedidos/mostrar/pedidos"))
                //lo que esperamos en esa peticion
                .andExpect(status().isOk())//codigo 200
                //4.- verificacion que el primer elemento JSON sea el producto CR7
                .andExpect(jsonPath("$[0].nombre").value("Pepe"));
    }
    //POST
    @Test
    @DisplayName("Testing controller 2-Guardar Productos")
    void testPost() throws Exception {
        pedidoModel producto = new pedidoModel(1,"21466878-k","CR7","Perfume cítrico","En camino");
        //2 Simular con mockito el guardar este producto y me devuelve uno con el id ya asignado
        when(service.guardar(any())).thenReturn(new pedidoModel(1,"21466878-k","CR7","Perfume cítrico","En camino"));
        //3
        mockMvc.perform(post("/api/pedidos")
                        .contentType("application/json")// indicar que el contenido es JSON
                        .content(mapper.writeValueAsString(producto)))//convertimos el obejto JSON
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Mario"));
    }
}
