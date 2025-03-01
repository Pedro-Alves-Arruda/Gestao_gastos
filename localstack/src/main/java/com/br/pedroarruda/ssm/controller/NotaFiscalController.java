package com.br.pedroarruda.ssm.controller;


import com.br.pedroarruda.ssm.dto.NotaFiscalDTO;
import com.br.pedroarruda.ssm.model.NotaFiscal;
import com.br.pedroarruda.ssm.model.Usuario;
import com.br.pedroarruda.ssm.repository.UsersRepository;
import com.br.pedroarruda.ssm.services.NotaFiscalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/cadastroGasto")
public class NotaFiscalController {

    @Autowired
    private NotaFiscalServices notaFiscalServices;

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("/TelaCadastroGasto")
    public String telaCadastro(Model model){
        model.addAttribute("NotaFiscal", new NotaFiscalDTO(null, null, null, null));
        model.addAttribute("MensagemErro", new String());
        return "Gastos/cadastro_nota";
    }

    @PostMapping("/novo")
    public String novo(@ModelAttribute NotaFiscalDTO notaFiscal, Model model){
        NotaFiscal novaNota = notaFiscalServices.save(notaFiscal);
        if(novaNota != null){
            Usuario usuario = usersRepository.findByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("saldo", usuario.getSaldo() == null ? 0.0 : usuario.getSaldo());

            List<NotaFiscal> gastos = notaFiscalServices.findAll();

            List<NotaFiscal> finalGastos = gastos;
            gastos = Arrays.stream(usuario.getRoles())
                    .toList()
                    .stream()
                    .map(role -> {
                        if (!role.equals("ADMIN")) {
                            return notaFiscalServices.findGastosById(usuario.getId());
                        }
                        return finalGastos;

                    })
                    .filter(Objects::nonNull)
                    .flatMap(List::stream)
                    .collect(Collectors.toList());

            model.addAttribute("ListaGastos", gastos);
            return "Gastos/lista_gastos";
        }
        return "redirect:/cadastroGasto/TelaCadastroGasto";
    }

    @GetMapping("listaGastos")
    public String listaGastos(Model model){
        Usuario usuario = usersRepository.findByUsuario(SecurityContextHolder.getContext().getAuthentication().getName());

        List<NotaFiscal> gastos = notaFiscalServices.findAll();

        List<NotaFiscal> finalGastos = gastos;
        gastos = Arrays.stream(usuario.getRoles())
                .toList()
                .stream()
                .map(role -> {
                    if (!role.equals("ADMIN")) {
                        return notaFiscalServices.findGastosById(usuario.getId());
                    }
                    return finalGastos;

                })
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        model.addAttribute("saldo", usuario.getSaldo() == null ? 0.0 : usuario.getSaldo());
        model.addAttribute("ListaGastos", gastos);
        return "Gastos/lista_gastos";
    }




}
