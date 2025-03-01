package com.br.pedroarruda.ssm.mappers;

import com.br.pedroarruda.ssm.dto.UsuarioDTO;
import com.br.pedroarruda.ssm.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(UsuarioDTO dto);
}
