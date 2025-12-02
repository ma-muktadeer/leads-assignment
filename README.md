# Leads UI - Product Management Application

A modern, feature-rich Angular application for managing products with dual data source support (Fake Store API and Database API). Built with Angular 20.3 and Bootstrap 5, this application provides a clean and responsive interface for product management operations.

## 🚀 Features

### Authentication & Security
- **JWT-based Authentication** - Secure login with token-based session management
- **Dual Login Modes** - Support for both Fake Store API and Database API authentication
- **Route Guards** - Protected routes with `secureGuard` to ensure authenticated access
- **HTTP Interceptors** - Automatic token injection for authenticated API requests

### Product Management
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

## 📋 Prerequisites

Before running this application, ensure you have the following installed:

- **Node.js** (v18 or higher recommended)
- **npm** (v9 or higher)
- **Angular CLI** (v20.3.0 or higher)

## 🔧 Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd leads-ui
   ```

2. **Install dependencies**
   ```bash
   npm install
   ```

3. **Configure Backend API**
   
   Ensure your backend server is running on `http://localhost:8080` with the following endpoints available:
   - **Authentication**: `/api/fake-store/login` or `/api/public/login`
   - **Products**: `/api/fake-store/products` or `/api/app/products`

## 🚦 Running the Application

### Development Server

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

## 📁 Project Structure

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

## 🧪 Testing

### Run Unit Tests

Execute the unit tests using Karma:

```bash
npm test
# or
ng test
```

### Run End-to-End Tests

For e2e testing (requires additional setup):

```bash
ng e2e
```

> **Note**: Angular CLI doesn't include an e2e testing framework by default. You can add one based on your needs (e.g., Cypress, Playwright).

## 🔐 Authentication Flow

1. User navigates to `/login`
2. Enters credentials and selects authentication source (Fake Store or Database)
3. Upon successful login, JWT token is stored in session storage
4. Protected routes are accessible with the secure guard
5. HTTP interceptor automatically adds the token to all API requests

## 🌐 API Integration

### Fake Store API Endpoints

- `POST /api/fake-store/login` - User authentication
- `GET /api/fake-store/products` - Get all products
- `GET /api/fake-store/products/category/{category}` - Get products by category
- `GET /api/fake-store/products/get/{id}` - Get single product
- `POST /api/fake-store/products/add` - Create new product
- `PUT /api/fake-store/products/edit/{id}` - Update product
- `DELETE /api/fake-store/products/delete/{id}` - Delete product

### Database API Endpoints

- `POST /api/public/login` - User authentication
- `GET /api/app/products` - Get all products
- `GET /api/app/products/category/{category}` - Get products by category
- `GET /api/app/products/get/{id}` - Get single product
- `POST /api/app/products/add` - Create new product
- `PUT /api/app/products/edit/{id}` - Update product
- `DELETE /api/app/products/delete/{id}` - Delete product

## 🎨 Code Formatting

This project uses Prettier for code formatting with the following configuration:

- **Print Width**: 100 characters
- **Single Quotes**: Enabled
- **HTML Parser**: Angular template syntax

Format your code with:
```bash
npx prettier --write .
```

## 🔨 Development Tools

### Generate Components

Create a new component:
```bash
ng generate component component-name
# or shorthand
ng g c component-name
```

### Generate Services

Create a new service:
```bash
ng generate service service-name
# or shorthand
ng g s service-name
```

### View All Available Schematics

```bash
ng generate --help
```

## 📚 Additional Resources

- [Angular Documentation](https://angular.dev)
- [Angular CLI Command Reference](https://angular.dev/tools/cli)
- [Bootstrap Documentation](https://getbootstrap.com/docs/5.3)
- [ng-bootstrap Components](https://ng-bootstrap.github.io)
- [RxJS Documentation](https://rxjs.dev)

## 👥 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is part of the ITHouse Leads Management System.

## 🐛 Known Issues

- Ensure backend APIs are running before starting the application
- Session tokens are stored in session storage (cleared on browser close)

## 🔮 Future Enhancements

- [ ] Add product image upload functionality
- [ ] Implement advanced search and filtering
- [ ] Add shopping cart functionality
- [ ] Implement user profile management
- [ ] Add export/import product data
- [ ] Integrate analytics dashboard

---

**Built with ❤️ using Angular and Bootstrap**
