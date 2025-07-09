# 📚 BookVerse Backend

A simple backend application for **BookVerse**, developed using **Java Spring Boot**. This project includes **CRUD operations**, **book ratings**, and **pagination support**. It is designed for managing a digital library system and tested via **Postman**.

---

## 🚀 Features

- 📖 Create, Read, Update, and Delete (CRUD) Books
- ⭐ Add and manage Ratings for each Book
- 📃 Paginate through the list of Books
- 🧪 Fully tested using Postman (no frontend included)

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL (in-memory DB for testing)
- Postman (for manual API testing)

---

## 📁 Project Structure

```
bookverse-backend/
├── controller/       # REST controllers
├── service/          # Business logic
├── repository/       # JPA repositories
├── model/            # Entity classes (Book, Rating, etc.)
├── dto/              # DTOs for data transfer
├── exception/        # Custom exception handlers
└── resources/
    └── application.properties
```

---

## 🧪 Sample API Endpoints

| Method | Endpoint                        | Description               |
|--------|----------------------------------|---------------------------|
| POST   | `/api/books`                    | Add a new book            |
| GET    | `/api/books?page=0&size=5`      | Get paginated books       |
| PUT    | `/api/books/{id}`               | Update book details       |
| DELETE | `/api/books/{id}`               | Delete a book             |
| POST   | `/api/books/{id}/rating`        | Add rating to a book      |

> Test these endpoints using Postman.

---

## ⚙️ Setup Instructions

### Prerequisites

- Java 17 or above
- Maven
- MySQL (or use H2 for in-memory testing)
- Postman (for API testing)

### Clone the Repository

```bash
git clone https://github.com/Birendra99/bookverse-backend.git
cd bookverse-backend
```

### Configure the Database

Edit `src/main/resources/application.properties` for your DB setup.

#### Example for MySQL:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookverse_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

#### Or use H2 for testing:
```properties
spring.datasource.url=jdbc:h2:mem:bookverse
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
```

### Run the Application

```bash
mvn spring-boot:run
```

---

## 🧰 Tools Used

- Spring Tools Suite / IntelliJ IDEA / Eclipse
- Spring Initializr
- MySQL Workbench
- Postman

---

## 📬 Author

**Birendra Bahadur Budha**  
🔗 GitHub: [Birendra99](https://github.com/Birendra99)

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
