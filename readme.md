# 🧠 Spring AI Integration - Recipe Suggestion API

Este proyecto es una pequeña API REST construida con **Spring Boot** y **Spring AI**, que permite generar recetas usando modelos de lenguaje como OpenAI (GPT). La aplicación expone varios endpoints para interactuar con un modelo de IA que sugiere recetas en función de ingredientes y otros parámetros.

---

## ⚙️ Tecnologías utilizadas

- Java 21
- Spring Boot
- Spring AI (`ChatClient`, `PromptTemplate`, `OutputConverters`)
- OpenAI API (o cualquier modelo compatible)
- SLF4J + Logback para logging
- Maven (o Gradle)

---

## 🔌 Endpoints disponibles

### `/recipes/suggester`

| Endpoint                          | Descripción                                                                 |
|----------------------------------|-----------------------------------------------------------------------------|
| `GET /suggest-recipe`           | Devuelve una lista de 10 recetas con un ingrediente dado (`List<String>`) |
| `GET /country`                  | Devuelve un mapa `country -> dish` con platos de diferentes países        |
| `GET /best`                     | Devuelve el mejor plato como objeto `Recipe`                               |
| `GET /best-list`                | Devuelve una lista de los mejores platos como `List<Recipe>`              |

Ejemplo de uso:
```http
GET /recipes/suggester/suggest-recipe?ingredient=tomato