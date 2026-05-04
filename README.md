# 🛡️ Insurance Management System

## 📌 Overview

Insurance Management System is a backend-driven application designed to manage **insurance policies, customers, and related operations**.
It enables efficient handling of policy data, customer records, and core insurance workflows.

## 🛠️ Tech Stack

* Java
* Spring Boot 
* JDBC / JPA
* MySQL
* REST APIs

## ✨ Features

* 👤 Manage customer details
* 📄 Create and manage insurance policies
* 🔄 Update policy information
* ❌ Delete records
* 🔍 Retrieve policy and customer data
* 🧩 Structured backend architecture

## 📂 Project Structure

```id="q2w7dz"
Insurance-project/
│── src/
│   ├── controller/     # API endpoints 
│   ├── service/        # Business logic
│   ├── repository/     # Database layer
│   ├── model/          # Entity classes
│   └── main/           # Main application class
│── README.md
```

## ⚙️ Installation & Setup

### 1. Clone Repository

```bash id="8n1j9k"
git clone https://github.com/Vinayak7788722/Insurance-project.git
cd Insurance-project
```

---

### 2. Configure Database

Create a MySQL database (e.g., `insurance_db`)

```sql id="m4y1ke"
CREATE TABLE policies (
    id INT PRIMARY KEY AUTO_INCREMENT,
    policy_name VARCHAR(100),
    premium DOUBLE,
    duration INT
);

CREATE TABLE customers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    age INT,
    policy_id INT
);
```

---

### 3. Update Configuration

```properties id="fxt6j9"
spring.datasource.url=jdbc:mysql://localhost:3306/insurance_db
spring.datasource.username=root
spring.datasource.password=your_password
```

---

### 4. Run the Project

```bash id="xg2t4p"
mvn spring-boot:run
```

Runs on: `http://localhost:8080`

---

## 🧪 Sample Response

```json id="1z5f7c"
{
  "id": 1,
  "policyName": "Life Secure Plan",
  "premium": 5000,
  "duration": 10
}
```

---

## 📌 Key Learnings

* Backend system design
* RESTful API development
* Database relationship handling
* Layered architecture (Controller → Service → Repository)

---

## 🚀 Future Improvements

* 🔐 Add authentication & authorization
* 📊 Dashboard for analytics
* 📁 File upload for policy documents
* ☁️ Cloud deployment

---

## 👨‍💻 Author

Vinayak
