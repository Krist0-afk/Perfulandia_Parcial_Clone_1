package com.perfulandia.logisticaservice;
import com.perfulandia.logisticaservice.model.Repartidor;
import com.perfulandia.logisticaservice.repository.RepartidorRepository;

import org.springframework.boot.CommandLineRunner;//Permite ejecutar codigo automaticamente al iniciar la aplicacion
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final RepartidorRepository repo;

    public DataLoader(RepartidorRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args){
        //insertar productos a la base de datos para hacer pruebas
        repo.save(new Repartidor(null, "Juan", "Pérez","12345678-9","moto",50,"ABC123"));
        repo.save(new Repartidor(null, "María", "González", "98765432-1", "camioneta", 150, "XYZ789"));
        repo.save(new Repartidor(null, "Pedro", "Ramírez", "11223344-5", "auto", 100, "LMN456"));
    }
}
