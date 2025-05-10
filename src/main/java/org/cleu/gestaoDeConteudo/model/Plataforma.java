package org.cleu.gestaoDeConteudo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "plataforma")
public class Plataforma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @NotNull
    private String nome;

    @ManyToMany
    @JoinTable(
            name = "plataforma_tarefa", // tabela de ligação
            joinColumns = @JoinColumn(name = "plataforma_id"),
            inverseJoinColumns = @JoinColumn(name = "tarefa_id")
    )
    private List<Tarefa> tarefas;

}
