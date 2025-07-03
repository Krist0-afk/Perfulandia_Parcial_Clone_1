package com.perfulandia.productservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Producto {
    @Schema(description = "ID autoguardado con IDENTITY", example ="1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del producto", example ="CR7")
    private String nombre;

    @Schema(description = "Precio del producto", example ="12990")
    private double precio;

    @Schema(description = "Stock disponible del producto", example ="15")
    private int stock;

}
