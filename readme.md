# 🧠 Spring AI Integration - Recipe Suggestion API

This project is a small REST API built with **Spring Boot** and **Spring AI**, which allows recipe generation using language models like OpenAI (GPT). The application exposes several endpoints to interact with an AI model that suggests recipes based on ingredients and other parameters.

---

## ⚙️ Technologies Used

- Java 21  
- Spring Boot  
- Spring AI (`ChatClient`, `PromptTemplate`, `OutputConverters`)  
- OpenAI API (or any compatible model)  
- SLF4J + Logback for logging  
- Maven (or Gradle)  

---

## 🔌 Available Endpoints

### `/recipes/suggester`

| Endpoint                     | Description                                                                |
|-----------------------------|----------------------------------------------------------------------------|
| `GET /suggest-recipe`       | Returns a list of 10 recipes with a given ingredient (`List<String>`)     |
| `GET /country`              | Returns a map `country -> dish` with dishes from different countries       |
| `GET /best`                 | Returns the best dish as a `Recipe` object                                 |
| `GET /best-list`            | Returns a list of the best dishes as `List<Recipe>`                        |

### Example usage
```http
GET /recipes/suggester/suggest-recipe?ingredient=tomato