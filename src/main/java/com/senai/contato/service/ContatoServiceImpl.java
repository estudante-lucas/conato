package com.senai.contato.service;

import com.senai.contato.dto.ContatoDTO;
import com.senai.contato.entity.ContatoEntity;
import com.senai.contato.exception.ResourceNotFoundException;
import com.senai.contato.mapper.ContatoMapper;
import com.senai.contato.repository.ContatoRepository;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ContatoServiceImpl implements ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    private ContatoMapper getMapper() {
        return ContatoMapper.INSTANCE;
    }

    @Override
    public List<ContatoDTO> listarTodos() {
        List<ContatoEntity> contatos = contatoRepository.findAll();
        return contatos.stream()
                .map(getMapper()::toDTO)
                .toList();
    }

    @Override
    public ContatoDTO buscarPorId(@NotNull Long id) {
        ContatoEntity contato = contatoRepository.findById(id)
                .orElse(null);
        return getMapper().toDTO(contato);
    }

    @Override
    public ContatoDTO criar(@NotNull ContatoDTO contato) {
        if (contato.temId()) throw new ValidationException("Campo 'id' informado. Se você está tentando atualizar um " +
                "contato, utilze o método HTTP Put");

        return validarESalvar(contato);
    }

    private ContatoDTO validarESalvar(ContatoDTO contato) {
        ContatoEntity contatoEntity = getMapper().toEntity(contato);

        validarCamposObrigatorios(contatoEntity);
        ContatoEntity contatoSalvo = contatoRepository.save(contatoEntity);

        return getMapper().toDTO(contatoSalvo);
    }

    private void validarCamposObrigatorios(ContatoEntity contato) {
        if (StringUtils.isEmpty(contato.getNome())) {
            throw new ValidationException("O campo 'nome' é obrigatório");
        }
        if (StringUtils.isEmpty(contato.getEmail()) && StringUtils.isEmpty(contato.getTelefone())) {
            throw new ValidationException("Deve informar ao menos um dos campos: 'email' ou 'telefone'");
        }
    }

    @Override
    public ContatoDTO atualizar(@NotNull ContatoDTO contato) {
        if (contato.naoTemId()) throw new ValidationException("Campo 'id' não informado. Se você está tentando criar " +
                "um contato, utilize o método HTTP Post");
        ContatoDTO contatoBase = buscarPorId(contato.getId());

        if (contatoBase == null) throw new ResourceNotFoundException(String.format("Contato com id %s não existe",
                contato.getId()));

        contato.setNome(Optional.ofNullable(contato.getNome()).orElse(contatoBase.getNome()));
        contato.setEmail(Optional.ofNullable(contato.getEmail()).orElse(contatoBase.getEmail()));
        contato.setTelefone(Optional.ofNullable(contato.getTelefone()).orElse(contatoBase.getTelefone()));
        return validarESalvar(contato);
    }

    @Override
    public List<ContatoDTO> buscarPorTermo(String nome, String email, String telefone) {
        List<ContatoDTO> contatosPorNome = buscarPorNome(nome);
        List<ContatoDTO> contatosPorEmail = buscarPorEmail(email);
        List<ContatoDTO> contatosPorTelefone = buscarPorTelefone(telefone);

        ArrayList<ContatoDTO> contatos = new ArrayList<>();

        contatos.addAll(contatosPorNome);
        contatos.addAll(contatosPorEmail);
        contatos.addAll(contatosPorTelefone);

        return contatos;
    }

    private List<ContatoDTO> buscarPorNome(String nome) {
        if (nome == null) return Collections.emptyList();

        List<ContatoEntity> contatos = contatoRepository.findByNomeIgnoreCaseContaining(nome);
        return contatos.stream()
                .map(getMapper()::toDTO)
                .toList();
    }

    private List<ContatoDTO> buscarPorEmail(String email) {
        if (email == null) return Collections.emptyList();

        List<ContatoEntity> contatos = contatoRepository.findByEmailIgnoreCaseContaining(email);
        return contatos.stream()
                .map(getMapper()::toDTO)
                .toList();
    }

    private List<ContatoDTO> buscarPorTelefone(String telefone) {
        if (telefone == null) return Collections.emptyList();

        List<ContatoEntity> contatos = contatoRepository.findByTelefoneIgnoreCaseContaining(telefone);
        return contatos.stream()
                .map(getMapper()::toDTO)
                .toList();
    }

    @Override
    public void deletar(@NotNull Long id) {
        ContatoEntity contato = contatoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contato não encontrado com o id: " + id));
        contatoRepository.delete(contato);
    }
}

