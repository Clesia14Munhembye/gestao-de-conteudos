package org.cleu.gestaoDeConteudo.controller;

import java.security.Principal;

import org.cleu.gestaoDeConteudo.model.Usuario;
import org.cleu.gestaoDeConteudo.repository.PlanoRepository;
import org.cleu.gestaoDeConteudo.repository.PlataformaTarefaRepository;
import org.cleu.gestaoDeConteudo.repository.TarefaRepository;
import org.cleu.gestaoDeConteudo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private PlanoRepository  planoRepository;
    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PlataformaTarefaRepository plataformaTarefaRepository;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElseThrow(()-> new RuntimeException("Usuario não encontrada"));
        model.addAttribute("user",usuario.getId());
        model.addAttribute("numconteudo", tarefaRepository.countByContedoAndUsuarioId(usuario.getId()));
        model.addAttribute("numTarefas", tarefaRepository.countByUsuarioId(usuario.getId()));
        model.addAttribute("validadePlano", planoRepository.findValidadeByUsuarioId(usuario.getId()));
        model.addAttribute("publicacaoes", plataformaTarefaRepository.countByIdUsuarioId(usuario.getId()));
        return "dashboard";
    }
}
