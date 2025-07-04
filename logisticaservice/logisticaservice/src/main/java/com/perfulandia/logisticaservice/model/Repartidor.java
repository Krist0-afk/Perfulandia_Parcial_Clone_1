package com.perfulandia.logisticaservice.model;
import jakarta.persistence.*;
import lombok.*;

@Entity// Indica que esta clase representa una tabla en la base de datos.
@Data // Lombok genera automáticamente cosas repetitivas como get y set
@NoArgsConstructor // crea constructores sin parametros util para crear un contructor vacio para crear objetos
@AllArgsConstructor // Crea un constructor con todos los campos como parámetros
@Builder // Genera Constructores de manera Flexible
public class Repartidor {
    @Id // indica que campo necesita una clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)// genera automaticamente el valor del campo, como una ID
    // se utilizan en conjunto con el @Id
    private Long id;
    private String nombre;
    private String apellido;
    private String rut;
    private String vehiculo;
    private long capacidad;
    private String patente;
}