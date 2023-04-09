package com.senai.contato.mapper;

import com.senai.contato.dto.ContatoDTO;
import com.senai.contato.entity.ContatoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ContatoMapper {

    ContatoMapper INSTANCE = Mappers.getMapper(ContatoMapper.class);

    ContatoDTO toDTO(ContatoEntity contato);
    ContatoEntity toEntity(ContatoDTO contatoDTO);
}
