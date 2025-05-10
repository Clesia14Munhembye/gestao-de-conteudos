package org.cleu.gestaoDeConteudo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 3, max = 15, message = "O nome apenas deve ter no minimo {min} e no maximo {max}")
    @NotNull
    private String nome;

    @Email
    private String email;

    private String senha;


    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Assinatura> assinaturas;
}
