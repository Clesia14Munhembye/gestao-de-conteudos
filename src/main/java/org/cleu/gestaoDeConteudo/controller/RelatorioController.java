package org.cleu.gestaoDeConteudo.controller;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.cleu.gestaoDeConteudo.model.Tarefa;
import org.cleu.gestaoDeConteudo.model.Usuario;
import org.cleu.gestaoDeConteudo.repository.TarefaRepository;
import org.cleu.gestaoDeConteudo.repository.UsuarioRepository;
import org.cleu.gestaoDeConteudo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RelatorioController {

    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/tarefas/pdf")
    public void gerarPdf(@RequestParam("us") Integer usuarioId, HttpServletResponse response) throws IOException, JRException {
        List<Tarefa> tarefas = tarefaRepository.findAll(); // Ideal: buscar apenas tarefas do usuário

        List<org.cleu.gestaoDeConteudo.service.TarefaDTO> tarefaDTOs = tarefas.stream()
                .filter(t -> t.getUsuario() != null && t.getUsuario().getId().equals(usuarioId))
                .map(t -> new TarefaDTO(
                        t.getConteudo() != null ? t.getConteudo().getNome() : "Sem Nome",
                        t.getTema(),
                        t.getLegenda(),
                        t.getDataCriacao(),
                        t.getAgendamento()
                ))
                .collect(Collectors.toList());

        Usuario usuario =  usuarioRepository.findById(usuarioId).orElse(null);
        JerarPDFTarefas relatorio = new JerarPDFTarefas();
        relatorio.gerarRelatorio(tarefaDTOs, usuario.getNome() ,usuario.getEmail(), new Date());

        // Envia o PDF gerado para o navegador
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=relatorio_tarefas.pdf");
        java.nio.file.Files.copy(java.nio.file.Path.of("relatorio_tarefas.pdf"), response.getOutputStream());
        response.getOutputStream().flush();
    }
}
