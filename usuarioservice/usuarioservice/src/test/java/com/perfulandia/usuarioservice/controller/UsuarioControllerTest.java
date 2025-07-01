package com.perfulandia.usuarioservice.controller;

import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.service.UsuarioService;

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

@WebMvcTest(UsuarioControllerTest.class)
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mockmvc;

    @MockitoBean
    private UsuarioService service;

    private final ObjectMapper mapper=new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Testing Controller 1 - Mostrar Usuarios")
    void testgetAll() throws Exception{
        when(service.listar()).thenReturn(List.of(new Usuario(1,"Pepe","pepe@duocuc.cl","estudiante")));
        //2.-Ejecutar una peticion get falsa
        mockMvc.perform(get("/api/usuarios/mostrar/usuarios"))
                //lo que esperamos en esa peticion
                .andExpect(status().isOk())//codigo 200
                //4.- verificacion que el primer elemento JSON sea el usuario Pepe
                .andExpect(jsonPath("$[0].nombre").value("Pepe"));
    }

    @Test
    @DisplayName("Testing Controller 1 - Guardar Usuarios")
    void testGetAll() throws Exception{
        when(service.listar()).thenReturn(List.of(new Usuario(1,"Pepe","pepe@duocuc.cl","estudiante")));
        //2.-Ejecutar una peticion get falsa
        mockMvc.perform(get("/api/usuarios/mostrar/usuarios"))
                //lo que esperamos en esa peticion
                .andExpect(status().isOk())//codigo 200
                //4.- verificacion que el primer elemento JSON sea el usuario Pepe
                .andExpect(jsonPath("$[0].nombre").value("Pepe"));
    }

}

