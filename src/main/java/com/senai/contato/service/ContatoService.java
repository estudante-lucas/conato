package com.senai.contato.service;

import com.senai.contato.dto.ContatoDTO;

import java.util.List;

public interface ContatoService {
    List<ContatoDTO> listarTodos();
    ContatoDTO buscarPorId(Long id);
    List<ContatoDTO> buscarPorTermo(String nome, String email, String telefone);
    ContatoDTO criar(ContatoDTO contato);
    ContatoDTO atualizar(ContatoDTO contato);
    void deletar(Long id);
}

