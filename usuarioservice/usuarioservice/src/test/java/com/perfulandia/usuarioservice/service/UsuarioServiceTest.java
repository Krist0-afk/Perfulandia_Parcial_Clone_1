package com.perfulandia.usuarioservice.service;

import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.repository.UsuarioRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//Librerías para usar mockito
import org.mockito.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*; //Mocks Simular inserciones, datos de listas etc.

public class UsuarioServiceTest {
    //Creando una instancia de Mocks=Simular para poder iyectarlas donde sea necesario
    @InjectMocks
    private UsuarioService service;
    //Creando un mock del repositorio //objeto simulado
    @Mock
    private UsuarioRepository repo;

    //Constructor para inicializar test antes de cada prueba
    public UsuarioServiceTest(){

        //Abre e inializa los mocks anotados con @Mock y @InjectMocks
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Testing 1 - FindAll Service")
    void testFindAll(){
        //Simular la creación de un objeto de usuario
        when(repo.findAll()).thenReturn(List.of(new Usuario(1,"Pepe","pepe@duocuc.cl","Usuario")));
        //Llamar al metodo de servicio que sera probado
        List<Usuario> resultado =  service.listar();
        //Verificacion
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Testing 2 - Guardar service")
    void testGuardar(){
        Usuario usuario = new Usuario(1,"Mario","mario@duocuc.cl","Usuario");
        when(repo.save(usuario)).thenReturn(new Usuario(1,"Mario","mario@duocuc.cl","Usuario"));
        Usuario resultado =  service.guardar(usuario);
        assertEquals(1, resultado.getId());
    }
}
