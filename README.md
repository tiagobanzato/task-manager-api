# Task Manager API

Task management REST API with automatic, AI-based priority classification, built with Java and Spring Boot.

## About the project

Users create tasks and the system automatically classifies their priority (**ALTA** / high, **MÉDIA** / medium or **BAIXA** / low) based on the task description, using the Google Gemini API (generative AI). There is no need to set the priority manually: the system decides on its own.

## Features

- Create a task with automatic AI priority classification
- List all tasks
- Update an existing task
- Delete a task

## Tech stack

- **Java 17+**
- **Spring Boot 3.5**
- **Spring Data JPA**
- **H2 Database** (in-memory)
- **Google Gemini API** (AI priority classification)
- **Maven**

## Architecture

The project follows a standard layered architecture:

```
Controller → Service → Repository → Database
```

```
src/
├── controller/
│   └── TarefaController.java    # API endpoints
├── model/
│   └── Tarefa.java              # Database entity
├── repository/
│   └── TarefaRepository.java    # Data access
└── service/
    ├── TarefaService.java       # Business logic
    └── GeminiService.java       # AI integration
```

## Running locally

### Requirements
- Java 17 or later
- Maven

### Steps

1. Clone the repository:
```bash
git clone https://github.com/tiagobanzato/task-manager-api.git
```

2. Go to the project folder:
```bash
cd task-manager-api/tarefas-api
```

3. Add your Gemini API key to `application.properties`:
```properties
gemini.api.key=YOUR_KEY_HERE
```
> You can get a free key at [aistudio.google.com](https://aistudio.google.com)

4. Run the project:
```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## Endpoints

> Field names and priority values are in Portuguese, as defined in the code.

### Create a task
```http
POST /tarefas
Content-Type: application/json

{
  "titulo": "Servidor caiu em produção",
  "descricao": "Sistema fora do ar, clientes não conseguem acessar"
}
```

**Response:**
```json
{
  "id": 1,
  "titulo": "Servidor caiu em produção",
  "descricao": "Sistema fora do ar, clientes não conseguem acessar",
  "prioridade": "ALTA"
}
```

### List tasks
```http
GET /tarefas
```

### Update a task
```http
PUT /tarefas/{id}
Content-Type: application/json

{
  "titulo": "New title",
  "descricao": "New description"
}
```

### Delete a task
```http
DELETE /tarefas/{id}
```

## How the AI works

When a task is created or updated, the system sends its title and description to the **Google Gemini API**, which analyses the context and returns the priority:

- **ALTA** (high): urgent or critical tasks
- **MÉDIA** (medium): important tasks with no immediate urgency
- **BAIXA** (low): tasks that can wait

## Author

**Tiago Banzato Rodrigues dos Santos**
Software developer · Dublin, Ireland
[![GitHub](https://img.shields.io/badge/GitHub-tiagobanzato-black?logo=github)](https://github.com/tiagobanzato)
[![LinkedIn](https://img.shields.io/badge/LinkedIn-banzato-0A66C2?logo=linkedin)](https://www.linkedin.com/in/banzato)
