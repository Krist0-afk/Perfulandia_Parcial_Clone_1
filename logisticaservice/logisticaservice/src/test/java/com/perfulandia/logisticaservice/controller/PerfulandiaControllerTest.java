// URL: http://localhost:8083/swagger-ui/index.html#/
package com.perfulandia.logisticaservice.controller;
import com.perfulandia.logisticaservice.controller.RepartidorController;
import com.perfulandia.logisticaservice.model.Repartidor;
import com.perfulandia.logisticaservice.model.Repartidor;

import com.perfulandia.logisticaservice.service.RepartidorService;
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

//Anotacion que indica que solo probara el RepartidorController
@WebMvcTest(RepartidorController.class)
public class PerfulandiaControllerTest {
    //Crar http para poder realizar pruebas unitarias injectar

    @Autowired

    private MockMvc mockMvc;

    //Simulacion del servicio para evitar acceder a datos reales

    @MockitoBean

    private RepartidorService service;

    //ObjectMapper convierte los datos de json a texto y viceversa

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Testing Controller 1 - Obtener Todo")
    void testGetAll() throws Exception {
        // 1.- simular con mockito un dato fake
        when(service.listar()).thenReturn(List.of(new Repartidor(1,"Luis","Uribe","12.456.987-8","Mercedez",1200,"HG-54-87")));
        // 2.- ejecutar una peticion get falsa
        mockMvc.perform(get("/api/Repartidores/listar/repartidores"))
                //lo que esperamos en esa peticion
                .andExpect(status().isOk()) //codigo 200
                //4.- verificacion que el primer elemento de JSON sea el juego TETRIS
                .andExpect(jsonPath("$[0].nombre").value("Luis"));

    }


    // POST guardar objeto
    @Test
    @DisplayName("Testing  controller 2 - Guardar POST")
    void testPost() throws Exception {

        Repartidor c = new Repartidor(2,"Gustavo","Santana","13.564.354-8","Mercedez",1200,"HG-54-87");

        //Simular Con mockito el guardar este videojuego y me devuelve uno con el id ya asignado
        when(service.guardar(c)).thenReturn(new Repartidor(2,"Gustavo","Santana","13.564.354-8","Mercedez",1200,"HG-54-87"));
        //Url del dónde se va a guardar la información
        mockMvc.perform(post("/api/Repartidores/guardar/repartidores")
                        .contentType("application/json")// indicar que el contenido es JSON
                        .content(mapper.writeValueAsString(c)))//convertimos el obejto JSON
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Gustavo"));
    }



}
