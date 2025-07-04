package com.perfulandia.carritoservice.model;

//importar jakarta y lombok
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;


@Entity // Indica que esta clase representa una tabla en la base de datos.
@Data // Lombok genera automáticamente cosas repetitivas como get y set
@NoArgsConstructor // crea constructores sin parametros util para crear un contructor vacio para crear objetos
@AllArgsConstructor // Crea un constructor con todos los campos como parámetros
@Builder // Genera Constructores de manera Flexible
public class Carrito {//
    @Id // indica que campo necesita una clave primaria
    @Schema(description = "ID autoguardado con IDENTITY", example ="1")
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera automaticamente el valor del campo, como una ID
    // se utilizan en conjunto con el @Id

    private Long id;

    @Schema(description = "Nombre del perfume", example ="Polo")

    private String nombre;

    @Schema(description = "Precio del Producto", example ="20000")
    private double precio;

    @Schema(description = "Nombre de la categoria", example ="EDP")
    private String categoria;
}
