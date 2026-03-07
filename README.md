# 🛒 NEXUS E-Commerce — Spring Boot + MySQL

A full-stack e-commerce web application built with **Java Spring Boot**, **MySQL**, and **Thymeleaf**.

---

## 📦 Features
- 8 product categories with 170+ products auto-seeded
- User registration & login (Spring Security)
- Shopping cart (session-based)
- Product search & category filtering
- Order placement & order history
- Admin dashboard (manage products, orders, users)
- Responsive dark-themed UI

---

## ⚙️ Tech Stack
| Layer | Tech |
|---|---|
| Backend | Java 17, Spring Boot 3.2 |
| Database | MySQL 8+ |
| ORM | Spring Data JPA / Hibernate |
| Templates | Thymeleaf |
| Security | Spring Security |
| Build Tool | Maven |

---

## 🚀 Getting Started

### 1. Prerequisites
- Java JDK 17+ → https://adoptium.net
- MySQL 8+ → https://dev.mysql.com/downloads/
- Maven (or use IntelliJ which bundles it)
- VS Code with:
  - Extension Pack for Java
  - Spring Boot Extension Pack

### 2. Configure MySQL
Open MySQL Workbench (or any MySQL client) and run:
```sql
CREATE DATABASE nexus_db;
```

### 3. Set your password
Open `src/main/resources/application.properties` and update:
```properties
spring.datasource.password=your_mysql_password_here
```

### 4. Run the project

**Option A — VS Code:**
1. Open the `nexus-ecommerce` folder in VS Code
2. Wait for Java to index the project
3. Click `Run` on the `EcommerceApplication.java` file
   OR press `F5`

**Option B — Terminal:**
```bash
cd nexus-ecommerce
./mvnw spring-boot:run
```

**Option C — Maven:**
```bash
mvn spring-boot:run
```

### 5. Open in browser
```
http://localhost:8080
```

The database will auto-create all tables and seed all products on first run! ✅

---

## 🔐 Demo Accounts

| Role | Email | Password |
|---|---|---|
| Customer | john@example.com | user123 |
| Admin | admin@nexus.com | admin123 |

---

## 📁 Project Structure
```
src/main/java/com/nexus/ecommerce/
├── config/
│   ├── SecurityConfig.java       ← Login/auth setup
│   └── DataSeeder.java           ← Auto-seeds all 170+ products
├── controller/
│   ├── ShopController.java       ← Home, shop, product detail
│   ├── CartController.java       ← Cart add/update/remove
│   ├── AuthController.java       ← Login/register
│   ├── OrderController.java      ← Checkout, orders
│   └── AdminController.java      ← Admin dashboard
├── model/
│   ├── User.java
│   ├── Product.java
│   ├── Category.java
│   ├── Order.java
│   └── OrderItem.java
├── repository/                   ← JPA database queries
├── service/                      ← Business logic
└── dto/                          ← Data transfer objects

src/main/resources/
├── templates/                    ← Thymeleaf HTML pages
│   ├── index.html                ← Homepage
│   ├── layout.html               ← Shared nav + footer
│   ├── shop/                     ← Shop, cart, checkout, orders
│   ├── auth/                     ← Login, register
│   └── admin/                    ← Admin pages
├── static/
│   ├── css/style.css             ← All styles
│   └── js/main.js                ← Client-side interactions
└── application.properties        ← DB config
```

---

## 🌐 URL Routes
| URL | Description |
|---|---|
| / | Homepage |
| /shop | All products |
| /shop?category=electronics | Filter by category |
| /shop?q=headphones | Search products |
| /product/{id} | Product detail |
| /cart | Shopping cart |
| /checkout | Checkout |
| /orders | My orders |
| /login | Login |
| /register | Register |
| /admin | Admin dashboard |
| /admin/products | Manage products |
| /admin/orders | Manage orders |

---

## 🛠️ Common Issues

**"Access denied for user root"**
→ Wrong MySQL password. Update `application.properties`

**Port 8080 already in use**
→ Change `server.port=8081` in `application.properties`

**Build errors in VS Code**
→ Make sure Java 17+ is installed and JAVA_HOME is set

---

Built with ❤️ — End of Semester Java Project
