# 📚 Library Management System

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![MVC Pattern](https://img.shields.io/badge/Architecture-MVC-blue?style=for-the-badge)

A robust Library Management System built in Java using the **Model-View-Controller (MVC)** architectural pattern. This console-based application allows for the efficient management of books, users, and loan transactions while strictly enforcing library rules and utilizing custom exceptions.

---

## 🚀 Project Overview

This system provides a comprehensive solution for daily library operations. It is designed to be scalable and maintainable, dividing the application logic, user interface, and data models into distinct components. 

### ✨ Key Features
* **Book Management:** Tracks ISBN, Title, Author, Year, Publisher, Genre, and Availability.
* **User Management:** Manages user IDs, names, current active loans, and borrowing history.
* **Loan Processing:** Handles borrowing, returning, and reserving books with precise date tracking.
* **Advanced Search:** Find books by Title, ISBN, or Genre.
* **Strict Library Policies Enforced:**
  * Maximum of **3 books** checked out per user at any given time.
  * Maximum loan duration of **30 days**.
  * **7-day cooldown period** enforced before a user can re-borrow the same book.
* **Robust Validation:** Prevents checking out already borrowed/reserved books and handles logic flows using custom Java Exceptions (e.g., `LibroNoDisponibleException`, `LimitePrestamosExcedidoException`).

---

## 🏗️ Architecture (MVC)

The project is structured into logical packages adhering to the MVC pattern:

* **`modelo/` (Model):** Contains the core data classes (`Libro`, `Usuario`, `Prestamo`) and Enums (`Genero`, `EstadoLibro`).
* **`vista/` (View):** Contains `Consola.java`, responsible for all user interaction, displaying menus, and rendering lists.
* **`controlador/` (Controller):** Contains classes like `GestorBiblioteca.java` that handle the business logic, tying the models and the view together.
* **`app/` (Main):** Contains the main executable class to launch the system.

---
