package org.cleu.gestaoDeConteudo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "assinatura")
public class Assinatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "plano_id")
    private Plano plano;

    @Column(name = "data_criacao", columnDefinition = "date")
    @Temporal(value = TemporalType.DATE)
    private Date dataInicio;

    @Column(name = "data_fim", columnDefinition = "date")
    @Temporal(value = TemporalType.DATE)
    private Date dataFim;
}
