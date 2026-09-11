# 🎮 Loja de Games

API REST desenvolvida durante o **Bootcamp Java Full Stack da Generation Brasil**, com o objetivo de praticar o desenvolvimento de aplicações Back-end utilizando Java e Spring Boot.
O projeto simula o Back-end de uma **loja de games**, permitindo o gerenciamento de produtos, categorias e usuários, além de implementar autenticação e autorização utilizando **Spring Security e JWT**.

## 🚀 Tecnologias utilizadas

- ☕ Java 17
- 🌱 Spring Boot
- 🌐 Spring MVC
- 🗄️ Spring Data JPA
- 🔐 Spring Security
- 🔑 JWT (JSON Web Token)
- 🐬 MySQL
- ✅ Bean Validation
- 📦 Maven

## 📌 Funcionalidades

### 🎮 Produtos

- Cadastro de produtos
- Consulta de produtos
- Consulta de produto por ID
- Atualização de produtos
- Exclusão de produtos
- Associação de produtos a categorias

### 🏷️ Categorias

- Cadastro de categorias
- Consulta de categorias
- Consulta de categoria por ID
- Atualização de categorias
- Exclusão de categorias
- Relacionamento com produtos

### 👤 Usuários

- Cadastro de usuários
- Login
- Autenticação utilizando JWT
- Criptografia de senha
- Controle de acesso

## 🔐 Segurança

A API utiliza **Spring Security** para proteger os endpoints e **JWT (JSON Web Token)** para autenticação dos usuários.

O fluxo de autenticação funciona da seguinte forma:

```text
Cadastro
   ↓
Usuário
   ↓
Login
   ↓
Autenticação
   ↓
Token JWT
   ↓
Acesso aos endpoints protegidos
```

As senhas dos usuários são armazenadas de forma segura utilizando criptografia.

## 🏗️ Estrutura do projeto

O projeto segue uma arquitetura organizada em camadas:

```text
src
├── main
│   └── java
│       └── com.generation.lojadegames
│           ├── configuration
│           ├── controller
│           ├── model
│           ├── repository
│           ├── security
│           └── service
│
└── test
    └── java
        └── com.generation.lojadegames
```

### Principais camadas

**Controller**  
Responsável pelos endpoints da API e pela comunicação com as requisições HTTP.

**Service**  
Responsável pelas regras de negócio da aplicação.

**Repository**  
Responsável pela persistência e comunicação com o banco de dados através do Spring Data JPA.

**Model**  
Contém as entidades utilizadas pela aplicação, como Usuário, Categoria e Produto.

**Security**  
Contém as configurações relacionadas à autenticação, autorização e utilização do JWT.

**Configuration**  
Responsável pelas configurações gerais da aplicação.

## 🔗 Relacionamentos

A aplicação trabalha com relacionamentos entre as entidades.

```text
Categoria
    │
    └── Produtos
```

Os produtos são associados às suas respectivas categorias, permitindo organizar os games dentro da loja.

## 📡 Principais endpoints

### Usuários

```http
POST /usuarios/cadastrar
POST /usuarios/logar
```

### Categorias

```http
GET    /categorias
GET    /categorias/{id}
POST   /categorias
PUT    /categorias/{id}
DELETE /categorias/{id}
```

### Produtos

```http
GET    /produtos
GET    /produtos/{id}
POST   /produtos
PUT    /produtos/{id}
DELETE /produtos/{id}
```

> Alguns endpoints da aplicação exigem autenticação através do token JWT.

## ▶️ Como executar o projeto

### 1. Clone o repositório

```bash
git clone https://github.com/alveslaine/Loja_de_games.git
```

### 2. Acesse a pasta

```bash
cd Loja_de_games
```

### 3. Configure o banco de dados

Crie o banco de dados MySQL e configure as informações de conexão no arquivo de propriedades da aplicação.

### 4. Execute o projeto

No Windows:

```bash
mvnw.cmd spring-boot:run
```

Ou utilizando Maven:

```bash
mvn spring-boot:run
```

A API será iniciada localmente.

## 🧪 Testes

O projeto também possui estrutura para testes automatizados utilizando o ecossistema de testes do Spring Boot.

## 🎯 Objetivo do projeto

Este projeto foi desenvolvido para colocar em prática conceitos importantes de desenvolvimento Back-end com Java e Spring, incluindo:

- Desenvolvimento de APIs REST
- Programação Orientada a Objetos
- Spring Boot
- Spring Data JPA
- Relacionamento entre entidades
- CRUD
- Validação de dados
- Spring Security
- Autenticação com JWT
- Criptografia de senhas
- Integração com banco de dados MySQL
- Organização de projetos em camadas

⭐ Projeto desenvolvido para fins de estudo e prática em desenvolvimento Back-end com Java e Spring Boot.
