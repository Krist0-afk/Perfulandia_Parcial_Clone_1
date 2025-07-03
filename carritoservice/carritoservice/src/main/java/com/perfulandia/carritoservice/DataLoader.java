package com.perfulandia.carritoservice;

import com.perfulandia.carritoservice.model.Carrito;
import com.perfulandia.carritoservice.repository.CarritoRepository;

import org.springframework.boot.CommandLineRunner; // Permite ejecutar código automáticamente al iniciar la aplicación
import org.springframework.stereotype.Component;

@Component // Marca esta clase como un componente para ser detectado por Spring
public class DataLoader implements CommandLineRunner {

    private final CarritoRepository repo;

    public DataLoader(CarritoRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        // Insertar productos de ejemplo en la base de datos para hacer pruebas
        repo.save(new Carrito(null, "Collar para perro", 10000, "Accesorios"));
        repo.save(new Carrito(null, "Pelota interactiva", 7500, "Juguetes"));
        repo.save(new Carrito(null, "Cama para gato", 15000, "Descanso"));
    }
}