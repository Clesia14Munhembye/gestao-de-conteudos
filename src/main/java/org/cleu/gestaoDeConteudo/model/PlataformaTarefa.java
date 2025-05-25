package org.cleu.gestaoDeConteudo.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
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

    @CreationTimestamp
    @Column(name = "data_publicacao", columnDefinition = "datetime")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dataPublicacao;
    // Getters e setters
}
