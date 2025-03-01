package com.br.pedroarruda;

import com.br.pedroarruda.ssm.controller.UsuarioController;
import com.br.pedroarruda.ssm.model.Usuario;
import com.br.pedroarruda.ssm.services.UserServices;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServices userServices;


    @Test
    @DisplayName("Deve retornar lista de todos os usuários")
    void deveRetornarTodosUsuarios() throws Exception {

        // Arrange
        // Arrange: Criação de lista mockada de usuários
        List<Usuario> users = Arrays.asList(
                new Usuario("Pedro", "pedro.arruda", "pedro.arruda", "pedro@gmail.com"),
                new Usuario("Maria","maria.clara","maria",  "maria@gmail.com")
        );
        when(userServices.findAll()).thenReturn(users);

        // Act & Assert
        mockMvc.perform(get("/user/findAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.length()").value(users.size()));
    }

}
