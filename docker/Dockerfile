
# ============================================
# ETAPA 1: COMPILACIÓN
# ============================================
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Maven Wrapper
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

RUN chmod +x mvnw

# Código fuente
COPY src ./src

# Compilar con Java 21
RUN ./mvnw clean package -DskipTests -B

# ============================================
# ETAPA 2: EJECUCIÓN
# ============================================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Usuario no privilegiado
RUN useradd -r -u 10001 spring

# Aplicación compilada
COPY --from=builder /app/target/*.jar app.jar

USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
