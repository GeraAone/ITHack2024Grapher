# Исходный образ
FROM maven:3.8.4-openjdk-17-slim AS build

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы проекта в контейнер
COPY pom.xml .
COPY src src

# Собираем приложение
RUN mvn clean package

# Имя исходного образа для запуска приложения
FROM openjdk:17.0.2-jdk-slim-buster

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем собранное приложение из исходного образа
COPY --from=build /app/target/grapher.jar ./app.jar

# Определяем порт, на котором будет работать приложение
EXPOSE 8080

# Команда для запуска приложения
CMD ["java", "-jar", "app.jar"]