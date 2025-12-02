# Leads UI - Product Management Application

A modern, feature-rich Angular application for managing products with dual data source support (Fake Store API and Database API). Built with Angular 20.3 and Bootstrap 5, this application provides a clean and responsive interface for product management operations.

# Leads Server - Product Management REST API

A robust Spring Boot backend server providing RESTful APIs for product management with JWT-based authentication and dual data source support. This server acts as a backend for product management applications, offering both mock data (Fake Store API) and database persistence.

## 🚀 Features

### Authentication & Security
### Angular
- **JWT-based Authentication** - Secure login with token-based session management
- **Dual Login Modes** - Support for both Fake Store API and Database API authentication
- **Route Guards** - Protected routes with `secureGuard` to ensure authenticated access
- **HTTP Interceptors** - Automatic token injection for authenticated API requests

### Spring Boot
- **JWT-based Authentication** - Secure token-based authentication using JSON Web Tokens
- **Spring Security Integration** - Built-in security with Spring Security framework
- **Dual Authentication Endpoints** - Support for both Fake Store and database authentication
- **Token-based Session Management** - Stateless authentication for scalability

### Product Management
### Angular
- **Product Listing** - Browse and view all products with detailed information
- **Product CRUD Operations**
  - Create new products
  - View product details
  - Update existing products
  - Delete products
- **Category Filtering** - Filter products by category
- **Rating System** - Display product ratings and review counts
### Dual Data Source Support
The application seamlessly integrates with two backend APIs:
- **Fake Store API** (`http://localhost:8080/api/fake-store`) - Mock data for testing
- **Database API** (`http://localhost:8080/api/app`) - Production database integration

### Spring Boot APIs
- **CRUD Operations** - Complete Create, Read, Update, Delete operations for products
- **Category Filtering** - Filter products by category
- **Product Rating System** - Support for product ratings and reviews
- **Dual Data Sources** - Seamless switching between mock data and database

### Dual API Support
The server provides two sets of APIs:
- **Fake Store API** (`/api/fake-store`) - Mock data for testing and development
- **Database API** (`/api/app`) - Production-ready database persistence

## 🛠️ Technology Stack

### Frontend Framework
- **Angular 20.3.0** - Latest Angular framework
- **TypeScript 5.9.2** - Type-safe JavaScript
- **RxJS 7.8.0** - Reactive programming with Observables

### UI & Styling
- **Bootstrap 5.3.8** - Responsive design framework
- **Bootstrap Icons 1.13.1** - Icon library
- **ng-bootstrap 19.0.1** - Angular-powered Bootstrap components
- **SweetAlert2 11.26.3** - Beautiful, responsive alerts and modals

### Testing
- **Jasmine 5.9.0** - Behavior-driven testing framework
- **Karma 6.4.0** - Test runner

### Core Framework
- **Spring Boot 4.0.0** - Modern Spring Boot framework
- **Java 25** - Latest Java LTS version
- **Maven** - Dependency management and build tool

### Dependencies
- **Spring Data JPA** - Database access and ORM
- **Spring Security** - Authentication and authorization
- **Spring Web MVC** - RESTful web services
- **MySQL Connector** - MySQL database driver
- **JJWT 0.13.0** - JWT token generation and validation

### Database
- **MySQL** - Primary database (configurable via profiles)
- **JPA/Hibernate** - ORM framework

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

### Frontend

- **Node.js 22.10.0** or higher
- **npm 9.16.0** or higher
- **Angular CLI 17.0.0 or higher

### Backend

- **Java 25** or higher
- **Maven 3.6+** or use included Maven wrapper (`mvnw`)
- **MySQL Server** (if using database profile)

## 🔧 Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd server
   ```

2. **Configure Database** (for database profile)
   
   Create a MySQL database and update the configuration in `application-db.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Build the project**
   ```bash
   ./mvnw clean install
   # or on Windows
   mvnw.cmd clean install
   ```

3. **Configure Backend API**
   
   Ensure your backend server is running on `http://localhost:8080` with the following endpoints available:
   - **Authentication**: `/api/fake-store/login` or `/api/public/login`
   - **Products**: `/api/fake-store/products` or `/api/app/products`

## 🚦 Running the Application

### Development Server

### Frontend
Start the development server:

```bash
npm start
# or
ng serve
```

Navigate to `http://localhost:4200/` in your browser. The application will automatically reload when you make changes to the source files.

### Build for Production

Build the project for production deployment:

```bash
npm run build
# or
ng build
```

The build artifacts will be stored in the `dist/` directory, optimized for performance.

### Watch Mode

For development with automatic rebuilds:

```bash
npm run watch
# or
ng build --watch --configuration development
```
### Backend
Run the application using Maven:

```bash
./mvnw spring-boot:run
# or on Windows
mvnw.cmd spring-boot:run
```

The server will start on `http://localhost:8080`

### Using JAR File

Build and run as a JAR:

```bash
./mvnw clean package
java -jar target/server-0.0.1-SNAPSHOT.war
```

### Active Profile

The application uses the `db` profile by default (configured in `application.properties`). You can change the active profile:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=fake-store
```

## 📁 Project Structure

### Frontend

```
leads-ui/
├── src/
│   ├── app/
│   │   ├── constants/         # TypeScript interfaces and constants
│   │   │   ├── product.ts     # Product interface definition
│   │   │   └── leads-service.ts # Service interface
│   │   ├── guards/            # Route guards
│   │   │   └── secure-guard.ts # Authentication guard
│   │   ├── services/          # Business logic services
│   │   │   ├── login.service.ts        # Authentication service
│   │   │   ├── fake-product.service.ts # Fake Store API service
│   │   │   ├── db-product.service.ts   # Database API service
│   │   │   └── session.ts              # Session management
│   │   ├── public/            # Public pages (no auth required)
│   │   │   └── login/         # Login component
│   │   ├── private/           # Protected pages (auth required)
│   │   │   ├── product/       # Product management
│   │   │   │   ├── product-list/      # List products
│   │   │   │   └── product-add/       # Add/Edit product
│   │   │   └── product-listing/       # Alternative product view
│   │   ├── app.routes.ts      # Application routing configuration
│   │   └── app.config.ts      # Application configuration
│   ├── interceptor.ts         # HTTP interceptor for auth tokens
│   ├── main.ts               # Application entry point
│   ├── index.html            # Main HTML file
│   └── styles.css            # Global styles
├── angular.json              # Angular CLI configuration
├── package.json              # Dependencies and scripts
├── tsconfig.json             # TypeScript configuration
└── README.md                 # This file
```
## Server

```
server/
├── src/
│   ├── main/
│   │   ├── java/com/leads/server/
│   │   │   ├── classes/           # DTOs and request/response classes
│   │   │   │   ├── LoginRequest.java
│   │   │   │   └── Rating.java
│   │   │   ├── controller/        # REST Controllers
│   │   │   │   ├── AppController.java          # Database API endpoints
│   │   │   │   ├── FakeStoreController.java    # Fake Store API endpoints
│   │   │   │   └── PublicController.java       # Public endpoints
│   │   │   ├── entities/          # JPA Entities
│   │   │   │   ├── Product.java
│   │   │   │   └── User.java
│   │   │   ├── repositories/      # Spring Data repositories
│   │   │   │   └── UserRepo.java
│   │   │   ├── services/          # Business logic services
│   │   │   │   ├── FakeLoginService.java
│   │   │   │   ├── FakeStoreService.java
│   │   │   │   ├── ProductService.java
│   │   │   │   └── UserService.java
│   │   │   ├── setup/             # Security and configuration
│   │   │   │   ├── beans/         # Spring beans configuration
│   │   │   │   ├── jwt/           # JWT token handling
│   │   │   │   ├── principal/     # User authentication details
│   │   │   │   └── security/      # Security configuration
│   │   │   ├── InitialValue.java
│   │   │   ├── ServerApplication.java
│   │   │   └── ServletInitializer.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/                      # Test classes
├── pom.xml                        # Maven configuration
└── README.md                      # This file
```

## 🔐 Authentication Flow

1. Client sends credentials to `/api/fake-store/login` or `/api/public/login`
2. Server validates credentials and generates JWT token
3. Client receives JWT token in response
4. Client includes token in `Authorization` header for subsequent requests
5. JWT filter validates token and authenticates requests

## 🌐 API Endpoints

### Fake Store API (`/api/fake-store`)

#### Authentication
- `POST /api/fake-store/login` - User authentication (returns JWT token)

#### Products
- `GET /api/fake-store/products` - Get all products
- `GET /api/fake-store/products/get/{id}` - Get product by ID
- `GET /api/fake-store/products/category/{category}` - Get products by category
- `POST /api/fake-store/products/add` - Create new product (requires auth)
- `PUT /api/fake-store/products/edit/{id}` - Update product (requires auth)
- `DELETE /api/fake-store/products/delete/{id}` - Delete product (requires auth)

#### Health Check
- `GET /api/fake-store/ping` - Check API status

### Database API (`/api/app`)

#### Products
- `GET /api/app/products` - Get all products
- `GET /api/app/products/get/{id}` - Get product by ID
- `GET /api/app/products/category/{category}` - Get products by category
- `POST /api/app/products/add` - Create new product (requires auth)
- `PUT /api/app/products/edit/{id}` - Update product (requires auth)
- `DELETE /api/app/products/delete/{id}` - Delete product (requires auth)

#### Health Check
- `GET /api/app/ping` - Check API status

### Public API (`/api/public`)

- `POST /api/public/login` - Database user authentication (returns JWT token)

## ⚙️ Configuration

### Application Properties

Key configuration in `application.properties`:

```properties
# Application name
spring.application.name=leads-server

# Server port
server.port=8080

# Active profile
spring.profiles.active=db

# Logging
logging.file.name=<path>/logs/leads-server.log
logging.level.com.leads.server=DEBUG

# CORS configuration
application.domain=http://localhost:4200,http://localhost:321
application.cors.allowed-methods=GET,POST,PUT,DELETE
application.cors.allowed-headers=Authorization,Content-Type
```

### CORS Configuration

The application is configured to allow requests from specified domains. Update `application.domain` in `application.properties` to add or modify allowed origins.

## 🧪 Testing

### Run Unit Tests

Execute tests using Maven:

```bash
./mvnw test
```

### Test Coverage

Generate test coverage report:

```bash
./mvnw verify
```

## 🔨 Development

### Hot Reload

For development with automatic reload, use Spring Boot DevTools:

1. Add DevTools dependency (if not already included)
2. Run the application
3. Make changes to your code
4. Application will automatically restart

### Building for Production

Create a production WAR file:

```bash
./mvnw clean package -DskipTests
```

The WAR file will be generated in `target/server-0.0.1-SNAPSHOT.war`

## 📊 Logging

The application uses SLF4J with Logback for logging:

- **Console Logging** - Formatted output to console
- **File Logging** - Logs written to configured file location
- **Log Levels** - Configurable per package
- **Log Rotation** - Automatic log file rotation (max 10MB, 30 days history)

## 📚 Additional Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [Spring Data JPA Documentation](https://spring.io/projects/spring-data-jpa)
- [JJWT Documentation](https://github.com/jwtk/jjwt)

## 👥 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is part of the ITHouse Leads Management System.

## 🐛 Known Issues

- Ensure MySQL database is running when using `db` profile
- JWT secret key should be configured in production environment
- Log file path should be adjusted for deployment environment

## 🔮 Future Enhancements

- [ ] Add product image upload and storage
- [ ] Implement pagination for product listing
- [ ] Add advanced search functionality
- [ ] Implement user role-based access control
- [ ] Add API documentation with Swagger/OpenAPI
- [ ] Integrate caching for improved performance
- [ ] Add monitoring and metrics collection

---

**Built with ❤️ using Spring Boot and Java**
