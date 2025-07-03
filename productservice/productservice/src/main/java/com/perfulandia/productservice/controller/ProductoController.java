package com.perfulandia.productservice.controller;
import com.perfulandia.productservice.model.Usuario;
import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.service.ProductoService;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//Nuevas importaciones DTO conexión al MS usuario
import org.springframework.web.client.RestTemplate;
//Para hacer peticiones HTTP a otros microservicios.


@Tag(name="Producto",description = "Operaciones de un CRU para la API de productos")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;
    //Constructor para poder consumir la interfaz.
    public ProductoController(ProductoService service) {
        this.service = service;
    }


    @Operation(summary = "Mostrar productos", description = "Muestra todos los productos en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Muestra de productos de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @GetMapping("/listar/productos")
    public List<Producto> listar() {return service.listar();}

    //guardar
    @Operation(summary = "Agregar un producto", description = "Agrega un producto en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto agregado de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @PostMapping("/guardar/producto")
    public Producto guardar(@RequestBody Producto producto) {return service.guardar(producto);}


    @Operation(summary = "Buscar producto por id", description = "Muestra un producto dependiendo de su id en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca del producto de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @GetMapping("/buscar/producto/{id}")
    public Producto buscar(@PathVariable long id) {return service.buscar(id);}


    @Operation(summary = "Eliminar producto por id", description = "Elimina un producto dependiendo de su id en la base de datos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Producto borrado de forma exitosa"),
            @ApiResponse(responseCode = "400", description = "Fallo en la consulta")
    })
    @DeleteMapping("/eliminar/producto/{id}")
    public void eliminar(@PathVariable long id) {service.eliminar(id);}

}
