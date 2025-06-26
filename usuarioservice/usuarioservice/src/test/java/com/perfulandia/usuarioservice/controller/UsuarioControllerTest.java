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


    @Test
    @DisplayName("Testing Controller 1 - Mostrar Usuarios")
    void testGetAll() throws Exception{

    }
}
