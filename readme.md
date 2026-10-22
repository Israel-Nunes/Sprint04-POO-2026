# Sprint 4 — Motiva

API REST desenvolvida em Java com Spring Boot para gerenciamento de trechos rodoviários, equipes de manutenção, intervenções operacionais e relatórios de prioridade, utilizando Oracle Database e Spring Data JPA.

## Integrantes do grupo

| Nome completo | RM |
|---|---:|
| Thiago Sobral de Alvarenga | 562695 |
| Pedro Miranda Campos Riato | 562117 |
| Israel Karacsony de Camargo Nunes | 563435 |
| Diego Antonio Silva Mendes | 565509 |
| Giovanni de Lela Anjos Costa | 563066 |
| Gabriel Hiro Nakamura | 562221 |

## Tecnologias utilizadas

- Java 17
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA
- Jakarta Bean Validation
- Oracle Database
- Maven
- JUnit e Spring Boot Test

## Funcionalidades

- CRUD de trechos rodoviários.
- CRUD de equipes de manutenção.
- CRUD de intervenções operacionais.
- Cálculo do crescimento estimado da vegetação.
- Classificação de prioridade dos trechos.
- Geração e consulta de relatórios de prioridade.
- Consulta do histórico de relatórios e filtragem por período.
- Validação dos dados recebidos pela API.
- Tratamento de erros com respostas HTTP apropriadas.

## Pré-requisitos

Antes de executar o projeto, é necessário ter:

- JDK 17 ou superior.
- Acesso ao Oracle Database da FIAP.
- Git, caso deseje clonar o repositório.

## Configuração do banco de dados

O projeto utiliza o Oracle Database da FIAP. Configure as variáveis de ambiente `ORACLE_USER` e `ORACLE_PASSWORD` com suas credenciais do banco.

No PowerShell, para a sessão atual:

```powershell
$env:ORACLE_USER="seu_usuario"
$env:ORACLE_PASSWORD="sua_senha"
```

Substitua os valores pelos seus dados reais. Não compartilhe suas credenciais nem as envie ao GitHub.

A conexão está configurada em `src/main/resources/application.properties`. O Hibernate utiliza `ddl-auto=validate`, portanto o esquema do banco deve existir e ser compatível com as entidades mapeadas.

## Como executar

Na pasta raiz do projeto, execute:

```powershell
.\mvnw.cmd spring-boot:run
```

Por padrão, a aplicação fica disponível em:

`http://localhost:8080`

## Endpoints da API

Todas as rotas utilizam o prefixo `/api`.

| Recurso | Método | Endpoint | Descrição |
|---|---|---|---|
| Trechos | GET | `/api/trechos` | Lista os trechos rodoviários |
| Trechos | GET | `/api/trechos/{id}` | Busca um trecho por ID |
| Trechos | POST | `/api/trechos` | Cadastra um trecho |
| Trechos | PUT | `/api/trechos/{id}` | Atualiza um trecho |
| Trechos | DELETE | `/api/trechos/{id}` | Exclui um trecho |
| Equipes | GET | `/api/equipes` | Lista as equipes de manutenção |
| Equipes | GET | `/api/equipes/{id}` | Busca uma equipe por ID |
| Equipes | POST | `/api/equipes` | Cadastra uma equipe |
| Equipes | PUT | `/api/equipes/{id}` | Atualiza uma equipe |
| Equipes | DELETE | `/api/equipes/{id}` | Exclui uma equipe |
| Intervenções | GET | `/api/intervencoes` | Lista as intervenções |
| Intervenções | GET | `/api/intervencoes/{id}` | Busca uma intervenção por ID |
| Intervenções | POST | `/api/intervencoes` | Cadastra uma intervenção |
| Intervenções | PUT | `/api/intervencoes/{id}` | Atualiza uma intervenção |
| Intervenções | DELETE | `/api/intervencoes/{id}` | Exclui uma intervenção |
| Relatórios | GET | `/api/relatorios` | Consulta o histórico de relatórios |
| Relatórios | POST | `/api/relatorios` | Gera um novo relatório |
| Relatórios | GET | `/api/relatorios/periodo` | Consulta relatórios por período |

## Exemplos de requisições

Os exemplos abaixo podem ser executados com a aplicação iniciada.

### Cadastrar um trecho rodoviário

```bash
curl -X POST http://localhost:8080/api/trechos \
  -H "Content-Type: application/json" \
  -d '{"km":55,"alturaVegetacao":35,"areaUmida":false}'
```

### Cadastrar uma equipe de manutenção

```bash
curl -X POST http://localhost:8080/api/equipes \
  -H "Content-Type: application/json" \
  -d '{"nome":"Equipe de Manutencao","tipo":"ROCADA"}'
```

### Cadastrar uma intervenção operacional

```bash
curl -X POST http://localhost:8080/api/intervencoes \
  -H "Content-Type: application/json" \
  -d '{"tipo":"ROCADA_MECANIZADA","custoEstimado":1500,"idEquipe":1}'
```

O valor de `idEquipe` deve corresponder a uma equipe existente.

### Gerar um relatório de prioridade

```bash
curl -X POST http://localhost:8080/api/relatorios \
  -H "Content-Type: application/json" \
  -d '{}'
```

### Consultar o histórico de relatórios

```bash
curl http://localhost:8080/api/relatorios
```

### Consultar relatórios por período

O período utiliza data e hora no formato ISO 8601:

```bash
curl "http://localhost:8080/api/relatorios/periodo?inicio=2026-10-01T00:00:00&fim=2026-10-31T23:59:59"
```

## Classificação de prioridade

O relatório classifica os trechos com base no crescimento estimado da vegetação:

| Prioridade | Crescimento calculado |
|---|---:|
| URGENTE | Maior ou igual a 80 cm |
| CRÍTICO | Maior ou igual a 50 cm e menor que 80 cm |
| ATENÇÃO | Maior ou igual a 25 cm e menor que 50 cm |
| NORMAL | Menor que 25 cm |

O cálculo considera a condição da área: o fator utilizado é 1,5 para áreas úmidas e 1,1 para áreas secas.

## Códigos HTTP

| Código | Significado |
|---|---|
| 200 OK | Requisição processada com sucesso |
| 201 Created | Recurso criado |
| 204 No Content | Recurso excluído com sucesso |
| 400 Bad Request | Dados inválidos ou requisição incorreta |
| 404 Not Found | Recurso não encontrado |
| 500 Internal Server Error | Erro interno do servidor |

## Executar os testes

Para executar a suíte de testes:

```powershell
.\mvnw.cmd clean test
```

Os testes cobrem o cálculo de crescimento da vegetação, a classificação de prioridade, a inicialização da aplicação e validações da API.

## Arquitetura do projeto

O código está organizado em camadas:

- **Controller:** recebe as requisições HTTP e retorna as respostas.
- **DTO:** define e valida os dados recebidos pela API.
- **Service:** concentra as regras de negócio e as validações.
- **Repository:** realiza o acesso aos dados por meio do Spring Data JPA.
- **Model:** contém as entidades mapeadas para as tabelas do Oracle.
- **Exception:** centraliza o tratamento de erros da aplicação.

## Versionamento

O projeto utiliza Git para controle de versão. As alterações são registradas em commits e enviadas ao repositório remoto no GitHub.

As credenciais do banco de dados devem permanecer em variáveis de ambiente e nunca ser versionadas.

---
## Perguntas de reflexão

### 1. Por que o Repository é uma interface? Quem implementa e quando?

O Repository é uma interface porque define as operações de acesso aos dados sem precisar implementar manualmente cada uma delas. O Spring Data JPA gera uma implementação em tempo de execução, quando a aplicação é inicializada, disponibilizando os métodos definidos pela interface.

### 2. O pattern DAO morreu na migração?

Não. O conceito continua existindo, mas passou a ser atendido pelo Repository do Spring Data JPA. Na Sprint 3, precisávamos escrever o código JDBC para acessar o banco. Na Sprint 4, o framework fornece a implementação do acesso aos dados, reduzindo o código necessário.

### 3. Por que validar nivelVegetacao >= 0 no Service e não apenas no Controller?

Porque a regra de negócio deve ser protegida independentemente de como o sistema é acessado. O Controller valida os dados da requisição, enquanto o Service garante que as regras sejam respeitadas em todas as operações que executam a lógica de negócio. Assim, outros componentes também não conseguem ignorar a regra simplesmente chamando o Service com dados inválidos.

### 4. Onde está o SQL do findByTipo()? Quem o gerou?

O SQL não é escrito manualmente no método. O Spring Data JPA interpreta o nome `findByTipo()` e gera a consulta necessária com base na propriedade `tipo` da entidade. O Hibernate e o provedor JPA executam a consulta no banco de dados.

---
**Projeto acadêmico — FIAP | Sprint 4**
