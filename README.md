## API DE DAPARTAMENTO DE POLICIA

---

## 📝Objetivo:
Em uma situação Hipotética onde uma departamento de policia precisa cadastrar seus agentes/casos abre uma deixa imperdivel para a PoliceDepartamentApi, 
porque não só damos a possibilidade de cadastrar novos agentes quanto casos e fazer ligação entre agente/caso.

## 🤔Funcionamento:

A  api funciona com fornecimento manual de dados sobre os agentes e sobre os casos, porem a ligação entre caso e agente é feita automaticante via ID do agente.

Também é possivel fazer todas operações básicas como excluir,adicionar,editar,etc... além disso todos dados são salvos em um banco de dados que previne que sejam perdidos dados importantes.

## 🙋Como rodar:
1-Primeiramente precisamos de um database, no nosso caso precisa ter o Postgres baixado

2-O application.properties deve seguir esse padrao para poder se conectar
```
spring.application.name=SEU-BANCO-DE-DADOS
spring.datasource.url=jdbc:postgresql://localhost:5432/SEU-BANCO-DE-DADOS
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.datasource.driver-class-name=org.postgresql.Driver
spring.datasource.username=SEU-USERNAME
spring.datasource.password=SUA-SENHA
spring.jpa.hibernate.ddl-auto=update
logging.level.org.hibernate.SQL=DEBUG

```

3-E deve possuir essas dependecias(Basta colocar no arquivo gradle)
```
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
  	implementation 'org.springframework.boot:spring-boot-starter-web'
  	compileOnly 'org.projectlombok:lombok'
  	runtimeOnly 'com.h2database:h2'
  	annotationProcessor 'org.projectlombok:lombok'
  	testImplementation 'org.springframework.boot:spring-boot-starter-test'
  	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
  	implementation 'org.modelmapper:modelmapper:3.2.0'
  	implementation'org.springframework.boot:spring-boot-starter-validation'
  	implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9'
  	implementation 'org.postgresql:postgresql:42.7.7'
```

### 📌 Exemplos Json

Json Padrão Agente
```json
  "agente_id": 0,
  "name": "string",
  "dataDeIncorporacao": "2025-08-06",
  "cargo": "string"

```

Json padrão casos
```Json
  	{
  "case_id": 0,
  "titulo": "string",
  "descricao": "string",
  "status": "ABERTO",
  "agente_id": 0
}
```

# Agora é só rodar👌

---

## 😉Importante: 
  Esse é o endereço para rodar o swagger para testar quando tiver tudo instalando http://localhost:SUA-PORTA/swagger-ui/index.html#/

  ## 🚨 PROJETO PARA FINS ACADEMICOS, NÃO POSSUI TESTES UNITARIOS NEM 100% DE VALIDAÇÕES DE RETORNO
  Projeto somente para entendimento de como funciona o Spring e para me familiarizar com criações de APIS em Java.
