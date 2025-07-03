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

@Tag(name="Usuario",description = "Operaciones de un CRU para la API de usuarios")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;
    //Constructor para poder consumir la interfaz.
    public UsuarioController(UsuarioService service){
        this.service=service;
    }


    @Operation(summary = "Mostrar usuarios", description = "Muestra todos los usuarios en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Muestra de usuarios de forma exitosa"),
            @ApiResponse(responseCode = "400",description = "Fallo en la consulta")
    })
    @GetMapping("/listar/usuario")
    public List<Usuario> listar(){
        return service.listar();
    }


    @Operation(summary = "Agregar un usuario", description = "Agrega un usuario en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Usuario agregado de forma exitosa"),
            @ApiResponse(responseCode = "400",description = "Fallo en la consulta")
    })
    @PostMapping("/guardar/usuario")
    public Usuario guardar(@RequestBody Usuario usuario){
        return service.guardar(usuario);
    }


    @Operation(summary = "Buscar usuario por id", description = "Muestra un usuario dependiendo de su id en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200",description = "Busca de usuario de forma exitosa"),
            @ApiResponse(responseCode = "400",description = "Fallo en la consulta")
    })
    @GetMapping("/buscar/usuario/{id}")
    public Usuario buscar(@PathVariable long id){
        return service.buscar(id);
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
