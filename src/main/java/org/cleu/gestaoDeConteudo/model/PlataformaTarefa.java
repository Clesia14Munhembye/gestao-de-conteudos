package org.cleu.gestaoDeConteudo.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "plataforma_tarefa")
public class PlataformaTarefa {

    @EmbeddedId
    private PlataformaTarefaId id;

    @ManyToOne
    @MapsId("plataformaId")
    @JoinColumn(name = "plataforma_id")
    private Plataforma plataforma;

    @ManyToOne
    @MapsId("tarefaId")
    @JoinColumn(name = "tarefa_id")
    private Tarefa tarefa;

    private String imagem; // ou um campo String (URL ou nome do ficheiro)

    // Getters e setters
}
