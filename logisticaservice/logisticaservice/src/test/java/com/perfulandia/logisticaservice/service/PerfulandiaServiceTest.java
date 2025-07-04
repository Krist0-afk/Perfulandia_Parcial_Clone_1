//URL: http://localhost:8083/swagger-ui/index.html#/
package com.perfulandia.logisticaservice.service;


import com.perfulandia.logisticaservice.model.Repartidor;
import com.perfulandia.logisticaservice.repository.RepartidorRepository;

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
    private RepartidorService service;
    //Creando un mock del repositorio //objeto simulado
    @Mock
    private RepartidorRepository repo;

    //Constructor para inicializar test antes de cada prueba
    public PerfulandiaServiceTest() {

        //abre e inicializa los mocks anotadoscon @Mock y @InjectMocks
        MockitoAnnotations.openMocks(this);


    }

    @Test
    @DisplayName("Testing 1 - FindAll Service")
    void testFindAll() {
        //Simular la creacion de un objeto de perfume
        when(repo.findAll()).thenReturn(List.of(new Repartidor(1, "Luis", "Uribe", "12.456.987-8", "Mercedez", 1200, "HG-54-87")));
        List<Repartidor> resultado = service.listar();
        //verificacion
        assertEquals(resultado.size(), 1);
    }

    @Test
    @DisplayName("Testing 2 - Save Service")
    void testSave() {

        Repartidor r = new Repartidor(1, "Luis", "Uribe", "12.456.987-8", "Mercedez", 1200, "HG-54-87");
        //Simular el Guardado de un videojuego
        when(repo.save(r)).thenReturn(r);
        //Llamar al metodo de  servicio que sera probado
        Repartidor resultado = service.guardar(r);
        //Verifica que el objeto resultado no sea null.
        assertNotNull(resultado);
        //Comprueba que el nombre del objeto resultado sea exactamente "Axe".
        assertEquals("Luis", resultado.getNombre());
        //Verifica que el precio del objeto resultado sea exactamente 12000.
        assertEquals(1200, resultado.getCapacidad());
    }


}

