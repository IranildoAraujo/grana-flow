# Etapa 1: Construção da aplicação (Builder)
FROM openjdk:17-jdk-slim AS builder

# Instalar o Maven
RUN apt-get update && apt-get install -y maven

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo pom.xml e baixar as dependências do Maven
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar o restante do código da aplicação
COPY src /app/src

# Construir a aplicação Spring Boot
RUN mvn clean package -DskipTests

# Etapa 2: Execução (Runtime)
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo jar gerado para o container
COPY --from=builder /app/target/grana-flow-0.0.1-SNAPSHOT.jar /app/grana-flow.jar

# Expôr a porta da aplicação
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/grana-flow.jar"]
