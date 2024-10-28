# Etapa 1: Compilação da aplicação
FROM maven:3.8.5-openjdk-17 AS builder

# Diretório de trabalho na imagem Docker
WORKDIR /app

# Copia o arquivo pom.xml e baixa as dependências
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o restante do código fonte
COPY src ./src

# Compila a aplicação e empacota em um JAR
RUN mvn clean package -DskipTests

# Etapa 2: Preparando a imagem final
FROM openjdk:17-jdk-slim-buster AS prod

# Diretório de trabalho na imagem Docker
WORKDIR /app

# Instala o netcat para o script de inicialização
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*

# Copia o JAR da etapa de build
COPY --from=builder /app/target/optmize-jvm-0.0.1-SNAPSHOT.jar app.jar

# Copia o script de inicialização
COPY init.sh /init.sh
RUN chmod +x /init.sh

# Definir variáveis de ambiente (ajuste conforme necessário)
ENV JAVA_OPTS="-XX:+UseG1GC -Xmx512m -Xms256m -Djava.security.egd=file:/dev/./urandom"

# Expor a porta da aplicação
EXPOSE 8080

# Comando para iniciar a aplicação via script de inicialização
ENTRYPOINT ["/init.sh"]
