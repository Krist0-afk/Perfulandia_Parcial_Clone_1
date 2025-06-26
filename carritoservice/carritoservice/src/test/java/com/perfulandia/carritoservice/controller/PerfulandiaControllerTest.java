package com.perfulandia.carritoservice.controller;

import com.perfulandia.carritoservice.controller.CarritoController;
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

}
