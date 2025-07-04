package com.perfulandia.usuarioservice.controller;

import com.perfulandia.usuarioservice.assembler.UsuarioAssembler;
import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.service.UsuarioService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

@Tag(name="Usuario",description = "Operaciones de un CRU para la API de usuarios")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    private final UsuarioAssembler assembler;

    public UsuarioController(UsuarioService service, UsuarioAssembler assembler){
        this.service = service;
        this.assembler = assembler;
    }

    @Operation(summary = "Mostrar usuarios", description = "Muestra todos los usuarios en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Muestra de usuarios de forma exitosa"),
            @ApiResponse(responseCode = "400",description = "Fallo en la consulta")
    })
    @GetMapping("/listar/usuario")
    public CollectionModel<EntityModel<Usuario>> listar(){
        List<EntityModel<Usuario>> usuarios = service.listar().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(usuarios,
                linkTo(UsuarioController.class).slash("/listar/usuario").withSelfRel());
    }

    @Operation(summary = "Agregar un usuario", description = "Agrega un usuario en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Usuario agregado de forma exitosa"),
            @ApiResponse(responseCode = "400",description = "Fallo en la consulta")
    })
    @PostMapping("/guardar/usuario")
    public EntityModel<Usuario> guardar(@RequestBody Usuario usuario){
        Usuario nuevoUsuario = service.guardar(usuario);
        return assembler.toModel(nuevoUsuario);
    }

    @Operation(summary = "Buscar usuario por id", description = "Muestra un usuario dependiendo de su id en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Busqueda de usuario de forma exitosa"),
            @ApiResponse(responseCode = "400",description = "Fallo en la consulta")
    })
    @GetMapping("/buscar/usuario/{id}")
    public EntityModel<Usuario> buscar(@PathVariable long id){
        Usuario usuario = service.buscar(id);
        if (usuario == null) {
            throw new RuntimeException("Usuario no encontrado con id " + id);
        }
        return assembler.toModel(usuario);
    }

    @Operation(summary = "Eliminar usuario por id", description = "Elimina un usuario dependiendo de su id en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Usuario borrado de forma exitosa"),
            @ApiResponse(responseCode = "400",description = "Fallo en la consulta")
    })
    @DeleteMapping("/eliminar/usuario/{id}")
    public void eliminar(@PathVariable long id){
        service.eliminar(id);
    }
}
