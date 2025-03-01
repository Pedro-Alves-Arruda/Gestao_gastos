package com.br.pedroarruda.ssm.security;

import com.br.pedroarruda.ssm.model.Usuario;
import com.br.pedroarruda.ssm.repository.UsersRepository;
import com.br.pedroarruda.ssm.services.LoginServices;
import com.br.pedroarruda.ssm.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomUserDetailService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    private UserServices userServices;

    public CustomUserDetailService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usersRepository.findByUsuario(username);

        if(usuario == null){
            throw new UsernameNotFoundException("");
        }

        String[] roles = Arrays.stream(usuario.getRoles()).toList().stream()
                .toArray(String[]::new);

        return User.builder()
                .username(usuario.getUsuario())
                .password(usuario.getPassword())
                .roles(roles)
                .build();

    }
}
