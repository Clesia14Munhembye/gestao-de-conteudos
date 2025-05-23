package org.cleu.gestaoDeConteudo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarefa")
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String legenda;
    private String tema;

    @ManyToOne
    @JoinColumn(name = "conteudo_id")
    private Conteudo conteudo;

    @Column(columnDefinition = "date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(value = TemporalType.DATE)
    private Date agendamento;

    @CreationTimestamp
    @Column(name = "data_criacao", columnDefinition = "datetime")
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date dataCriacao;

}
