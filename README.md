# 🚀 Todo List API (Spring Boot)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![H2](https://img.shields.io/badge/H2-database-blue?style=for-the-badge)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

Профессиональное REST API для управления задачами с полной документацией и встроенной базой данных.

## 📦 Особенности

- **Полноценный CRUD** для задач
- **Встроенная H2** с веб-интерфейсом
- **Swagger UI** документация

## 🏁 Быстрый старт

### Требования
- Java 17+
- Gradle 
- Spring Boot 3.1.0+

### Установка
```bash
git clone https://github.com/your-username/todo-list-api.git
cd todo-list-api
mvn clean install
java -jar target/todo-list-*.jar
```
### Пример запроса
```bash
curl -X POST http://localhost:8080/api/tasks \
-H "Content-Type: application/json" \
-d '{"title":"Демо задача","description":"Пример"}'
```

### Интерфейсы
-**H2 Console**
  ```text
  http://localhost:8080/h2-console
  ```
-**Swagger UI**
  ```text
  http://localhost:8080/swagger-ui.html
  ```
