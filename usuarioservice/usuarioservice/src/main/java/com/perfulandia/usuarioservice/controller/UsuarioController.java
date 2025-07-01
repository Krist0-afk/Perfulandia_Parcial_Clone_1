package com.perfulandia.usuarioservice.controller;
import com.perfulandia.usuarioservice.model.Usuario;
import com.perfulandia.usuarioservice.service.UsuarioService;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name="Usuario",description = "Operaciones de un CRU para la API  de usuarios")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    //Constructor para poder consumir la interfaz.
    public UsuarioController(UsuarioService service){
        this.service=service;
    }

    @Operation(summary = "Crear un nuevo usuario", description = "Agrega un nuevo usuario a la base de datos ")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Usuario creado de forma exitosa"),
            @ApiResponse(responseCode = "400",description = "Fallo en la consulta")
    })
    @GetMapping
    public List<Usuario> getAll(){return service.listar();
    }

    @Operation(summary = "Crear un nuevo Videojuego", description = "Agrega un nuevo videojuego a la base de datos ")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "juego creado de forma exitosa"),
            @ApiResponse(responseCode = "400",description = "Fallo en la consulta")
    })


    @GetMapping("/listar/usuario")
    public List<Usuario> listar(){
        return service.listar();
    }

    @PostMapping("/guardar/usuario")
    public Usuario guardar(@RequestBody Usuario usuario){
        return service.guardar(usuario);
    }

    @GetMapping("/buscar/usuario{id}")
    public Usuario buscar(@PathVariable long id){
        return service.buscar(id);
    }

    @DeleteMapping("/eliminar/usuario/{id}")
    public void eliminar(@PathVariable long id){
        service.eliminar(id);
    }



}
