package com.perfulandia.carritoservice.controller;

import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.service.CarritoService;
import com.perfulandia.carritoservice.model.Usuario;
import com.perfulandia.carritoservice.model.Logistica;
import com.perfulandia.carritoservice.model.Producto;
import com.perfulandia.carritoservice.model.Pedido;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.client.RestTemplate;


@RestController// Esta clase va a manejar solicitudes web y va a responder con datos
@RequestMapping("/api/carrito") // Sirve para definir la ruta base (URL raíz) de todos los métodos de esa clase.
public class CarritoController {
    private final RestTemplate restTemplate;


    private final CarritoService service;
    public CarritoController(CarritoService service, RestTemplate restTemplate) {
        this.service = service;
        this.restTemplate = restTemplate;
    }

    @Operation(summary = "obtener todos los perfumes", description = "Devuelve una lista de todos los perfumes almacenados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Consulta exitosa"), // apiresponses de respuesta
            @ApiResponse(responseCode = "400", description = "Error en la consulta") // apiresponses de negativa
    })

    @GetMapping("/mostrar/carrito")// se usa para mostrar el carrito
    public List<Carrito> listar(){
        return service.listar();
    }

    @Operation(summary = "Agregar un nuevo perfume ", description = "Agrega un nuevo producto a la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto agregado exitosamente"), // apiresponses de respuesta
            @ApiResponse(responseCode = "400", description = "Error en la consulta") // apiresponses de negativa
    })

    @PostMapping("/agregar/producto")// Se usa para guardar pedidos al servidor.
    public Carrito guardar(@RequestBody Carrito carrito){
        return service.guardar(carrito);
    }

    @DeleteMapping("/eliminar/producto/{id}")// Se usa para eliminar datos dependiendo de la id.
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    //Nuevo método
    // llama a otro microservicio con el restemplate
    @GetMapping("/usuario/{id}")
    public Usuario obtenerUsuario(@PathVariable long id){
        return restTemplate.getForObject("http://localhost:8081/api/usuarios/"+id,Usuario.class);
    }
    //Nuevo método
    @GetMapping("/Producto/{id}")
    public Producto obtenerproducto(@PathVariable long id){
        return restTemplate.getForObject("http://localhost:8082/api/Producto/"+id,Producto.class);
    }

    //Nuevo método
    @GetMapping("/Logistica/{id}")
    public Logistica ObtenerLogistica(@PathVariable long id){
        return restTemplate.getForObject("http://localhost:8083/api/Repartidores/"+id,Logistica.class);
    }
    //Nuevo método
    @GetMapping("/Pedido/{id}")
    public Pedido ObtenerPedido(@PathVariable int id){
        return restTemplate.getForObject("http://localhost:8089/api/pfl/"+id,Pedido.class);
    }
    //Nuevo método
    @GetMapping("/Carrito/{id}")
    public Carrito ObtenerCarrito(@PathVariable int id){
        return restTemplate.getForObject("http://localhost:8085/api/Carrito/"+id,Carrito.class);
    }


}
