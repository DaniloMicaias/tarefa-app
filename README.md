# 📝 TarefaApp



## 🚀 Tecnologias Utilizadas

- ✅ **Java 21**
- ✅ **Spring Boot 3**
  - Spring Web
  - Spring Security (JWT)
  - Spring Data JPA
- ✅ **Angular**
- ✅ **PostgreSQL**
- ✅ **Docker & Docker Compose**
- ✅ **JUnit** para testes
- ✅ **Maven** como gerenciador de dependências

---

## 📂 Estrutura do Projeto

```
tarefa-app/
│
├── docker-compose.yml        # Orquestra backend, frontend e banco
├── init.sql                  # Script de inicialização do banco
│
└── backend/                  # Backend em Spring Boot
│
└── frontend/                 # Frontend em Angular

```

---

## ⚙️ Configuração do Projeto

### Pré-requisitos

Certifique-se de ter instalado:

- [Docker](https://www.docker.com/)
- [Java 21](https://adoptium.net/pt-br/)
- [Maven](https://maven.apache.org/)
- (Opcional) [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou outro IDE Java

---

## 🐳 Rodando com Docker

### 1. Suba a aplicação com um único comando:

```bash
docker-compose up --build
```

O Docker Compose irá:

- Subir o banco de dados PostgreSQL
- Rodar o script `init.sql` com estrutura base
- Construir o backend Java e o frontend Angular e executá-los

### 2. Acesse a API

Após o build, a API estará disponível em:

```
http://localhost:8080
```

---

## 🔐 Autenticação

A aplicação utiliza JWT (JSON Web Token) para autenticação.

### Endpoints:

- `POST /auth/login` – Recebe `login` e `senha`, e retorna um token.
- Endpoints protegidos exigem o header:

```
Authorization: Bearer <token>
```

---

## 📌 Endpoints Principais

- `POST /auth/login` — Autenticação
- `GET /tarefas` — Listar tarefas
- `POST /tarefas` — Criar nova tarefa
- `PUT /tarefas/{id}` — Atualizar tarefa
- `DELETE /tarefas/{id}` — Excluir tarefa

Também pode ser acessado via:
- [Swagger](http://localhost:8080/swagger-ui/index.html)

---

## Acesso ao Frontend

Após o build, o frontend estará disponível em:

```
http://localhost:4200
```

## 🧪 Rodando os Testes

Se quiser rodar os testes localmente:

```bash
cd backend
./mvnw test
```

---

## 📦 Build Manual (sem Docker)

```bash
cd backend
./mvnw clean package
java -jar target/*.jar
```

---

## 🧠 Autor

Desenvolvido por **Danilo Micaías** como desafio técnico.

---