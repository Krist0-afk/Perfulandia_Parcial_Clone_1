package com.perfulandia.pedidos.controller;

import com.perfulandia.pedidos.model.pedidoModel;
import com.perfulandia.pedidos.service.pedidoService;
import com.perfulandia.pedidos.Assembler.PedidoAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Tag(name="Pedido",description = "Operaciones de un CRU para la API de pedidos")
@RestController
@RequestMapping("/api/pfl/")
public class pedidoController {

    private final pedidoService pedidoService;
    private final PedidoAssembler assembler;

    public pedidoController(pedidoService pedidoService, PedidoAssembler assembler) {
        this.pedidoService = pedidoService;
        this.assembler = assembler;
    }

    @Operation(summary = "Mostrar pedidos", description = "Muestra todos los pedidos en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Muestra de pedidos de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @GetMapping("/listar/pedidos")
    public CollectionModel<EntityModel<pedidoModel>> listar() {
        List<EntityModel<pedidoModel>> pedidos = pedidoService.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(pedidos,
                linkTo(methodOn(pedidoController.class).listar()).withSelfRel());
    }

    @Operation(summary = "Guardar pedido", description = "Agrega un pedido en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido agregado de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @PostMapping("/guardar/pedido")
    public EntityModel<pedidoModel> guardar(@RequestBody pedidoModel pedido) {
        return assembler.toModel(pedidoService.guardar(pedido));
    }

    @Operation(summary = "Buscar pedido por id", description = "Muestra un pedido dependiendo de su id en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido encontrado de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @GetMapping("/buscar/pedido/{id}")
    public EntityModel<pedidoModel> buscar(@PathVariable Long id) {
        return assembler.toModel(pedidoService.buscar(id));
    }

    @Operation(summary = "Eliminar pedido por id", description = "Elimina un pedido dependiendo de su id en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido borrado de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @DeleteMapping("/eliminar/pedido/{id}")
    public void eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
    }
}
