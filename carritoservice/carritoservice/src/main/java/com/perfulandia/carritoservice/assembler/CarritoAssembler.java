package com.perfulandia.carritoservice.assembler;

import com.perfulandia.carritoservice.controller.CarritoController;
import com.perfulandia.carritoservice.model.Carrito;

// hateoas
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

// Esta clase convierte un Carrito a un modelo HATEOAS (EntityModel) con enlaces.
@Component // Marca la clase como componente para que Spring la detecte automáticamente
public class CarritoAssembler implements RepresentationModelAssembler<Carrito, EntityModel<Carrito>> {

    @Override
    public EntityModel<Carrito> toModel(Carrito carrito) {
        return EntityModel.of(carrito,
                // Enlace al recurso individual del carrito (self link)
                linkTo(methodOn(CarritoController.class).buscar(carrito.getId())).withSelfRel(),

                // Enlace al listado completo de carritos
                linkTo(methodOn(CarritoController.class).listar()).withRel("carritos")
        );
    }
}
