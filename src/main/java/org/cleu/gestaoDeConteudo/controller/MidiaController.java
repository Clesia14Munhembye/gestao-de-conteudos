package org.cleu.gestaoDeConteudo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MidiaController {

    @RequestMapping("/midia")
    public String midia(Model model, @RequestParam("us") Integer usuarioId){
        model.addAttribute("user",usuarioId);
        return "midia";
    }
}
