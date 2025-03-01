package com.br.pedroarruda.ssm.controller;

import com.br.pedroarruda.ssm.dto.UsuarioDTO;
import com.br.pedroarruda.ssm.mappers.UsuarioMapper;
import com.br.pedroarruda.ssm.model.Usuario;
import com.br.pedroarruda.ssm.services.LoginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/login")
public class LoginController {


    @Autowired
    private LoginServices loginServices;

    @Autowired
    private final UsuarioMapper mapper;

    public LoginController(UsuarioMapper mapper) {
        this.mapper = mapper;
    }


    @GetMapping("/Telalogin")
    public String templateLogin(Model model){
        model.addAttribute("Usuario", new UsuarioDTO(null, null, null, null, null));
        return "Login/login";
    }

    @PostMapping("/VerificarLogin")
    public String verificarLogin(@ModelAttribute UsuarioDTO usuario, RedirectAttributes redirect){

        if(usuario.usuario().isEmpty() && usuario.email().isEmpty()){
            redirect.addFlashAttribute("Por favor preencha o campo email ou usuario");
            return "redirect:/login/Telalogin";
        }

        Usuario emailUsuarioDesejado = loginServices.verificarLogin(usuario.usuario());

        if(emailUsuarioDesejado != null && !emailUsuarioDesejado.getEmail().isEmpty()){
            return "redirect:/TelaMenu";
        }
        redirect.addFlashAttribute("Erro, usuario não encontrado, por favor verificar os dados informados");
        return "redirect:/login/Telalogin";

    }
}
