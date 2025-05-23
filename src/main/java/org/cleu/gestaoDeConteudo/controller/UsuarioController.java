package org.cleu.gestaoDeConteudo.controller;

import javax.naming.AuthenticationException;

import org.cleu.gestaoDeConteudo.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.cleu.gestaoDeConteudo.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DaoAuthenticationProvider authenticationProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping("/login")
    public String login() {
        return "usuario/login";
    }

    @RequestMapping(path = "criar", method = RequestMethod.GET)
    public ModelAndView CriarConta() {
        ModelAndView mv = new ModelAndView("usuario/criar");
        mv.addObject("usuario", new Usuario());
        return mv;
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public ModelAndView salvar(@Valid Usuario usuario, BindingResult result) {
        ModelAndView mv = new ModelAndView();

        if (result.hasErrors()) {
            mv.setViewName("usuario/criar");
            mv.addObject("erro", "Verifique seus dados e preencha novamente.");
            return mv;
        }

        // Codifica a senha antes de salvar
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioRepository.save(usuario);

        // Login automático após registo
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                usuario.getEmail(), usuario.getSenha());
        Authentication authentication = authenticationProvider.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mv.setViewName("redirect:usuario/plano/get?usuarioId=" + usuario.getId()); // página após login com sucesso
        return mv;
    }

    @PostMapping("/fazerlogin")
    public String processLogin(
            @RequestParam String username,
            @RequestParam String password,
            Model model,
            HttpServletRequest request) {

        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);

            Authentication authentication = authenticationManager.authenticate(authToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            Usuario usuario = usuarioRepository.findByEmail(authentication.getName()).orElse(null);

            return "redirect:/usuario/plano/get?usuarioId=" + usuario.getId();

        } catch (org.springframework.security.core.AuthenticationException e) {
            model.addAttribute("error", "Credenciais inválidas");
            return "usuario/login";
        }
    }
}