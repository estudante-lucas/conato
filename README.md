# 📖 Introdução

Este projeto foi criado para o curso de Desenvolvimento de Sistemas para Web, ministrado pelo docente [Julio Cezar Rutke](https://github.com/jrutke). O objetivo do projeto é desenvolver uma API em Java utilizando Spring Framework e integrá-la com Spring Data JPA e PostgreSQL. Os principais requisitos do projeto incluem a implementação de diferentes endpoints para realizar a criação, atualização, exclusão e busca de registros na base de dados. A finalidade da aplicação é o gereciamento de contatos.

<br>

# ⚙️ Configuração

## Dependências

- Java SE Development Kit 17.0.6
- Maven 3.8+
- PostgreSQL Version 15

## Banco de dados

- É necessário criar uma base de dados com o nome `lista_telefonica`.
- Por padrão está configurado o acesso ao postgres atavés da porta `5432`;
- O usuário e senha para acesso ao banco de dados é `postgres`;
- A porta onde o projeto irá rodar é `7071`;
- Estas configurações podem ser alteradas em `contato/src/main/resources/application.properties`.

<br>

# 📗 Iniciando

## Rodando

- Abra o prompt de comando;
- Acesse a pasta onde o projeto foi clonado;
- Execute o comando abaixo:

```
.\mvnw spring-boot:run
```

- Aguarde a instalação das dependências e o build ser gerado;
- O projeto deverá estar rodando dentro de alguns minutos.
