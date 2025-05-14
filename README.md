# 🔍 Service Discovery com Eureka no Projeto de Pagamentos

## ✨ O que é Service Discovery?

Em uma arquitetura de microsserviços, os serviços estão em constante mudança — eles podem escalar horizontalmente, reiniciar ou até mudar de endereço IP. Para que esses serviços consigam se comunicar de forma dinâmica, utilizamos o conceito de **Service Discovery** (Descoberta de Serviços).

Em vez de cada serviço precisar saber o endereço exato dos demais, eles consultam um "catálogo central" que informa onde cada um está rodando. Isso torna o sistema mais **flexível, resiliente e escalável**.

---

## 🗂️ O que é um Service Registry?

Um **Service Registry** (Registro de Serviços) é o componente central que armazena as informações dos serviços ativos. Ele mantém um registro atualizado com os nomes dos serviços, seus endereços (host:porta) e outros metadados.

Os serviços **se registram automaticamente** no registry ao iniciar, e podem **consultar outros serviços** para descobrir como se comunicar com eles.

---

## 🚀 Eureka como Service Registry

[Eureka](https://github.com/Netflix/eureka) é um projeto da Netflix que funciona como um Service Registry. Ele permite que microsserviços Spring Boot se registrem automaticamente e descubram outros serviços registrados.

Neste projeto, estou utilizando o **Eureka Server (versão 3.1)** como registry e o **Spring Boot 2.6.x** nos microsserviços, o que garante compatibilidade com a versão do Spring Cloud utilizada.

---

## 🧾 Aplicação prática no projeto de pagamentos

Neste projeto de arquitetura baseada em microsserviços, temos o serviço de **pagamentos** que precisa se comunicar com outros serviços, como pedidos, estoque, etc.

### ✔️ Tecnologias utilizadas:

- Spring Boot 2.6.x
- Spring Cloud Netflix Eureka Client 3.1.2
- Eureka Server
- Java 17

### 🏗️ Estrutura:

- **Eureka Server**: atua como o **Service Registry**, centralizando as informações dos serviços registrados.
- **pagamentos-service**: registra-se no Eureka automaticamente usando a anotação `@EnableEurekaClient` e as configurações do `application.properties`.

### 🔧 Configuração básica do cliente Eureka:

```properties
spring.application.name=pagamentos-service
server.port=8082

eureka.client.register-with-eureka=true
eureka.client.fetch-registry=true
eureka.client.service-url.defaultZone=http://localhost:8081/eureka```

## 💡 O que acontece na prática?

- Ao iniciar, o `pagamentos-service` se **registra no Eureka Server**.
- O Eureka Server mantém o **nome e endereço** desse serviço em sua lista de registros ativos.
- Outros microsserviços podem **descobrir o `pagamentos-service`** consultando o Eureka pelo nome.

---

## 📌 Benefícios dessa abordagem

- **Escalabilidade**: múltiplas instâncias de um serviço são automaticamente registradas.
- **Tolerância a falhas**: se uma instância cair, o Eureka a remove da lista após o timeout.
- **Desacoplamento**: os serviços não precisam conhecer os IPs uns dos outros.

