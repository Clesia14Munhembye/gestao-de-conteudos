package org.cleu.gestaoDeConteudo.controller;

import org.cleu.gestaoDeConteudo.repository.ConteudoRepository;
import org.cleu.gestaoDeConteudo.repository.PlanoRepository;
import org.cleu.gestaoDeConteudo.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ConteudoRepository conteudoRepository;
    @Autowired
    private PlanoRepository  planoRepository;
    @Autowired
    private TarefaRepository tarefaRepository;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @RequestParam("us") Integer usuarioId){
        model.addAttribute("user",usuarioId);
        model.addAttribute("numconteudo", tarefaRepository.countByContedoAndUsuarioId(usuarioId));
        model.addAttribute("numTarefas", tarefaRepository.countByUsuarioId(usuarioId));
        model.addAttribute("validadePlano", planoRepository.findValidadeByUsuarioId(usuarioId));
        return "dashboard";
    }
}
