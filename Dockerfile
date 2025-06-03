# Usa una imagen base de Java
FROM openjdk:17-jdk-slim

# Establece el directorio de trabajo
WORKDIR /app

# Copia el archivo JAR al contenedor (ajusta el nombre del jar si es diferente)
COPY target/wonderlands-api-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto (ajusta según tu app)
EXPOSE 8080

# Comando para ejecutar la app
CMD ["java", "-jar", "app.jar"]