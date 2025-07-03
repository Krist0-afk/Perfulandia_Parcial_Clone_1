package com.perfulandia.pedidos;

import com.perfulandia.pedidos.model.pedidoModel;
import com.perfulandia.pedidos.repository.pedidoInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final pedidoInterface repo;

    public DataLoader(pedidoInterface repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) throws Exception {
        repo.save(new pedidoModel(0, "usuario01", "Perfume A", "Fragancia cítrica de verano", "Pendiente"));
        repo.save(new pedidoModel(0, "usuario02", "Perfume B", "Aroma intenso para la noche", "En camino"));
        repo.save(new pedidoModel(0, "usuario03", "Perfume C", "Notas florales suaves", "Entregado"));
    }
}
