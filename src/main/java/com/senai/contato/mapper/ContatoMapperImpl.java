package com.senai.contato.mapper;

import com.senai.contato.dto.ContatoDTO;
import com.senai.contato.entity.ContatoEntity;
import org.springframework.stereotype.Component;

@Component
public class ContatoMapperImpl implements ContatoMapper {

    @Override
    public ContatoDTO toDTO(ContatoEntity contato) {
        if (contato == null) return null;

        ContatoDTO contatoDTO = new ContatoDTO();
        contatoDTO.setId(contato.getId());
        contatoDTO.setNome(contato.getNome());
        contatoDTO.setEmail(contato.getEmail());
        contatoDTO.setTelefone(contato.getTelefone());
        return contatoDTO;
    }

    @Override
    public ContatoEntity toEntity(ContatoDTO contatoDTO) {
        if (contatoDTO == null) return null;

        ContatoEntity contato = new ContatoEntity();
        contato.setId(contatoDTO.getId());
        contato.setNome(contatoDTO.getNome());
        contato.setEmail(contatoDTO.getEmail());
        contato.setTelefone(contatoDTO.getTelefone());
        return contato;
    }
}
