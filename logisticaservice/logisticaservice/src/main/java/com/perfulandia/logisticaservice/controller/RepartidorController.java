package com.perfulandia.logisticaservice.controller;
//HATEOAS
//hacer una llamada al metodo del controlador
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

//2 permite agregar links de hateoas basados en metodos
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

//3 import de assembler
import com.perfulandia.logisticaservice.assembler.RepartidorAssembler;

// librerias hateoas
import org.springframework.hateoas.*;

import java.util.stream.Collectors;


import com.perfulandia.logisticaservice.model.Repartidor;
import com.perfulandia.logisticaservice.service.RepartidorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController  // Esta clase va a manejar solicitudes web y va a responder con datos
@RequestMapping("/api/Repartidores") // Sirve para definir la ruta base (URL raíz) de todos los métodos de esa clase.

@CrossOrigin("*")// permite hacer consultas y para agregar los enlaces del hateoas
public class RepartidorController { //.
    private final RepartidorService service;
    private final RepartidorAssembler assembler;
    public RepartidorController(RepartidorService service, RepartidorAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }
    //listar
    @GetMapping("/listar/repartidores") // se usa para manejar solicitudes
    public CollectionModel<EntityModel<Repartidor>> listar() {
        // Obteniendo todos los productos, los transformamos en un entitymodel con enlaces assembler
        List<EntityModel<Repartidor>> repartidores = service.listar().stream()
                .map(assembler::toModel)//transformanndo cada producto en un modelo de entidad <Repartidor> con enlaces
                .collect(Collectors.toList());
        return CollectionModel.of(repartidores,
                linkTo(methodOn(RepartidorController.class).listar()).withSelfRel());//enlace del listado asimismo
    }
    //guardar
    @PostMapping("/guardar/repartidores") // Se usa típicamente para enviar datos al servidor,
    public EntityModel<Repartidor> guardar(@RequestBody Repartidor r) {
        Repartidor repartidor = service.guardar(r);
        return assembler.toModel(repartidor);
    }
    //buscar por id
    @GetMapping("/buscar/{id}")
    public EntityModel<Repartidor> buscarConHateoas(@PathVariable long id) {
        Repartidor repartidor = service.buscar(id);
        return assembler.toModel(repartidor);
    }
    //eliminar
    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable long id) {
        service.eliminar(id);
    }
    //buscar por capacidad
    @GetMapping("/capacidad/repartidor") // Se usa para eliminar datos desde el servidor.
    public CollectionModel<EntityModel<Repartidor>> buscarcapacidad(@RequestParam long capacidad) {
        List<EntityModel<Repartidor>> repartidores = service.buscarcapacidad(capacidad).stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(repartidores,
                linkTo(methodOn(RepartidorController.class).buscarcapacidad(capacidad)).withSelfRel());
    }


}
