package org.cleu.gestaoDeConteudo.controller;

import java.util.Calendar;
import java.util.Date;

import org.cleu.gestaoDeConteudo.model.Plano;
import org.cleu.gestaoDeConteudo.model.Usuario;
import org.cleu.gestaoDeConteudo.repository.PlanoRepository;
import org.cleu.gestaoDeConteudo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import static org.cleu.gestaoDeConteudo.utils.Constantes.*;

import jakarta.validation.Valid;

 
@Controller
@RequestMapping("/usuario/")
public class PlanoController {

   @Autowired
    private PlanoRepository planoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
  
    @RequestMapping(path = "plano/get", method = RequestMethod.GET)
    public ModelAndView plano(@RequestParam("usuarioId") Integer usuarioId){
        ModelAndView mv = new ModelAndView();
        Plano plano = new Plano();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
        Plano plano2 = planoRepository.findByUsuario(usuario).orElse(null);
        if (plano2!=null) {
            mv.setViewName("redirect:/dashboard?us=" + usuario.getId());
        }
        else {
            plano.setUsuario(usuario);
            mv.addObject("plano", plano);
            mv.setViewName("usuario/plano");
        }
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
        mv.setViewName("redirect:/dashboard?us="+usuario.getId());
        return mv;
    }
}
