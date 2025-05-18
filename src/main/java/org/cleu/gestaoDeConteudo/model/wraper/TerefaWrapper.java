package org.cleu.gestaoDeConteudo.model.wraper;

import java.util.ArrayList;
import java.util.List;

import org.cleu.gestaoDeConteudo.model.Conteudo;
import org.cleu.gestaoDeConteudo.model.Tarefa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TerefaWrapper {
    private Integer userId;
    private Conteudo conteudo;
    private List<Tarefa> tarefas = new ArrayList<>();
}
