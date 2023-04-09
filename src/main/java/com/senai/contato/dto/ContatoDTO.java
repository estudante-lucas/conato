package com.senai.contato.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ContatoDTO {
    private Long id;
    private String nome;
    private String email;
    private String telefone;

    public boolean temId() {
        return id != null;
    }

    public boolean naoTemId() {
        return !temId();
    }
}
