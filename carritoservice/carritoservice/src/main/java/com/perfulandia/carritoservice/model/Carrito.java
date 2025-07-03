package com.perfulandia.carritoservice.model;

//importar jakarta y lombok
import jakarta.persistence.*;
import lombok.*;


@Entity // Indica que esta clase representa una tabla en la base de datos.
@Data // Lombok genera automáticamente cosas repetitivas como get y set
@NoArgsConstructor // crea constructores sin parametros util para crear un contructor vacio para crear objetos
@AllArgsConstructor // Crea un constructor con todos los campos como parámetros
@Builder // Genera Constructores de manera Flexible
public class Carrito {//
    @Id // indica que campo necesita una clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera automaticamente el valor del campo, como una ID
    // se utilizan en conjunto con el @Id
    private Long id;
    private String nombre;
    private double precio;
    private String categoria;
}
