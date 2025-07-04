//URL: http://localhost:8085/swagger-ui/index.html#/
package com.perfulandia.carritoservice.service;

import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.repository.CarritoRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//Librerías para usar mockito
import org.mockito.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*; //Mocks Simular inserciones, datos de listas etc.


public class PerfulandiaServiceTest {

    //Creando una instancia dem mocks=Simular para poder inyectar donde sea necesario
    @InjectMocks
    private CarritoService service;
    //Creando un mock del repositorio //objeto simulado
    @Mock
    private CarritoRepository repo;

    //Constructor para inicializar test antes de cada prueba
    public PerfulandiaServiceTest() {

        //abre e inicializa los mocks anotadoscon @Mock y @InjectMocks
        MockitoAnnotations.openMocks(this);


    }

    @Test
    @DisplayName("Testing 1 - FindAll Service")
    void testFindAll() {
        //Simular la creacion de un objeto de perfume
        when(repo.findAll()).thenReturn(List.of(new Carrito(1,"POLO",90000,"EDP")));
        List<Carrito> resultado = service.listar();
        //verificacion
        assertEquals(resultado.size(), 1);
    }

    @Test
    @DisplayName("Testing 2 - Save Service")
    void testSave(){

        Carrito c = new Carrito(1,"AXE",12000,"EDT");
        //Simular el Guardado de un videojuego
        when(repo.save(c)).thenReturn(c);
        //Llamar al metodo de  servicio que sera probado
        Carrito resultado = service.guardar(c);
        //Verifica que el objeto resultado no sea null.
        assertNotNull(resultado);
        //Comprueba que el nombre del objeto resultado sea exactamente "Axe".
        assertEquals("AXE", resultado.getNombre());
        //Verifica que el precio del objeto resultado sea exactamente 12000.
        assertEquals(12000, resultado.getPrecio());
    }






}
