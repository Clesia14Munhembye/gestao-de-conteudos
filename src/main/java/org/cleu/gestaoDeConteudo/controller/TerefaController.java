package org.cleu.gestaoDeConteudo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.security.Principal;
import java.util.List;

import org.cleu.gestaoDeConteudo.model.Conteudo;
import org.cleu.gestaoDeConteudo.model.Tarefa;
import org.cleu.gestaoDeConteudo.model.Usuario;
import org.cleu.gestaoDeConteudo.model.wraper.*;
import org.cleu.gestaoDeConteudo.repository.ConteudoRepository;
import org.cleu.gestaoDeConteudo.repository.TarefaRepository;
import org.cleu.gestaoDeConteudo.repository.UsuarioRepository;

@Controller
@RequestMapping("/tarefa")
public class TerefaController {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ConteudoRepository conteudoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public String mostrarTarefa(Model model, Principal principal) {
        Usuario usuario= usuarioRepository.findByEmail(principal.getName()).orElse(null);
        model.addAttribute("conteudoWrapper", new TerefaWrapper());
        model.addAttribute("user",usuario.getId());
        List<Conteudo> conteudos = conteudoRepository.findConteudosByUsuarioId(usuario.getId());
        model.addAttribute("conteudos", conteudos);
        return "tarefas";
    }

    @PostMapping("/salvar")
    public String salvarGrupoTarefas(@ModelAttribute TerefaWrapper conteudoWrapper) {
        Usuario usuario = usuarioRepository.findById(conteudoWrapper.getUserId()).orElse(null);
        Conteudo conteudo = conteudoWrapper.getConteudo();
        conteudoRepository.save(conteudo);

        for (Tarefa tarefa : conteudoWrapper.getTarefas()) {
            tarefa.setConteudo(conteudo);
            tarefa.setUsuario(usuario);
            tarefaRepository.save(tarefa);
        }
        return "redirect:/tarefa/get";
    }

}
