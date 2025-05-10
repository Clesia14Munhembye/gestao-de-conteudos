package org.cleu.gestaoDeConteudo.controller;

import org.cleu.gestaoDeConteudo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.model.Model;

import org.cleu.gestaoDeConteudo.repository.UsuarioRepository;
import jakarta.validation.Valid;



@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository UsuarioRepository;

    @RequestMapping("/login")
    public String login(){
        return "usuario/login";
    }

    @RequestMapping(path = "criar", method = RequestMethod.GET)
    public ModelAndView CriarConta(){
        ModelAndView mv= new ModelAndView("usuario/criar");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @RequestMapping(path="/save", method=RequestMethod.POST)
    public ModelAndView requestMethodName(@Valid Usuario usuario, BindingResult result) {
        ModelAndView mv= new ModelAndView();
        if(result.hasErrors()){
            mv.setViewName("usuario/criar");
            mv.addObject("erro", "Verifique seus dados e preenche de novo");
        }
        UsuarioRepository.save(usuario);
        mv.setViewName("redirect:/tarefas");
        return mv;
    }
    
}
