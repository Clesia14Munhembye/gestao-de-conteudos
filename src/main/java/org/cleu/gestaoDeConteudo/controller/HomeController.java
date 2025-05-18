package org.cleu.gestaoDeConteudo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @RequestParam("us") Integer usuarioId){
        model.addAttribute("user",usuarioId);
        return "dashboard";
    }
}
