# Etapa 1: Compilar a aplicação
FROM maven:3.9.7-eclipse-temurin-21-alpine AS build

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo pom.xml e os arquivos de dependências para o diretório de trabalho
COPY pom.xml ./
COPY src ./src

# Compila a aplicação
RUN mvn package

# Etapa 2: Criar a imagem final para execução
FROM eclipse-temurin:21-jre-alpine

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Cria um argumento para o nome da aplicação
ARG JAR_FILE=target/*.jar

COPY opentelemetry-javaagent.jar /otel/otel.jar

ENV \
  OTEL_SERVICE_NAME=third-microservice \
  OTEL_EXPORTER_OTLP_ENDPOINT=http://host.docker.internal:4318 \
  OTEL_EXPORTER_OTLP_PROTOCOL=http/protobuf \
  OTEL_METRICS_EXPORTER=none \
  OTEL_LOGS_EXPORTER=none \
  JAVA_TOOL_OPTIONS="-javaagent:/otel/otel.jar"

# Copia o jar compilado da etapa anterior
COPY --from=build /app/${JAR_FILE} app.jar

# Expõe a porta da aplicação
EXPOSE 8080

# Define o comando padrão para rodar a aplicação
CMD ["java", "-jar", "app.jar"]