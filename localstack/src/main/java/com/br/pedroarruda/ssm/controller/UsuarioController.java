package com.br.pedroarruda.ssm.controller;


import com.br.pedroarruda.ssm.dto.UsuarioDTO;
import com.br.pedroarruda.ssm.model.Usuario;
import com.br.pedroarruda.ssm.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UserServices services;

    @GetMapping("getTemplateCadastroUsuario")
    public String getTemplateCadastroUsuario(Model model){
        model.addAttribute("usuario", new Usuario());
        return "Usuario/cadastro_usuario";
    }

    @GetMapping("/findAll")
    @ResponseBody
    public List<Usuario> findAll(){
        var users = services.findAll();
        return users;
    }

    @GetMapping("/getTemplateListaUsuario")
    public String getTemplateListaUsuario(Model model){
        var users = services.findAll();
        model.addAttribute("usuarios", users);
        return "Usuario/lista_usuarios";
    }


    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute UsuarioDTO usuario, @RequestParam("roles") String[] roles, Model model){
        var Novousuario = services.save(usuario, roles);
        model.addAttribute("usuarios", services.findAll());
        return "Usuario/lista_usuarios";
    }

}
