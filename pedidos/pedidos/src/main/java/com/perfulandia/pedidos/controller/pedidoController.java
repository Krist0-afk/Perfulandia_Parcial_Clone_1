package com.perfulandia.pedidos.controller;

import com.perfulandia.pedidos.model.pedidoModel;
import com.perfulandia.pedidos.service.pedidoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Pedido",description = "Operaciones de un CRU para la API de pedidos")
@RestController // Esta clase va a manejar solicitudes web y va a responder con datos
@RequestMapping("/api/pedidos") // Sirve para definir la ruta base (URL raíz) de todos los métodos de esa clase.

public class pedidoController { //.

    private final pedidoService pedidoService;

    public pedidoController(pedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }


    @Operation(summary = "Mostrar pedidos", description = "Muestra todos los pedidos en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Muestra de pedidos de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    //listar
    @GetMapping("/listar/pedidos") // se usa para manejar solicitudes
    public List<pedidoModel> listar(){
        return pedidoService.listar();
    }


    @Operation(summary = "Guardar pedido", description = "Agrega un pedido en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Muestra de pedidos de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @PostMapping("/guardar/pedido") // Se usa típicamente para enviar datos al servidor,
    public pedidoModel guardar(@RequestBody pedidoModel pedidoModel){
        return pedidoService.guardar(pedidoModel);
    }


    @Operation(summary = "Buscar pedido por id", description = "Muestra un pedido dependiendo de su id en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Muestra de pedido de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @GetMapping("/buscar/pedido/{id}")
    public pedidoModel buscar(@PathVariable Long id){
        return pedidoService.buscar(id);
    }


    @Operation(summary = "Eliminar pedido por id", description = "Elimina un pedido dependiendo de su id en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedido borrado de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @DeleteMapping("/eliminar/pedido/{id}") // Se usa para eliminar datos desde el servidor.
    public void eliminar(@PathVariable Long id){
        pedidoService.eliminar(id);
    }

}
