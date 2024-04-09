# API de Pagamentos

Esta é uma API de Pagamentos completa desenvolvida com Spring Boot. Ela permite realizar operações de pagamento, estorno e consulta de transações.

## Requisitos

- Java 8 ou superior
- Maven
- Postman (para testar as chamadas HTTP)

## Tecnologias utilizadas

- Spring Boot
- Spring Security
- Spring Validation
- H2 Database
- REST
- JSON

## Configuração

1. Clone este repositório
2. Importe o projeto na sua IDE favorita
3. Execute o projeto como uma aplicação Spring Boot
4. Acesse `http://localhost:8080` para verificar se a aplicação está funcionando corretamente

## Endpoints

### Pagamento

- **Método:** POST
- **URL:** `/pagamentos`
- **Body:** 
  ```json
  {
    "transacao": {
      "cartao": "4441234",
      "id": "100023568900001",
      "descricao": {
        "valor": "500.50",
        "datahora": "01/05/2021 18:30:00",
        "estabelecimento": "PetShop Mundo cão"
      },
      "formaPagamento": {
        "tipo": "AVISTA",
        "parcelas": "1"
      }
    }
  }
  ```
- **Resposta:**
  ```json
  {
    "transacao": {
      "cartao": "4441234",
      "id": "100023568900001",
      "descricao": {
        "valor": "50.00",
        "datahora": "01/05/2021 18:30:00",
        "estabelecimento": "PetShop Mundo cão",
        "nsu": "1234567890",
        "codigoAutorizacao": "147258369",
        "status": "AUTORIZADO"
      },
      "formaPagamento": {
        "tipo": "AVISTA",
        "parcelas": "1"
      }
    }
  }
  ```

### Estorno

- **Método:** POST
- **URL:** `/estornos`
- **Body:** 
  ```json
  {
    "id": "100023568900001"
  }
  ```
- **Resposta:**
  ```json
  {
    "transacao": {
      "cartao": "4441234",
      "id": "100023568900001",
      "descricao": {
        "valor": "50.00",
        "datahora": "01/05/2021 18:30:00",
        "estabelecimento": "PetShop Mundo cão",
        "nsu": "1234567890",
        "codigoAutorizacao": "147258369",
        "status": "CANCELADO"
      },
      "formaPagamento": {
        "tipo": "AVISTA",
        "parcelas": "1"
      }
    }
  }
  ```

### Consulta

- **Método:** GET
- **URL:** `/transacoes`
- **Resposta:**
  ```json
  [
    {
      "transacao": {
        "cartao": "4441234",
        "id": "100023568900001",
        "descricao": {
          "valor": "50.00",
          "datahora": "01/05/2021 18:30:00",
          "estabelecimento": "PetShop Mundo cão",
          "nsu": "1234567890",
          "codigoAutorizacao": "147258369",
          "status": "AUTORIZADO"
        },
        "formaPagamento": {
          "tipo": "AVISTA",
          "parcelas": "1"
        }
      }
    }
  ]
  ```


# Tool Challenge - Coleção Postman

Esta coleção contém requisições para testar a Tool Challenge API.

## Como utilizar

1.  Importe a coleção no Postman que esta na pasta "postman"
2.  Execute as requisições de Usuario 1.0.0 para obter os tokens {{{TOLL_TOKEN}}`e adione o valor no campo "Current Value" que esta na Pasta Geral,depois faça o mesmo procedimento para {{API_TOKEN}}`).
3.  Altere as variáveis de ambiente conforme necessário (ex: `{{host}}`, `{{TOLL_TOKEN}}`, `{{API_TOKEN}}`).
4. Execute as requisições e verifique as respostas.

## Requisições disponíveis

### 1. Cadastro de Usuário

-   **Método:** POST
-   **URL:** `{{host}}/auth/register`
-   **Body:**
    
    jsonCopy code
    
    `{
      "name": "SrMaster",
      "email": "srmaster@tollchallenge.com.br",
      "password": "9876543210"
    }` 
    
-   **Descrição:** Cria um novo usuário com os dados fornecidos no body.

### 2. Autenticar Acesso

-   **Método:** GET
-   **URL:** `{{host}}/usuario`
-   **Descrição:** Retorna os dados do usuário autenticado.

### 3. Login

-   **Método:** POST
-   **URL:** `{{host}}/auth/login`
-   **Body:**
    
    jsonCopy code
    
    `{
      "email": "srmaster@tollchallenge.com.br",
      "password": "9876543210"
    }` 
    
-   **Descrição:** Realiza o login do usuário e retorna um token de autenticação.

### 4. Pagamento

-   **Método:** POST
-   **URL:** `{{host}}/api/pagamentos/pagamento`
-   **Body:**
    
    jsonCopy code
    
    `{
      "transacao": {
        "cartao": "444********1234",
        "id": "100023568900011",
        "descricao": {
          "valor": "1500.50",
          "datahora": "01/05/2021 18:42:00",
          "estabelecimento": "Supermercado PetShop Mundo Cão"
        },
        "formaPagamento": {
          "tipo": "PARCELADO",
          "parcelas": "1"
        }
      }
    }` 
    
-   **Descrição:** Realiza um pagamento com os dados fornecidos.

### 5. Consulta Pagamento por IdCartao

-   **Método:** GET
-   **URL:** `{{host}}/api/pagamentos/consulta/100023568900001`
-   **Descrição:** Consulta os pagamentos relacionados a um cartão específico.

### 6. Consulta Todos Pagamentos

-   **Método:** GET
-   **URL:** `{{host}}/api/pagamentos/consulta`
-   **Descrição:** Consulta todos os pagamentos realizados.

### 7. Estorno

-   **Método:** POST
-   **URL:** `{{host}}/api/pagamentos/estorno/100023568900010`
-   **Descrição:** Realiza um estorno de pagamento com base no ID fornecido.ta coleção contém requisições para testar a API de Pagamentos.
