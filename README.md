# API de gerenciamento de livros desenvolvida para elaborar testes

Este repositório contém uma API simples para o gerenciamento de livros, construída com o framework Spring Boot. O principal objetivo deste projeto é demonstrar como implementar e realizar **testes unitários** e **testes de integração** em uma API. A API permite realizar operações CRUD (Create, Read, Update, Delete) para livros, além de operações de empréstimo e devolução de livros.

## Estrutura do Projeto

A estrutura do projeto segue a arquitetura MVC (Model-View-Controller), com a inclusão de testes unitários para cada camada.

### Camada de Modelo (`model`)

- **Book**: Representa a entidade `Book` que será mapeada para a tabela `books` no banco de dados. Possui os atributos: `id`, `title`, `author`, `releaseYear`, e `quantityAvailable`.

### Camada de Serviço (`service`)

- **BookService**: Contém a lógica de negócios da aplicação, incluindo os métodos para criar, atualizar, excluir, listar, pegar por ID, emprestar e devolver livros.

### Camada de Controlador (`controller`)

- **BooksController**: Controlador responsável por expor os endpoints da API para interação com os usuários. Ele define as rotas para as operações CRUD, além de operações de empréstimo e devolução de livros.

### Repositório (`repository`)

- **BookRepository**: Interface que estende `JpaRepository` e é usada para interagir com o banco de dados para operações de persistência de livros.

## Funcionalidades da API

A API disponibiliza as seguintes funcionalidades:

- **Listar livros**:
  - `GET /api/books/list`: Retorna todos os livros cadastrados no banco de dados.
  - `GET /api/books/list/{id}`: Retorna um livro específico pelo seu ID.
  
- **Criar um novo livro**:
  - `POST /api/books/save`: Cria um novo livro no banco de dados.

- **Atualizar um livro**:
  - `PUT /api/books/update/{id}`: Atualiza um livro específico pelo seu ID.

- **Empréstimo de livro**:
  - `PUT /api/books/update/borrow/{id}`: Decrementa a quantidade disponível de um livro (simulando o empréstimo).

- **Devolução de livro**:
  - `PUT /api/books/update/bookHasReturned/{id}`: Incrementa a quantidade disponível de um livro (simulando a devolução).

- **Excluir um livro**:
  - `DELETE /api/books/delete/{id}`: Exclui um livro pelo seu ID.

## Dependências

- **Spring Boot**: Framework principal utilizado para a criação da API.
- **Jakarta Persistence (JPA)**: Para a persistência de dados no banco de dados.
- **H2 Database**: Banco de dados em memória usado para testes de integração.
- **JUnit 5**: Framework para realização de testes unitários e de integração.
- **Mockito**: Framework para criação de mocks nos testes unitários.

## Testes

### Testes de Repositório

Os testes de repositório são de **integração**, utilizando o banco de dados em memória H2. Os testes verificam a persistência de dados e a busca de livros no banco de dados.

#### Exemplos de Testes:
- **Salvar Livro**: Verifica se um livro é corretamente salvo no banco de dados.
- **Buscar Livro por ID**: Verifica se um livro pode ser encontrado corretamente pelo seu ID.

### Testes de Serviço

Os testes de serviço são **unitários**, utilizando **Mockito** para simular o comportamento do repositório e verificar a lógica de negócios.

#### Exemplos de Testes:
- **Criar Livro**: Verifica se um livro é corretamente criado, validando os dados fornecidos.
- **Atualizar Livro**: Verifica se um livro pode ser atualizado corretamente.
- **Excluir Livro**: Verifica se um livro pode ser excluído corretamente.
- **Empréstimo e Devolução de Livro**: Verifica se o empréstimo e devolução de livros funcionam corretamente, ajustando a quantidade disponível.

### Testes de Integração

Além dos testes unitários, o projeto também possui testes de integração que verificam a interação entre a API, o banco de dados e os serviços.

#### Exemplos de Testes de Integração:
- **Salvar e Listar Livros**: Verifica se livros podem ser salvos e listados corretamente via API.
- **Buscar Livro por ID**: Verifica se a busca de livros por ID funciona conforme esperado.

## Como Executar o Projeto

### Pré-requisitos

- JDK 11 ou superior
- Maven ou Gradle
- IDE (como IntelliJ IDEA ou Eclipse) ou terminal para rodar o projeto

### Passos para Execução

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/repositorio.git

2. Acesse a pasta onde está o projeto:
   ```bash
   cd repositorio

3. Execute o projeto (Maven):
   ```bash
   mvn spring-boot:run

#### A API estará rodando em: `http://localhost:8080`

## Executando os testes (Maven)
   ```bash
   mvn test
