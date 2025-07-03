package com.perfulandia.pedidos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;


@Entity // Indica que esta clase representa una tabla en la base de datos.
@Data // Lombok genera automáticamente cosas repetitivas como get y set
@NoArgsConstructor // crea constructores sin parametros util para crear un contructor vacio para crear objetos
@AllArgsConstructor // Crea un constructor con todos los campos como parámetros
@Builder // Genera Constructores de manera Flexible


public class   pedidoModel {
    @Schema(description = "ID autoguardado con IDENTITY", example ="1")
    @Id // indica que campo necesita una clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera automaticamente el valor del campo, como una ID
    // se utilizan en conjunto con el @Id
    private int id;

    @Schema(description = "Id del Usuario correspondiente al pedido", example ="21455979k")
    private String usuarioId;

    @Schema(description = "Nombre del producto correspondiente al pedido", example ="Antonio Banderas")
    private String producto;

    @Schema(description = "Descripcion del pedido", example ="Perfume cítrico")
    private String descripcion;

    @Schema(description = "Estado del pedido", example ="En camino")
    private String estado;

}