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

@WebMvcTest(pedidoController.class)
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
        mockMvc.perform(get("/api/pedidos/listar/pedidos"))
                //lo que esperamos en esa peticion
                .andExpect(status().isOk())//codigo 200
                //4.- verificacion que el primer elemento JSON sea el pedido 1
                .andExpect(jsonPath("$[0].producto").value("CR7"));
    }

    //POST
    @Test
    @DisplayName("Testing controller 2-Guardar Pedido")
    void testPost() throws Exception {
        pedidoModel pedido = new pedidoModel(1,"21466878-k","CR7","Perfume cítrico","En camino");
        //2 Simular con mockito el guardar este pedido y me devuelve uno con el id ya asignado
        when(service.guardar((pedido))).thenReturn(new pedidoModel(1,"21466878-k","CR7","Perfume cítrico","En camino"));
        //3
        mockMvc.perform(post("/api/pedidos/guardar/pedido")
                        .contentType("application/json")// indicar que el contenido es JSON
                        .content(mapper.writeValueAsString(pedido)))//convertimos el obejto JSON
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.producto").value("CR7"));
    }
}
