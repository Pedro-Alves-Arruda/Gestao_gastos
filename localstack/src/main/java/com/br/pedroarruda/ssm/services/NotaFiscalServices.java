package com.br.pedroarruda.ssm.services;


import com.br.pedroarruda.ssm.dto.NotaFiscalDTO;
import com.br.pedroarruda.ssm.model.NotaFiscal;
import com.br.pedroarruda.ssm.model.Usuario;
import com.br.pedroarruda.ssm.repository.NotaFiscalRepository;
import com.br.pedroarruda.ssm.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class NotaFiscalServices {

    @Autowired
    private NotaFiscalRepository repository;

    @Autowired
    private UsersRepository usersRepository;

    public NotaFiscal save(NotaFiscalDTO notaFiscalDTO){
        Usuario usuario = usersRepository.findByUsuario(notaFiscalDTO.usuario());
        NotaFiscal notaNova = new NotaFiscal(notaFiscalDTO.valor(), notaFiscalDTO.estabelecimento(), notaFiscalDTO.data(), usuario.getId());
        return repository.save(notaNova);
    }

    public List<NotaFiscal> findAll(){
        return repository.findAll();
    }

    public List<NotaFiscal> findGastosById(Long id){
        return repository.findGastosById(id);
    }

}
