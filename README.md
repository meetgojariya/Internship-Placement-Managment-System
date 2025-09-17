# 🎓 Internship & Placement Management System (IPMS)

## 📖 About Project

The **Internship & Placement Management System (IPMS)** is a **Java + MySQL** based console application designed to streamline the internship and placement process for **students**, **companies**, and **administrators**. The system provides a unified platform where:

- 🎓 Students can register, maintain profiles, and apply for job/internship opportunities.
- 🏢 Companies can post vacancies, manage applications, and schedule interviews.
- 👨‍💼 Admins can monitor and manage student and company records.

This project demonstrates:

- Core **Java programming**
- **JDBC** database connectivity
- Structured **modular development**
- Database design with **ER diagram**, **schema**, and **system flow documentation**

---

## 📌 Core Features

### 👨‍💻 Admin
- View all students  
- View all companies  
- Search student  
- Search company  
- Delete student  
- Delete company  

---

### 🎓 Student
- Student registration  
- Student login  
- View your profile  
- Update profile  
- Apply to company  
- View applications  
- Export data  
- Delete profile  

---

### 🏢 Company
- Company registration  
- Company login  
- Post internship/job vacancy  
- View applications  
- Edit company profile  
- Shortlist students  
- Schedule interview  
- Delete company profile  

---

## 🛠 Tech Stack

- **Language:** Java  
- **Database:** MySQL  
- **Connectivity:** JDBC (Java Database Connectivity)  

---

## 📂 Project Structure

```text
Internship-Placement-Management-System/
│
├── src/                      # Source code
│   ├── Main.java             # Entry point of the application
│   └── IPMS/                 # Core package containing modules
│
├── db/                       # Database related files
│   ├── schema.sql            # Database schema (tables & relations)
│   └── sample_data.sql       # Sample data for testing
│
├── docs/                     # Documentation files
│   ├── ER_Diagram.png        # Entity Relationship Diagram
│   └── Flowchart.png         # Flowchart of the system
│
├── .gitignore                # Ignored files & folders  
└── README.md                 # Project documentation (this file)


```
## ⚙️ Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK)** – Version 22  
- **MySQL Server** – A running instance of the PostgreSQL database server  
- **MySQL Connector** – Download the `.jar` file from the official [MySQL website](https://dev.mysql.com/downloads/connector/j/)

---

## 🗄️ Database Setup Guide

This project uses a MySQL database. Follow these steps to set it up using the terminal and `psql`:

### **Step 1: Create the Database**

```bash
psql -U your_postgres_username
```

```sql
CREATE DATABASE project;
```

### **Step 2: Build the Database Schema**

Navigate to your project directory and run:

```bash
psql -U your_mysql_username -d project -f db/schema.sql
```

### **Step 3: Seed the Database with Initial Data (Optional)**

```bash
psql -U your_mysql_username -d project -f db/sample_data.sql
```

Your database is now fully configured and ready to connect to the application.

---

## 🚀 How to Run the Project

### **Step 1: Clone the Repository**

```bash
git clone https://github.com/meetgojariya/Internship-Placement-Management-System.git
cd Internship-Placement-Management-System
```

### **Step 2: Open the Project in Your Java IDE**

Import the folder as a project in IntelliJ IDEA, Eclipse, or any IDE of your choice.

### **Step 3: Add PostgreSQL JDBC Driver to Project**

- Add the downloaded JDBC `.jar` to your project's build path.

### **Step 4: Configure the Database Connection**

In your database connection file (e.g., `DBConnection.java`), update:

```java
String url = "jdbc:mysql://localhost:3306/project";
String username = "your_mysql_username";
String password = "your_mysql_password";
```

### **Step 5: Compile and Run the Project**

#### From Terminal:

```bash
javac -cp "lib/mysql-connector-java-<version>.jar:src" src/Main.java
java -cp "lib/mysql-connector-java-<version>.jar:src" Main
```

> Replace `<version>` with the actual version of the PostgreSQL JDBC driver.

#### Or From IDE:

- Right-click `Main.java` and select **Run**

---

## 🤝 How to Contribute

Contributions are **always welcome!** If you'd like to improve this project, please follow these simple steps:

1. **Fork the repository**  
   Click the **Fork** button at the top right corner of the GitHub page.

2. **Create a new branch** for your feature or bugfix  
   ```bash
   git checkout -b feature/YourFeatureName
   ```

3. **Commit your changes** with a clear message  
   ```bash
   git commit -m "Add: YourFeatureName"
   ```

4. **Push the branch** to your fork  
   ```bash
   git push origin feature/YourFeatureName
   ```

5. **Open a Pull Request**  
   Go to the original repository and open a PR from your branch.
---
## 🤝 Contributor

Meet Gojariya
