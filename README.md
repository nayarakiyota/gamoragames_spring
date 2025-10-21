![Gamora Games Logo](https://copilot.microsoft.com/th/id/BCO.1a4b6f50-21e2-4665-ad2e-600e44fbd1dc.png)



# 🎮 GAMORA GAMES

**GAMORA GAMES** é uma aplicação BackEnd desenvolvida em **Spring Boot**, que simula uma **loja virtual de jogos**.  
O sistema permite realizar operações CRUD (Criar, Ler, Atualizar e Deletar) das entidades **Produto** e **Categoria**, que estão relacionadas entre si.  
As rotas da API foram testadas utilizando o **Insomnia**.

---

## 🧠 Objetivo do Projeto

O objetivo do GAMORA GAMES é servir como base para estudos de **desenvolvimento BackEnd em Java com Spring Boot**, demonstrando o uso correto de:
- Estrutura MVC (Model, Repository e Controller)
- Mapeamento objeto-relacional (JPA / Hibernate)
- Relacionamento entre entidades (OneToMany / ManyToOne)
- Conexão com banco de dados **MySQL**

---

## 🧩 Estrutura do Projeto

O projeto segue a arquitetura **MVC**, composta por:

📂 gamoraGames
 ┣ 📂 model
 ┃ ┣ 📜 Produto.java
 ┃ ┗ 📜 Categoria.java
 ┣ 📂 repository
 ┃ ┣ 📜 ProdutoRepository.java
 ┃ ┗ 📜 CategoriaRepository.java
 ┣ 📂 controller
 ┃ ┣ 📜 ProdutoController.java
 ┃ ┗ 📜 CategoriaController.java
 ┗ 📜 GamoraGamesApplication.java



---

## 🧱 Modelagem das Entidades

### 🕹️ Produto
| Atributo     | Tipo       | Descrição                                    |
| ------------ | ---------- | -------------------------------------------- |
| `id`         | Long       | Identificador único (gerado automaticamente) |
| `nome`       | String     | Nome do jogo                                 |
| `descricao`  | String     | Breve descrição do jogo                      |
| `preco`      | BigDecimal | Preço do jogo                                |
| `plataforma` | String     | Plataforma (ex: PC, PlayStation, Xbox)       |
| `imagemUrl`  | String     | URL da imagem do jogo                        |
| `categoria`  | Categoria  | Categoria relacionada (ex: Aventura, RPG)    |

> Relação: Muitos produtos pertencem a uma categoria (`@ManyToOne`).

---

### 🧩 Categoria
| Atributo   | Tipo           | Descrição                                    |
| ---------- | -------------- | -------------------------------------------- |
| `id`       | Long           | Identificador único (gerado automaticamente) |
| `tipo`     | String         | Tipo da categoria (ex: Aventura, RPG, etc.)  |
| `produtos` | List\<Produto> | Lista de produtos dessa categoria            |

> Relação: Uma categoria possui vários produtos (`@OneToMany`).

---

### 🧍 Usuário
| Atributo         | Tipo      | Descrição                                    |
| ---------------- | --------- | -------------------------------------------- |
| `id`             | Long      | Identificador único (gerado automaticamente) |
| `nome`           | String    | Nome do usuário                              |
| `usuario`        | String    | Email do usuário                             |
| `senha`          | String    | Senha do usuário (criptografada)             |
| `foto`           | String    | URL da foto do usuário                       |
| `dataNascimento` | LocalDate | Data de nascimento do usuário                |

------



## 🔐 Segurança com JWT e Validação de Maioridade

O projeto agora conta com autenticação e autorização via **JWT (JSON Web Token)**, garantindo que apenas usuários autenticados possam acessar endpoints protegidos.

### 🧾 Novos recursos implementados

- **Model `Usuario` atualizada** com o campo `dataNascimento` (`LocalDate`) para controle de idade.
- **Validação de maioridade**: o backend verifica se o usuário tem pelo menos 18 anos antes de permitir o cadastro.
- **Formato de data personalizado**: o campo `dataNascimento` aceita o formato `dd-MM-yyyy` nas requisições JSON.
- **Criptografia de senha** com `BCryptPasswordEncoder`.
- **Filtro JWT (`JwtAuthFilter`)** para interceptar e validar tokens em cada requisição.
- **Serviço `JwtService`** responsável por gerar e validar tokens.
- **Controller de autenticação** com endpoints para login e cadastro:
  - `POST /auth/cadastrar`
  - `POST /auth/logar`

### 🔐 Exemplo de cadastro com validação de idade

```json
{
  "nome": "Nayara",
  "usuario": "nayara@email.com",
  "senha": "123456",
  "dataNascimento": "15-05-2000"
}
```

> Se o usuário for menor de idade, o backend retorna `400 BAD_REQUEST` com a mensagem: `"Usuário deve ser maior de idade."`

### 🔑 Exemplo de login

```json
{
  "usuario": "nayara@email.com",
  "senha": "123456"
}
```

> O backend retorna um token JWT que deve ser usado no cabeçalho `Authorization` das requisições protegidas:

Código

```
Authorization: Bearer <seu_token_aqui>
```

### 📌 Observações

- O campo `dataNascimento` é obrigatório e deve estar no formato `dd-MM-yyyy`.
- A senha é armazenada de forma segura usando hash `BCrypt`.
- O token JWT tem validade e é necessário para acessar endpoints protegidos.

------



## 🛠️ Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **Insomnia** (para testes da API)
- **Maven**
- **Spring Security**
- **JWT (JSON Web Token)**
- **BCrypt (criptografia de senha)**

---

## ⚙️ Configuração do Banco de Dados

No arquivo `application.properties`, configure as credenciais do seu banco MySQL:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/db_gamora_games
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

## 🚀 Executando o Projeto

1. **Clone o repositório:**

   ```
   git clone https://github.com/nayarakiyota/gamoragames_spring.git
   ```

2. **Importe o projeto** no **Spring Tool Suite (STS)**.

3. **Configure o banco MySQL** e atualize o arquivo `application.properties`.

4. **Execute a aplicação**:

   - Via IDE: `Run → GamoraGamesApplication`

   - Via terminal:

     ```
     mvn spring-boot:run
     ```

A aplicação iniciará na porta padrão **8080**:

```
http://localhost:8080
```

⚠️ Para testar endpoints protegidos, é necessário obter um token JWT via `/auth/logar` e incluí-lo no cabeçalho `Authorization`.



## 🧪 Testando com o Insomnia

### 🔹 Categoria

**Endpoints:**

| Método   | Endpoint                  | Descrição                                    |
| -------- | ------------------------- | -------------------------------------------- |
| `GET`    | `/categorias`             | Lista todas as categorias                    |
| `GET`    | `/categorias/{id}`        | Lista o Id escolhido                         |
| `GET`    | `/categorias/tipo/{tipo}` | Lista todos os produtos com o tipo escolhido |
| `POST`   | `/categorias`             | Cria uma nova categoria                      |
| `PUT`    | `/categorias`             | Atualiza uma categoria existente             |
| `DELETE` | `/categorias/{id}`        | Deleta uma categoria pelo ID                 |

**Exemplo de requisição POST (JSON):**

```
{
  "tipo": "Aventura"
}
```

------

### 🔹 Produto

**Endpoints:**

| Método   | Endpoint                            | Descrição                                                    |
| -------- | ----------------------------------- | ------------------------------------------------------------ |
| `GET`    | `/produtos`                         | Lista todos os produtos                                      |
| `GET`    | `/produtos/{id}`                    | Lista o Id escolhido                                         |
| `GET`    | `/produtos/nome/{nome}`             | Lista o produto por nome escolhido                           |
| `GET`    | `/produtos/plataforma/{plataforma}` | Lista o produto escolhido por plataforma                     |
| `GET`    | `/produtos/preco/menor/{preco}`     | Lista todos os produtos com preço menor que o valor escolhido, ordenados de forma decrescente |
| `GET`    | `/produtos/preco/maior/{preco}`     | Lista todos os produtos com preço maior que o valor escolhido, ordenados de forma crescente |
| `POST`   | `/produtos`                         | Cria um novo produto                                         |
| `PUT`    | `/produtos`                         | Atualiza um produto existente                                |
| `DELETE` | `/produtos/{id}`                    | Deleta um produto pelo ID                                    |

**Exemplo de requisição POST (JSON):**

```
{
  "nome": "God of War Ragnarök",
  "descricao": "Aventura épica ambientada na mitologia nórdica.",
  "preco": 299.90,
  "plataforma": "PS5",
  "imagemUrl": "https://exemplo.com/godofwar.jpg",
  "categoria": {
    "id": 1
  }
}
```

------

## 🪄 Licença

<br />

Este repositório é parte de um projeto educacional, mas contribuições são sempre bem-vindas! Caso tenha sugestões, correções ou melhorias, fique à vontade para:

- Criar uma **issue**
- Enviar um **pull request**
- Compartilhar com colegas que estejam aprendendo Java!

<br />

##  🧑‍💻 Autora

<br />

Desenvolvido por [**Nayara Kiyota**](https://github.com/nayarakiyota)
Para dúvidas, sugestões ou colaborações, entre em contato via GitHub ou abra uma issue!

 📫 [LinkedIn](https://www.linkedin.com/in/nayara-kiyota/)