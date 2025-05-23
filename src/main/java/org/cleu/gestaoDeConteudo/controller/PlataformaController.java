package org.cleu.gestaoDeConteudo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

import org.cleu.gestaoDeConteudo.model.Plataforma;
import org.cleu.gestaoDeConteudo.model.PlataformaTarefa;
import org.cleu.gestaoDeConteudo.model.PlataformaTarefaId;
import org.cleu.gestaoDeConteudo.model.Tarefa;
import org.cleu.gestaoDeConteudo.model.Usuario;
import org.cleu.gestaoDeConteudo.repository.PlataformaRepository;
import org.cleu.gestaoDeConteudo.repository.PlataformaTarefaRepository;
import org.cleu.gestaoDeConteudo.repository.TarefaRepository;
import org.cleu.gestaoDeConteudo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/plataforma")
public class PlataformaController {

    @Autowired
    private PlataformaRepository plataformaRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PlataformaTarefaRepository plataformaTarefaRepository;

    @Value("${upload.dir}")
    private String uploadDir; // Diretório onde salvar as imagens

    @PostMapping("/save")
public String salvarPlataforma(
    @RequestParam("nome") String nome,
    @RequestParam("tarefa") Integer tarefaId,
    @RequestParam("imagem") MultipartFile imagem
) throws Exception {

    Tarefa tarefa = tarefaRepository.findById(tarefaId)
        .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

    Plataforma plataforma = new Plataforma();
    plataforma.setNome(nome);
    plataformaRepository.save(plataforma);

    // Salvar a imagem
    String fileName = UUID.randomUUID() + "_" + imagem.getOriginalFilename();
    Path path = Paths.get(uploadDir);
    if (!Files.exists(path)) {
        Files.createDirectories(path);
    }
    Files.write(path.resolve(fileName), imagem.getBytes());

    // Criar associação
    PlataformaTarefa pt = new PlataformaTarefa();
    pt.setId(new PlataformaTarefaId(plataforma.getId(), tarefa.getId()));
    pt.setPlataforma(plataforma);
    pt.setTarefa(tarefa);
    pt.setImagem("/uploads/" + fileName);

    plataformaTarefaRepository.save(pt);

    return "redirect:/plataforma/get";
}

    @GetMapping("/get")
    public String listarPlataformas(Model model) {
        model.addAttribute("plataformas", plataformaTarefaRepository.findAll());
        return "publicacoes/listar";
    }

    @GetMapping("/criar")
    public String criarPublicacao(Model model, Principal principal) {
        System.out.println("Email que esta a usar sistema agora --- " + principal.getName());
        Usuario usuario = usuarioRepository.findByEmail(principal.getName()).orElse(null);
        model.addAttribute("tarefas", tarefaRepository.findByUsuarioId(usuario.getId()));
        return "publicacoes/publicar";
    }
}