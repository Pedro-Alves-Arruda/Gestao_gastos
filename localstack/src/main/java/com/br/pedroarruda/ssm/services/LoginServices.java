package com.br.pedroarruda.ssm.services;

import com.br.pedroarruda.ssm.dto.UsuarioDTO;
import com.br.pedroarruda.ssm.model.Usuario;
import com.br.pedroarruda.ssm.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServices {

    @Autowired
    private UsersRepository usersRepository;

    public Usuario verificarLogin(String usuario){
        return usersRepository.findByUsuario(usuario);
    }

}
