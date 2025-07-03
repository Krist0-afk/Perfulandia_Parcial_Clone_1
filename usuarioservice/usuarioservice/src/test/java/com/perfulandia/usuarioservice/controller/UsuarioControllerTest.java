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

//Anotación que indica que solo probará el videjuegoController
@WebMvcTest(UsuarioControllerTest.class)
public class UsuarioControllerTest {
    //Crear hhtp pra poder realizar pruebas unitarias injectar
    @Autowired
    private MockMvc mockMvc;

    //simulacion del servicio para evitar acceder a datos reales
    @MockitoBean
    private UsuarioService service;


    //ObjectMapper comvierte los datos de json a texto y vise versa
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Testing Controller 1 - Mostrar Usuarios")
    void testGetAll() throws Exception{
        when(service.listar()).thenReturn(List.of(new Usuario(1,"Pepe","pepe@duocuc.cl","estudiante")));
        //2.-Ejecutar una peticion get falsa
        mockMvc.perform(get("/api/usuarios/mostrar/usuarios"))
                //lo que esperamos en esa peticion
                .andExpect(status().isOk())//codigo 200
                //4.- verificacion que el primer elemento JSON sea el usuario Pepe
                .andExpect(jsonPath("$[0].nombre").value("Pepe"));
    }

    //POST
    @Test
    @DisplayName("Testing controller 2-Guardar POST")
    void testPost() throws Exception {
        Usuario usuario = new Usuario(1,"Mario","mario@duocuc.cl","Usuario");
        //2 Simular con mockito el guardar este usuario y me devuelve uno con el id ya asignado
        when(service.guardar(any())).thenReturn(new Usuario(1,"Mario","mario@duocuc.cl","Usuario"));
        //3
        mockMvc.perform(post("/api/usuarios")
                        .contentType("application/json")// indicar que el contenido es JSON
                        .content(mapper.writeValueAsString(usuario)))//convertimos el obejto JSON
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Mario"));
    }

}

