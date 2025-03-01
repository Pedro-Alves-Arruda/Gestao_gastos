package com.br.pedroarruda.ssm.repository;

import com.br.pedroarruda.ssm.model.NotaFiscal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {

    @Query(value = "select nf.* from nota_fiscal as nf join usuario as us on nf.id_usuario = us.id where us.id = :id", nativeQuery = true)
    public List<NotaFiscal> findGastosById(@Param("id") Long id);
}
