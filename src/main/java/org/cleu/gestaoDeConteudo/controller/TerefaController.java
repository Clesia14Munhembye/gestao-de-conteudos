package org.cleu.gestaoDeConteudo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TerefaController {


    @RequestMapping(path = "/tarefas", method = RequestMethod.GET)
    public String tarefa(){
        return "tarefas";
    }
}
