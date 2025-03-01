package com.br.pedroarruda.ssm.repository;


import com.br.pedroarruda.ssm.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select * from usuario where usuario = :usuario", nativeQuery = true)
    public Usuario findByEmailSenha(@Param("usuario") String usuario);


    public Usuario findByUsuario(String usuario);

    @Query(value = "select * from usuario where email = :email", nativeQuery = true)
    Usuario findByEmail(@Param("email") String email);
}
