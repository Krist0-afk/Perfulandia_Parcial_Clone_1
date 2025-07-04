package com.perfulandia.productservice.controller;

import com.perfulandia.productservice.Assembler.ProductoAssembler;
import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.model.Usuario;
import com.perfulandia.productservice.service.ProductoService;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name="Producto",description = "Operaciones de un CRU para la API de productos")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;
    private final ProductoAssembler assembler;
    private final RestTemplate restTemplate;

    public ProductoController(ProductoService service, ProductoAssembler assembler, RestTemplate restTemplate) {
        this.service = service;
        this.assembler = assembler;
        this.restTemplate = restTemplate;
    }

    @Operation(summary = "Mostrar productos", description = "Muestra todos los productos en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Muestra de productos de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @GetMapping("/listar/producto")
    public CollectionModel<EntityModel<Producto>> listar() {
        List<EntityModel<Producto>> productos = service.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(productos,
                linkTo(methodOn(ProductoController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Agregar un producto", description = "Agrega un producto en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto agregado de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @PostMapping("/guardar/producto")
    public Producto guardar(@RequestBody Producto producto) {
        return service.guardar(producto);
    }

    @Operation(summary = "Buscar producto por id", description = "Muestra un producto dependiendo de su id en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto encontrado de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @GetMapping("/buscar/producto/{id}")
    public EntityModel<Producto> buscar(@PathVariable long id) {
        Producto producto = service.bucarPorId(id);
        return assembler.toModel(producto);
    }

    @Operation(summary = "Eliminar producto por id", description = "Elimina un producto dependiendo de su id en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto borrado de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @DeleteMapping("/eliminar/producto/{id}")
    public void eliminar(@PathVariable long id) {
        service.eliminar(id);
    }

    @Operation(summary = "Obtener datos del usuario asociado", description = "Obtiene un usuario por ID desde otro microservicio")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario encontrado correctamente"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @GetMapping("/usuario/{id}")
    public Usuario obtenerUsuario(@PathVariable long id) {
        return restTemplate.getForObject("http://localhost:8081/api/usuarios/" + id, Usuario.class);
    }
}
