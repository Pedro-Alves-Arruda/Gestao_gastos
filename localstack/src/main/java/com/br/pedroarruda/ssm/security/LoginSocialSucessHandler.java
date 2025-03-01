package com.br.pedroarruda.ssm.security;

import com.br.pedroarruda.ssm.model.Usuario;
import com.br.pedroarruda.ssm.repository.UsersRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class LoginSocialSucessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    private final UsersRepository usersRepository;
    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException, ServletException
    {
        OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        OAuth2User user = auth2AuthenticationToken.getPrincipal();
        Usuario usuario = usersRepository.findByEmail(user.getAttribute("email").toString());
        CustomAuthentication customAuthentication = new CustomAuthentication(usuario);
        SecurityContextHolder.getContext().setAuthentication(customAuthentication);
        System.out.println(Arrays.toString(usuario.getRoles()));
        System.out.println(usuario.getUsuario());
        redirectStrategy.sendRedirect(request, response, "/TelaMenu");

    }
}
