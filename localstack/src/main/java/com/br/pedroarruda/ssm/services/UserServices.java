package com.br.pedroarruda.ssm.services;

import com.br.pedroarruda.ssm.dto.UsuarioDTO;
import com.br.pedroarruda.ssm.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.br.pedroarruda.ssm.repository.UsersRepository;

import java.util.List;

@Service
public class UserServices {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    public List<Usuario> findAll(){
        return repository.findAll();
    }

    public Usuario save(UsuarioDTO usuario, String[] roles){
        Usuario novoUsuario = new Usuario(usuario.nome(), usuario.usuario(), encoder.encode(usuario.password()), usuario.email(), roles);
        return repository.save(novoUsuario);
    }

}
