package com.perfulandia.productservice.service;

import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.repository.ProductoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//Librerías para usar mockito
import org.mockito.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*; //Mocks Simular inserciones, datos de listas etc.

public class ProductoServiceTest {
    //Creando una instancia de Mocks=Simular para poder iyectarlas donde sea necesario
    @InjectMocks
    private ProductoService service;
    //Creando un mock del repositorio //objeto simulado
    @Mock
    private ProductoRepository repo;

    //Constructor para inicializar test antes de cada prueba
    public ProductoServiceTest(){

        //Abre e inializa los mocks anotados con @Mock y @InjectMocks
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Testing 1 - FindAll Service")
    void testFindAll(){
        //Simular la creación de un objeto de usuario
        when(repo.findAll()).thenReturn(List.of(new Producto(1,"CR7",46900,15)));
        //Llamar al metodo de servicio que sera probado
        List<Producto> resultado =  service.listar();
        //Verificacion
        assertEquals(1, resultado.size());
    }

    @Test
    @DisplayName("Testing 2 - Guardar service")
    void testGuardar(){
        Producto producto = new Producto(1,"CR7",46900,15);
        when(repo.save(producto)).thenReturn(new Producto(1,"CR7",46900,15));
        Producto resultado =  service.guardar(producto);
        assertEquals(1, resultado.getId());
    }
}
