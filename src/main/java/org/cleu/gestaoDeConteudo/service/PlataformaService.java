package org.cleu.gestaoDeConteudo.service;

import java.util.List;

import org.cleu.gestaoDeConteudo.model.Plataforma;
import org.cleu.gestaoDeConteudo.repository.PlataformaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class PlataformaService {

  
    private final PlataformaRepository repository;

    @Autowired
    public PlataformaService(PlataformaRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        if (repository.count() == 0) { // Evita salvar duplicados
            List<Plataforma> plataformas = List.of(
                new Plataforma(1, "Facebook"),
                new Plataforma(2, "Instagram")
            );
            repository.saveAll(plataformas);
        }
    }

}
