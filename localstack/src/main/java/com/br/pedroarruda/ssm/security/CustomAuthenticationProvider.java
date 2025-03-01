package com.br.pedroarruda.ssm.security;

import com.br.pedroarruda.ssm.model.Usuario;
import com.br.pedroarruda.ssm.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UsersRepository usersRepository;

    private final PasswordEncoder encoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String login = authentication.getName();
        String senha = authentication.getCredentials().toString();

        Usuario usuario = usersRepository.findByUsuario(login);

        if(usuario == null){
            throw getErroUsuarioNaoEncontrado();
        }

        if(usuario.getUsuario().equals(login) && encoder.matches(senha, usuario.getPassword())){
            return new CustomAuthentication(usuario);
        }

        throw getErroUsuarioNaoEncontrado();
    }

    private UsernameNotFoundException getErroUsuarioNaoEncontrado() {
        return new UsernameNotFoundException("Usuario e/ou senha incorretos");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
