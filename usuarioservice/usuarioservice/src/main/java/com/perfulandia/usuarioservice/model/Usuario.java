package com.perfulandia.usuarioservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder //Crear objetos de manera flexible = Constructor Flex
public class Usuario {
    @Schema(description = "ID autoguardado con IDENTITY", example ="1")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Schema(description = "Nombre del usuario", example ="Pepe")
    private String nombre;

    @Schema(description = "Correo del usuario", example ="Pepe@duocuc.cl")
    private String correo;

    @Schema(description = "Rol del usuario", example ="Usuario")
    private String rol; // ADMIN, GERENTE, Usuario
}
