package org.cleu.gestaoDeConteudo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MidiaController {

    @RequestMapping("/midia")
    public String midia(){
        return "midia";
    }
}
