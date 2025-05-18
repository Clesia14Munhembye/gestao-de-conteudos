package org.cleu.gestaoDeConteudo.controller;

import java.util.Calendar;
import java.util.Date;

import org.cleu.gestaoDeConteudo.model.Plano;
import org.cleu.gestaoDeConteudo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.cleu.gestaoDeConteudo.repository.PlanoRepository;
import org.cleu.gestaoDeConteudo.repository.UsuarioRepository;
import jakarta.validation.Valid;
import static org.cleu.gestaoDeConteudo.utils.Constantes.*;



@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PlanoRepository planoRepository;

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
        usuarioRepository.save(usuario);
        mv.setViewName("redirect:/usuario/plano/get?usuarioId=" + usuario.getId());
        return mv;
    }

    @RequestMapping(path = "plano/get", method = RequestMethod.GET)
    public ModelAndView plano(@RequestParam("usuarioId") Integer usuarioId){
        ModelAndView mv = new ModelAndView();
        Plano plano = new Plano();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        plano.setUsuario(usuario); 
        mv.setViewName("usuario/plano");
        mv.addObject("plano", plano); 
        return mv;
    }

    @RequestMapping(path = "plano/save", method = RequestMethod.POST)
    public ModelAndView salvarPlano(@Valid Plano plano, BindingResult result){
        ModelAndView mv= new ModelAndView();
        if(result.hasErrors()){
            mv.setViewName("usuario/plano/get?usuarioId=" + plano.getUsuario().getId());
            mv.addObject("erro", "Verifique seus dados e preenche de novo");
            return mv;
        }

        Usuario usuario = usuarioRepository.findById(plano.getUsuario().getId()).orElse(null);
        plano.setUsuario(usuario);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date()); // Pegar a data de hoje
        calendar.add(Calendar.DAY_OF_MONTH, 31); // Adicionamos 31 dias 
        
        plano.setValidade(calendar.getTime()); // Adicionamos a data no nosso plano

        if(plano.getNome().equalsIgnoreCase("basic"))
        plano.setDescricao(BASIC_DETAILS);
        else if(plano.getNome().equalsIgnoreCase("Standard"))
        plano.setDescricao(STANDART_DETAILS);
        else if(plano.getNome().equalsIgnoreCase("Premium"))
        plano.setDescricao(PREMINUM_DETAILS);

        planoRepository.save(plano);
        mv.setViewName("redirect:/tarefa/get?us="+usuario.getId());
        return mv;
    }
}
