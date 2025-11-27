# CI/CD Demo – Monorepo with Microservices

A full-stack monorepo demonstrating CI/CD practices with multiple services (FastAPI, Spring Boot, React) orchestrated via Docker Compose and automated through GitHub Actions and GitLab CI.


## 📋 Table of Contents

- [Project Overview](#project-overview)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Services](#services)
- [Getting Started](#getting-started)
- [CI/CD Pipelines](#cicd-pipelines)
- [Docker & Deployment](#docker--deployment)
- [Development Workflow](#development-workflow)
- [Testing](#testing)
- [Environment Variables](#environment-variables)

---

## 🎯 Project Overview

This project demonstrates a **production-ready CI/CD setup** for a microservices architecture. It includes:

- **Three independent services**: Users (Python/FastAPI), Orders (Java/Spring Boot), and Frontend (React/TypeScript)
- **Automated testing and deployment** via GitHub Actions and GitLab CI
- **Docker containerization** with multi-stage builds for optimized images
- **Database integration** with MySQL for both backend services
- **Infrastructure as Code** using Docker Compose

The project simulates a real-world scenario where different teams work on different services using different tech stacks, all orchestrated through a unified CI/CD pipeline.

---

## 🏗️ Architecture

```
┌─────────────────┐
│   Frontend      │
│  (React/Vite)   │
│   Port: 3000    │
└────────┬────────┘
         │
         ├─────────────────┬─────────────────┐
         │                 │                 │
         ▼                 ▼                 ▼
┌─────────────────┐ ┌─────────────────┐   
│  Users Service  │ │ Orders Service  │   
│   (FastAPI)     │ │  (Spring Boot)  │   
│   Port: 8000    │ │   Port: 8080    │   
└────────┬────────┘ └────────┬────────┘   
         │                   │            
         ▼                   ▼            
┌─────────────────┐ ┌─────────────────┐
│   MySQL DB      │ │   MySQL DB      │
│   (usersdb)     │ │  (ordersdb)     │
└─────────────────┘ └─────────────────┘
```

**Key Architectural Decisions:**

- **Microservices Pattern**: Each service is independently deployable and scalable
- **Database Per Service**: Each microservice has its own database to ensure loose coupling
- **API Gateway Pattern**: Frontend communicates with backend services via REST APIs
- **Container Orchestration**: Docker Compose manages service dependencies and networking


## 🛠️ Tech Stack

### Backend Services

**Users Service**
- **Language**: Python 3.12
- **Framework**: FastAPI
- **ORM**: SQLAlchemy
- **Database**: MySQL 8
- **Server**: Uvicorn

**Orders Service**
- **Language**: Java 21
- **Framework**: Spring Boot 4
- **Build Tool**: Maven
- **ORM**: Spring Data JPA (Hibernate)
- **Database**: MySQL 8

### Frontend

- **Framework**: React 19
- **Language**: TypeScript
- **Build Tool**: Vite
- **Bundler**: Rollup (via Vite)
- **Linting**: ESLint
- **Web Server**: Nginx (production)

### DevOps & CI/CD

- **Containerization**: Docker (multi-stage builds)
- **Orchestration**: Docker Compose
- **CI/CD**: GitHub Actions, GitLab CI
- **Container Registry**: GitHub Container Registry (GHCR)
- **Deployment**: EC2 (via SSH)


## 📁 Project Structure

```
CI_CD_Demo/
├── .github/
│   └── workflows/
│       └── ci.yml                    # GitHub Actions CI/CD pipeline
├── .gitlab-ci.yml                    # GitLab CI pipeline
├── docker-compose.yml                # Orchestrates all services
├── services/
│   ├── users_service/                # FastAPI microservice
│   │   ├── app/
│   │   │   ├── main.py               # FastAPI entry point
│   │   │   ├── database.py           # DB connection & session
│   │   │   ├── models.py             # SQLAlchemy models
│   │   │   ├── schema.py             # Pydantic schemas
│   │   │   └── routes/
│   │   │       └── health.py         # Health check endpoint
│   │   ├── Dockerfile                # Multi-stage Python build
│   │   ├── requirements.txt          # Python dependencies
│   │   └── .env                      # Environment variables
│   │
│   └── orders_service/               # Spring Boot microservice
│       ├── src/
│       │   ├── main/
│       │   │   ├── java/dev/aryadanech/Orders/
│       │   │   │   ├── OrdersApplication.java    # Main class
│       │   │   │   ├── controller/
│       │   │   │   │   ├── HealthController.java # Health endpoint
│       │   │   │   │   └── ProductController.java # Products API
│       │   │   │   ├── models/
│       │   │   │   │   ├── Orders.java           # Order entity
│       │   │   │   │   └── Product.java          # Product entity
│       │   │   │   ├── repository/               # JPA repositories
│       │   │   │   ├── services/                 # Business logic
│       │   │   │   └── utils/                    # API response utils
│       │   │   └── resources/
│       │   │       └── application.properties    # Spring config
│       │   └── test/                             # JUnit tests
│       ├── Dockerfile                            # Multi-stage Java build
│       └── pom.xml                               # Maven dependencies
│
└── www/                                          # React frontend
    ├── src/
    │   ├── main.tsx                              # React entry point
    │   ├── App.tsx                               # Main component
    │   └── assets/                               # Static files
    ├── public/                                   # Public assets
    ├── Dockerfile                                # Multi-stage Node/Nginx build
    ├── package.json                              # NPM dependencies
    ├── vite.config.ts                            # Vite config
    └── tsconfig.json                             # TypeScript config
```

---

## 🚀 Services

### 1. Users Service (FastAPI)

**Purpose**: User management microservice  
**Port**: 8000  
**Database**: `usersdb`

**Key Features**:
- User CRUD operations
- Database health check endpoint
- SQLAlchemy ORM with MySQL
- Environment-based configuration

**Endpoints**:
- `GET /` - Service status
- `GET /health` - Database connectivity check

**Database Schema**:
```python
User:
  - id (Integer, PK)
  - name (String)
  - email (String, unique)
  - password_hash (String)
  - created_at (DateTime)
```

---

### 2. Orders Service (Spring Boot)

**Purpose**: Order and product management  
**Port**: 8080  
**Database**: `ordersdb`

**Key Features**:
- Product and order management
- Spring Data JPA repositories
- Lombok for boilerplate reduction
- Custom API response wrapper

**Endpoints**:
- `GET /health` - Database connectivity check
- `GET /products` - Get all products
- `POST /products` - Create new product

**Database Schema**:
```java
Product:
  - productId (Long, PK, auto-increment)
  - productName (String)
  - productDescription (String)

Orders:
  - orderId (Long, PK)
  - productId (Long, FK)
  - quantity (Integer)
  - totalPrice (Double)
```


### 3. Frontend (React + Vite)

**Purpose**: User interface  
**Port**: 3000 (development), 80 (production)

**Key Features**:
- React 19 with TypeScript
- Vite for fast builds and HMR
- React Compiler for optimizations
- ESLint for code quality
- Nginx for production serving


## 🏃 Getting Started

### Prerequisites

- Docker & Docker Compose
- Git

### Local Development

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd CI_CD_Demo
   ```

2. **Set up environment variables**:
   ```bash
   # Create .env file in services/users_service/
   echo "DATABASE_URL=mysql+pymysql://root:root@users-db:3306/usersdb" > services/users_service/.env
   ```

3. **Start all services**:
   ```bash
   docker compose up --build
   ```

4. **Access the services**:
   - Frontend: http://localhost:3000
   - Users Service: http://localhost:8000
   - Orders Service: http://localhost:8080

5. **View logs**:
   ```bash
   docker compose logs -f [service_name]
   ```

6. **Stop services**:
   ```bash
   docker compose down
   ```


## 🔄 CI/CD Pipelines

### GitHub Actions Workflow

**File**: [`.github/workflows/ci.yml`](.github/workflows/ci.yml)

**Pipeline Stages**:

1. **Build & Test**
   - Sets up Python 3.12, Java 21, Node 20
   - Runs MySQL services for testing
   - Executes unit tests for all services
   - Lints and builds frontend

2. **Docker Build & Push**
   - Builds Docker images for all services
   - Pushes to GitHub Container Registry (GHCR)
   - Tags images with `latest`

3. **Deploy to EC2**
   - SSHs into EC2 instance
   - Pulls latest code and images
   - Restarts services with Docker Compose

**Trigger**: Push or PR to `main` branch


### GitLab CI Pipeline

**File**: [`.gitlab-ci.yml`](.gitlab-ci.yml)

**Pipeline Stages**:

1. **Test Stage**
   - `users_service_test`: Python tests with MySQL
   - `orders_service_test`: Maven build and test
   - `frontend_test`: Lint and build

2. **Docker Stage**
   - Builds all service images
   - Runs only on `main` branch

**Features**:
- Caching for Maven and NPM dependencies
- Parallel test execution
- MySQL service containers for integration tests


## 🐳 Docker & Deployment

### Multi-Stage Dockerfiles

All services use **multi-stage builds** for optimized production images:

**Users Service (Python)**:
```dockerfile
Stage 1: Build wheels with build tools
Stage 2: Runtime-only image with pre-built wheels
Result: ~200MB smaller image
```

**Orders Service (Java)**:
```dockerfile
Stage 1: Maven build with dependencies
Stage 2: JRE-only runtime with JAR
Result: ~300MB smaller image
```

**Frontend (React)**:
```dockerfile
Stage 1: Development with hot reload
Stage 2: Production build
Stage 3: Nginx serving static files
Result: ~900MB smaller image
```

### Docker Compose

**File**: [`docker-compose.yml`](docker-compose.yml)

**Services Defined**:
- `users_service` → `users-db` (MySQL)
- `orders_service` → `orders-db` (MySQL)
- `frontend` → both backend services

**Networking**:
- All services on the same bridge network
- Services communicate via service names (DNS)

**Volumes**:
- `users_db_data`: Persistent MySQL data for users
- `orders_db_data`: Persistent MySQL data for orders


## 💻 Development Workflow

### Working on Users Service (Python)

```bash
# Install dependencies locally
cd services/users_service
pip install -r requirements.txt

# Run locally (requires MySQL running)
uvicorn app.main:app --reload --port 8000

# Run tests
pytest
```

### Working on Orders Service (Java)

```bash
# Build and test
cd services/orders_service
mvn clean package
mvn test

# Run locally
mvn spring-boot:run
```

### Working on Frontend (React)

```bash
# Install dependencies
cd www
npm install

# Start dev server
npm run dev

# Lint code
npm run lint

# Build for production
npm run build
```


## 🧪 Testing

### Users Service Tests

- **Framework**: pytest
- **Location**: `services/users_service/tests/`
- **Coverage**: Health check, database connectivity

### Orders Service Tests

- **Framework**: JUnit 5 + Mockito
- **Location**: `services/orders_service/src/test/`
- **Test Classes**:
  - `OrdersEntityTest`: Entity field validation
  - `OrdersRepositoryTest`: JPA repository operations
  - `HealthControllerTest`: Controller endpoints
  - `ProductServiceTest`: Service layer logic

**Running Tests**:
```bash
# Python
pytest services/users_service/tests

# Java
mvn -f services/orders_service/pom.xml test

# Frontend
cd www && npm run lint
```


## 🔐 Environment Variables

### Users Service

| Variable       | Description                 | Default                                           |
|----------------|-----------------------------|---------------------------------------------------|
| `DATABASE_URL` | MySQL connection string     | `mysql+pymysql://root:root@users-db:3306/usersdb` |

### Orders Service

| Variable                      | Description       | Default                                |
|-------------------------------|-------------------|----------------------------------------|
| `SPRING_DATASOURCE_URL`       | JDBC URL          | `jdbc:mysql://orders-db:3306/ordersdb` |
| `SPRING_DATASOURCE_USERNAME`  | DB username       | `root`                                 |
| `SPRING_DATASOURCE_PASSWORD`  | DB password       | `root`                                 |

### CI/CD Secrets

**GitHub Actions**:
- `GH_TOKEN`: GitHub Personal Access Token for GHCR
- `EC2_HOST`: EC2 instance public IP
- `EC2_SSH_KEY`: Private SSH key for deployment

**GitLab CI**:
- Managed via GitLab CI/CD variables


## 📦 Deployment Strategies

### Current Setup (Single EC2)

All services deployed on a single EC2 instance using Docker Compose.

### Multi-Cloud Deployment (Advanced)

You can deploy services to different cloud providers:

1. **Orders Service → AWS EC2**
   - Build and push Docker image to ECR
   - Pull and run on EC2 instance

2. **Users Service → GCP Compute Engine**
   - Build and push to Google Container Registry
   - Deploy on GCP VM

3. **Frontend → Vercel/Hostinger**
   - Build static files
   - Deploy to CDN/static hosting


## 🛠️ Common Commands

### Docker Compose

```bash
# Start all services
docker compose up -d

# Rebuild and start
docker compose up --build

# View logs
docker compose logs -f [service]

# Stop all services
docker compose down

# Remove volumes (reset DBs)
docker compose down -v

# Restart a single service
docker compose restart [service]
```

### Individual Service Management

```bash
# Users Service
docker compose up users_service

# Orders Service
docker compose up orders_service

# Frontend
docker compose up frontend
```
