package com.perfulandia.pedidos.controller;

import com.perfulandia.pedidos.model.pedidoModel;
import com.perfulandia.pedidos.service.pedidoService;
import com.perfulandia.pedidos.Assembler.PedidoAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/pfl/")
public class pedidoController {

    private final pedidoService pedidoService;
    private final PedidoAssembler assembler;

    public pedidoController(pedidoService pedidoService, PedidoAssembler assembler) {
        this.pedidoService = pedidoService;
        this.assembler = assembler;
    }

    @GetMapping("/lista/pedidos")
    public CollectionModel<EntityModel<pedidoModel>> listar() {
        List<EntityModel<pedidoModel>> pedidos = pedidoService.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(pedidos,
                linkTo(methodOn(pedidoController.class).listar()).withSelfRel());
    }

    @PostMapping("/guardar/pedido")
    public EntityModel<pedidoModel> guardar(@RequestBody pedidoModel pedido) {
        return assembler.toModel(pedidoService.guardar(pedido));
    }

    @GetMapping("/buscar/pedido/{id}")
    public EntityModel<pedidoModel> buscar(@PathVariable Long id) {
        return assembler.toModel(pedidoService.buscar(id));
    }

    @DeleteMapping("/eliminar/pedido/{id}")
    public void eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
    }
}
