package com.perfulandia.carritoservice.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import com.perfulandia.carritoservice.assembler.CarritoAssembler;
import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.model.Usuario;
import com.perfulandia.carritoservice.model.Logistica;
import com.perfulandia.carritoservice.model.Producto;
import com.perfulandia.carritoservice.model.Pedido;
import com.perfulandia.carritoservice.service.CarritoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carrito")
@CrossOrigin("*")
public class CarritoController {

    private final CarritoService service;
    private final CarritoAssembler assembler;
    private final RestTemplate restTemplate;

    public CarritoController(CarritoService service, CarritoAssembler assembler, RestTemplate restTemplate) {
        this.service = service;
        this.assembler = assembler;
        this.restTemplate = restTemplate;
    }

    @Operation(summary = "Listar carritos", description = "Devuelve una lista de todos los carritos con enlaces HATEOAS")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
        @ApiResponse(responseCode = "400", description = "Error en la consulta")
    })
    @GetMapping("/listar/carritos")
    public CollectionModel<EntityModel<Carrito>> listar() {
        List<EntityModel<Carrito>> carritos = service.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(carritos,
                linkTo(methodOn(CarritoController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Guardar carrito", description = "Agrega un nuevo carrito al sistema")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Carrito agregado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Error en la consulta")
    })
    @PostMapping("/guardar/carrito")
    public EntityModel<Carrito> guardar(@RequestBody Carrito c) {
        Carrito carrito = service.guardar(c);
        return assembler.toModel(carrito);
    }

    @Operation(summary = "Eliminar carrito", description = "Elimina un carrito por su ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Carrito eliminado correctamente"),
        @ApiResponse(responseCode = "400", description = "Error en la consulta")
    })
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @Operation(summary = "Buscar carrito", description = "Busca un carrito por su ID con HATEOAS")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Carrito encontrado"),
        @ApiResponse(responseCode = "400", description = "Carrito no encontrado")
    })
    @GetMapping("/buscar/{id}")
    public EntityModel<Carrito> buscar(@PathVariable Long id) {
        Carrito carrito = restTemplate.getForObject("http://localhost:8085/api/Carrito/" + id, Carrito.class);
        return assembler.toModel(carrito);
    }

    @GetMapping("/usuario/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id) {
        return restTemplate.getForObject("http://localhost:8081/api/usuarios/" + id, Usuario.class);
    }

    @GetMapping("/producto/{id}")
    public Producto obtenerProducto(@PathVariable long id) {
        return restTemplate.getForObject("http://localhost:8082/api/Producto/" + id, Producto.class);
    }

    @GetMapping("/logistica/{id}")
    public Logistica obtenerLogistica(@PathVariable Long id) {
        return restTemplate.getForObject("http://localhost:8083/api/Repartidores/" + id, Logistica.class);
    }

    @GetMapping("/pedido/{id}")
    public Pedido obtenerPedido(@PathVariable int id) {
        return restTemplate.getForObject("http://localhost:8089/api/pfl/" + id, Pedido.class);
    }
}
