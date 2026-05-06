📋 Tarefas API
API REST de gerenciamento de tarefas com classificação automática de prioridade por Inteligência Artificial, desenvolvida com Java e Spring Boot.
💡 Sobre o Projeto
O sistema permite cadastrar tarefas e classifica automaticamente a prioridade (ALTA, MÉDIA ou BAIXA) com base no conteúdo da descrição, utilizando a API do Google Gemini (IA generativa). O usuário não precisa definir a prioridade manualmente — o sistema decide sozinho.
✨ Funcionalidades

✅ Criar tarefa com classificação automática de prioridade por IA
✅ Listar todas as tarefas
✅ Atualizar tarefa existente
✅ Deletar tarefa

🛠️ Tecnologias Utilizadas

Java 17+
Spring Boot 3.5
Spring Data JPA
H2 Database (banco em memória)
Google Gemini API (IA para classificação de prioridade)
Maven

🏗️ Arquitetura
O projeto segue a arquitetura em camadas padrão do mercado:
Controller → Service → Repository → Database
src/
├── controller/
│   └── TarefaController.java    # Endpoints da API
├── model/
│   └── Tarefa.java              # Entidade do banco de dados
├── repository/
│   └── TarefaRepository.java   # Acesso ao banco de dados
└── service/
    ├── TarefaService.java       # Lógica de negócio
    └── GeminiService.java       # Integração com IA
🚀 Como Rodar o Projeto
Pré-requisitos

Java 17 ou superior
Maven

Passo a passo

Clone o repositório:

bashgit clone https://github.com/tiagobanzato/tarefas-api.git

Entre na pasta do projeto:

bashcd tarefas-api/tarefas-api

Configure sua chave da API do Gemini no arquivo application.properties:

propertiesgemini.api.key=SUA_CHAVE_AQUI

Obtenha sua chave gratuitamente em aistudio.google.com


Rode o projeto:

bash./mvnw spring-boot:run
A API estará disponível em http://localhost:8080
📡 Endpoints
Criar tarefa
httpPOST /tarefas
Content-Type: application/json

{
  "titulo": "Servidor caiu em produção",
  "descricao": "Sistema fora do ar, clientes não conseguem acessar"
}
Resposta:
json{
  "id": 1,
  "titulo": "Servidor caiu em produção",
  "descricao": "Sistema fora do ar, clientes não conseguem acessar",
  "prioridade": "ALTA"
}
Listar tarefas
httpGET /tarefas
Atualizar tarefa
httpPUT /tarefas/{id}
Content-Type: application/json

{
  "titulo": "Novo título",
  "descricao": "Nova descrição"
}
Deletar tarefa
httpDELETE /tarefas/{id}
🤖 Como a IA Funciona
Ao criar ou atualizar uma tarefa, o sistema envia o título e a descrição para a API do Google Gemini, que analisa o contexto e retorna automaticamente a prioridade:

🔴 ALTA — tarefas urgentes ou críticas
🟡 MÉDIA — tarefas importantes mas sem urgência imediata
🟢 BAIXA — tarefas que podem aguardar

👨‍💻 Autor
Tiago Banzato Rodrigues dos Santos
Estudante de Análise e Desenvolvimento de Sistemas
