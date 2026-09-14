# Projeto Java Sprint 3

Aplicação Java para gerenciar vendedores, clientes e vendas usando JDBC, Oracle Database e interfaces simples com `JOptionPane`.

## Funcionalidades

- CRUD de vendedores, clientes e vendas para o manager.

## Requisitos

- Java 21
- Maven
- Acesso ao banco Oracle da FIAP
- VPN ou rede necessária para acessar o banco

## Como rodar

Na raiz do projeto, execute o fluxo do manager:

```bash
mvn compile exec:java -Dexec.mainClass=br.com.fiap.main.TesteCadastroManager
```

Para executar o fluxo do vendedor:

```bash
mvn compile exec:java -Dexec.mainClass=br.com.fiap.main.TesteCadastroSalesman
```

Para apenas compilar e validar o projeto:

```bash
mvn test
```

# RMS

- João Vitor Piccolo | RM 565127

- Gabrielle Calazans | RM 564460

- Jéssica Domingues | RM 562973

- Kauã Carvalho | RM 566371

- Leonardo Pereira | RM 561349
