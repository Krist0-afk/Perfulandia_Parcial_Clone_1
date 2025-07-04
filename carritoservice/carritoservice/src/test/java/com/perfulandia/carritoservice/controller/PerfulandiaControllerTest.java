//URL: http://localhost:8085/swagger-ui/index.html#/
package com.perfulandia.carritoservice.controller;

import com.perfulandia.carritoservice.controller.CarritoController;
import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.service.CarritoService;

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

//Anotacion que indica que solo probara el VideojuegoController
@WebMvcTest(CarritoController.class)
public class PerfulandiaControllerTest {
    //Crar http para poder realizar pruebas unitarias injectar

    @Autowired

    private MockMvc mockMvc;

    //Simulacion del servicio para evitar acceder a datos reales

    @MockitoBean

    private CarritoService service;

    //ObjectMapper convierte los datos de json a texto y viceversa

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Testing Controller 1 - Obtener Todo")
    void testGetAll() throws Exception {
    // 1.- simular con mockito un dato fake
        when(service.listar()).thenReturn(List.of(new Carrito(1,"POLO",90000,"EDP")));
    // 2.- ejecutar una peticion get falsa
        mockMvc.perform(get("/api/carrito/mostrar/carrito"))
                //lo que esperamos en esa peticion
                .andExpect(status().isOk()) //codigo 200
                //4.- verificacion que el primer elemento de JSON sea el juego TETRIS
                .andExpect(jsonPath("$[0].nombre").value("POLO"));

    }


    // POST guardar objeto
    @Test
    @DisplayName("Testing  controller 2 - Guardar POST")
    void testPost() throws Exception {

    Carrito c = new Carrito(1,"POLO",90000,"EDP");

    //Simular Con mockito el guardar este videojuego y me devuelve uno con el id ya asignado
        when(service.guardar(c)).thenReturn(new Carrito(1,"POLO",90000,"EDP"));
        //Url del dónde se va ha guardar la información
        mockMvc.perform(post("/api/carrito/agregar/producto")
                        .contentType("application/json")// indicar que el contenido es JSON
                        .content(mapper.writeValueAsString(c)))//convertimos el obejto JSON
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("POLO"));
    }



}
