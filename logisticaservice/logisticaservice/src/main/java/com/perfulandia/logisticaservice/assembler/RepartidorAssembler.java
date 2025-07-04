package com.perfulandia.logisticaservice.assembler;
import com.perfulandia.logisticaservice.model.Repartidor;
//importacion de Repartidor controller
import com.perfulandia.logisticaservice.controller.RepartidorController;

import org.springframework.hateoas.EntityModel; //Rpresentando un recurso con enlaces
import org.springframework.hateoas.server.RepresentationModelAssembler;//convierte un modelo en hateoas
import org.springframework.stereotype.Component;//Marca como un componente de spring
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component // Declara esta clase como uno //Clase que transforma un Producto en un entityModel
public class RepartidorAssembler implements RepresentationModelAssembler<Repartidor, EntityModel<Repartidor>> {

    @Override //convirtiendo el modelo en un entityModel con enlaces
    public EntityModel<Repartidor> toModel(Repartidor repartidor) {
        return EntityModel.of(repartidor,
                //Enlace al recurso actual (selft)

                linkTo(methodOn(RepartidorController.class).buscarConHateoas(repartidor.getId())).withSelfRel(),
                // Enlace al listado general
                linkTo(methodOn(RepartidorController.class).listar()).withRel("repartidores"));
    }
}
