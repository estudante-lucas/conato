package com.senai.contato.repository;

import com.senai.contato.entity.ContatoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContatoRepository extends JpaRepository<ContatoEntity, Long> {
    List<ContatoEntity> findByNomeIgnoreCaseContaining(String nome);
    List<ContatoEntity> findByEmailIgnoreCaseContaining(String email);
    List<ContatoEntity> findByTelefoneIgnoreCaseContaining(String telefone);
}

