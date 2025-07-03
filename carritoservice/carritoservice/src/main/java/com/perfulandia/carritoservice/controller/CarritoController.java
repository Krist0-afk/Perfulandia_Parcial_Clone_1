package com.perfulandia.carritoservice.controller;

// HATEOAS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn; // permite llamar métodos del controlador
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo; // permite crear enlaces hateoas

import com.perfulandia.carritoservice.assembler.CarritoAssembler;

import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.model.Usuario;
import com.perfulandia.carritoservice.model.Logistica;
import com.perfulandia.carritoservice.model.Producto;
import com.perfulandia.carritoservice.model.Pedido;

import com.perfulandia.carritoservice.service.CarritoService;

import org.springframework.hateoas.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController // Esta clase va a manejar solicitudes web y va a responder con datos
@RequestMapping("/api/carrito") // Define la ruta base de todos los métodos
@CrossOrigin("*") // permite llamadas externas desde otros dominios
public class CarritoController {

    private final CarritoService service;
    private final CarritoAssembler assembler;
    private final RestTemplate restTemplate;

    public CarritoController(CarritoService service, CarritoAssembler assembler, RestTemplate restTemplate) {
        this.service = service;
        this.assembler = assembler;
        this.restTemplate = restTemplate;
    }

    // listar
    @GetMapping("/listar/carritos") // se usa para mostrar todos los carritos
    public CollectionModel<EntityModel<Carrito>> listar() {
        List<EntityModel<Carrito>> carritos = service.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(carritos,
                linkTo(methodOn(CarritoController.class).listar()).withSelfRel());
    }

    // guardar
    @PostMapping("/guardar/carrito") // se usa para agregar un producto al carrito
    public EntityModel<Carrito> guardar(@RequestBody Carrito c) {
        Carrito carrito = service.guardar(c);
        return assembler.toModel(carrito);
    }

    // eliminar
    @DeleteMapping("/eliminar/{id}") // se usa para eliminar un producto del carrito por su ID
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    // buscar por ID
    @GetMapping("/buscar/{id}") // se usa para buscar un carrito por su ID
    public EntityModel<Carrito> buscar(@PathVariable Long id) {
        Carrito carrito = restTemplate.getForObject("http://localhost:8085/api/Carrito/" + id, Carrito.class);
        return assembler.toModel(carrito);
    }

    // consumir microservicio usuario
    @GetMapping("/usuario/{id}") // obtiene un usuario desde otro microservicio
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return restTemplate.getForObject("http://localhost:8081/api/usuarios/" + id, Usuario.class);
    }

    // consumir microservicio producto
    @GetMapping("/producto/{id}") // obtiene un producto desde otro microservicio
    public Producto obtenerProducto(@PathVariable long id) {
        return restTemplate.getForObject("http://localhost:8082/api/Producto/" + id, Producto.class);
    }

    // consumir microservicio logística
    @GetMapping("/logistica/{id}") // obtiene un repartidor desde otro microservicio
    public Logistica obtenerLogistica(@PathVariable Long id) {
        return restTemplate.getForObject("http://localhost:8083/api/Repartidores/" + id, Logistica.class);
    }

    // consumir microservicio pedido
    @GetMapping("/pedido/{id}") // obtiene un pedido desde otro microservicio
    public Pedido obtenerPedido(@PathVariable int id) {
        return restTemplate.getForObject("http://localhost:8089/api/pfl/" + id, Pedido.class);
    }
}
