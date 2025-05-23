package org.cleu.gestaoDeConteudo.service;

import java.util.Date;

public class TarefaDTO {
private String conteudo;
    private String tema;
    private String legenda;
    private Date dataCriacao;
    private Date agendamento;

    // Construtor
    public TarefaDTO(String conteudo, String tema, String legenda, java.util.Date dataCriacao, java.util.Date agendamento) {
        this.conteudo = conteudo;
        this.tema = tema;
        this.legenda = legenda;
        this.dataCriacao = dataCriacao;
        this.agendamento = agendamento;
    }

    // Getters e setters
    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }

    public String getTema() { return tema; }
    public void setTema(String tema) { this.tema = tema; }

    public String getLegenda() { return legenda; }
    public void setLegenda(String legenda) { this.legenda = legenda; }

    public Date getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(Date dataCriacao) { this.dataCriacao = dataCriacao; }

    public Date getAgendamento() { return agendamento; }
    public void setAgendamento(Date agendamento) { this.agendamento = agendamento; }

}
