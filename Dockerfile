# Usar una imagen base de Java 21 (JDK 21)
FROM openjdk:21-jdk-slim

# Establecer el directorio de trabajo en el contenedor
WORKDIR /app

# Copiar el archivo JAR desde el directorio de tu máquina local a la imagen Docker
COPY target/productos-api-0.0.1-SNAPSHOT.jar /app/productos-api.jar

# Exponer el puerto 8080 (o el puerto que uses en tu aplicación)
EXPOSE 8080

# Comando para ejecutar el JAR
ENTRYPOINT ["java", "-jar", "/app/productos-api.jar"]

