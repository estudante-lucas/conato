package com.senai.contato.controller;

import com.senai.contato.dto.ContatoDTO;
import com.senai.contato.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public ResponseEntity<List<ContatoDTO>> listarTodos() {
        List<ContatoDTO> contatos = contatoService.listarTodos();
        return ResponseEntity.ok(contatos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContatoDTO> buscarPorId(@PathVariable Long id) {
        ContatoDTO contato = contatoService.buscarPorId(id);
        return ResponseEntity.ofNullable(contato);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<ContatoDTO>> buscarPorTermo(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String telefone
    ) {
        List<ContatoDTO> contatos = contatoService.buscarPorTermo(nome, email, telefone);
        return ResponseEntity.ok(contatos);
    }

    @PostMapping
    public ResponseEntity<ContatoDTO> criar(@RequestBody ContatoDTO contato) {
        ContatoDTO contatoSalvo = contatoService.criar(contato);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(contatoSalvo.getId())
                .toUri();

        return ResponseEntity.created(location).body(contatoSalvo);
    }

    @PutMapping
    public ResponseEntity<ContatoDTO> atualizar(@RequestBody ContatoDTO contato) {
        ContatoDTO contatoAtualizado = contatoService.atualizar(contato);
        return ResponseEntity.ok(contatoAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        contatoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

