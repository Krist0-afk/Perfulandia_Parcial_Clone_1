package com.perfulandia.pedidos.service;

import com.perfulandia.pedidos.model.pedidoModel;
import com.perfulandia.pedidos.repository.pedidoInterface;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//Librerías para usar mockito
import org.mockito.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*; //Mocks Simular inserciones, datos de listas etc.

public class PedidoServiceTest {
    //Creando una instancia de Mocks=Simular para poder iyectarlas donde sea necesario
    @InjectMocks
    private pedidoService service;
    //Creando un mock del repositorio //objeto simulado
    @Mock
    private pedidoInterface repo;

    //Constructor para inicializar test antes de cada prueba
    public PedidoServiceTest(){

        //Abre e inializa los mocks anotados con @Mock y @InjectMocks
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Testing 1 - FindAll Service")
    void testFindAll(){
        //Simular la creación de un objeto de usuario
        when(repo.findAll()).thenReturn(List.of(new pedidoModel(1,"21466878-k","CR7","Perfume cítrico","En camino")));
        //Llamar al metodo de servicio que sera probado
        List<pedidoModel> resultado =  service.listar();
        //Verificacion
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Testing 2 - Guardar service")
    void testGuardar(){
        pedidoModel pedido = new pedidoModel(1,"21466878-k","CR7","Perfume cítrico","En camino");
        when(repo.save(pedido)).thenReturn(new pedidoModel(1,"21466878-k","CR7","Perfume cítrico","En camino"));
        pedidoModel resultado =  service.guardar(pedido);
        assertEquals(1, resultado.getId());
    }
}
