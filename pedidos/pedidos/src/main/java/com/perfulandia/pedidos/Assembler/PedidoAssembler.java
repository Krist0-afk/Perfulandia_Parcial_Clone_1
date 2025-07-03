package com.perfulandia.pedidos.Assembler;

import com.perfulandia.pedidos.controller.pedidoController;
import com.perfulandia.pedidos.model.pedidoModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class PedidoAssembler implements RepresentationModelAssembler<pedidoModel, EntityModel<pedidoModel>> {

    @Override
    public EntityModel<pedidoModel> toModel(pedidoModel pedido) {
        return EntityModel.of(pedido,
                linkTo(methodOn(pedidoController.class).buscar((long) pedido.getId())).withSelfRel(),
                linkTo(methodOn(pedidoController.class).listar()).withRel("pedidos"));
    }
}
