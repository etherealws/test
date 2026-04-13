# Cloud Demo - Spring Cloud Alibaba Microservices Example

[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/your-username/cloud-demo/blob/main/LICENSE.md)
[![Java Version](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.org/projects/jdk/17/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.3-brightgreen.svg)](https://spring.io/projects/spring-cloud)
[![Spring Cloud Alibaba](https://img.shields.io/badge/Spring%20Cloud%20Alibaba-2023.0.3.2-brightgreen.svg)](https://github.com/alibaba/spring-cloud-alibaba)
[![Maven Central](https://img.shields.io/badge/Maven-Central-red.svg)](https://maven.apache.org/)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](http://makeapullrequest.com)
[![GitHub stars](https://img.shields.io/github/stars/your-username/cloud-demo.svg)](https://github.com/your-username/cloud-demo/stargazers)
[![GitHub forks](https://img.shields.io/github/forks/your-username/cloud-demo.svg)](https://github.com/your-username/cloud-demo/network/members)
[![GitHub issues](https://img.shields.io/github/issues/your-username/cloud-demo.svg)](https://github.com/your-username/cloud-demo/issues)
[![GitHub contributors](https://img.shields.io/github/contributors/your-username/cloud-demo.svg)](https://github.com/your-username/cloud-demo/graphs/contributors)
[![Build Status](https://img.shields.io/github/actions/workflow/status/your-username/cloud-demo/ci.yml)](https://github.com/your-username/cloud-demo/actions/workflows/ci.yml)

A microservices e-commerce system demonstration project based on Spring Cloud Alibaba, showcasing core functionalities and best practices of microservices architecture.

## Project Overview

This project is an e-commerce microservices system example that implements core business functions of product and order services, demonstrating key microservices technologies such as service registration and discovery, configuration management, API gateway, service invocation, circuit breaking, and degradation.

## Tech Stack

- **Java**: 17
- **Spring Boot**: 3.3.4
- **Spring Cloud**: 2023.0.3
- **Spring Cloud Alibaba**: 2023.0.3.2
- **Nacos**: Service registration and configuration center
- **Sentinel**: Flow control and circuit breaking
- **Gateway**: API Gateway
- **OpenFeign**: Declarative HTTP client
- **LoadBalancer**: Client-side load balancing
- **Maven**: Project build tool

## Project Structure

```
cloud-demo/
├── gateway/                  # Gateway service module
│   └── src/main/
│       ├── java/com/example/
│       │   ├── GatewayApplication.java
│       │   └── filter/RtGlobalFilter.java
│       └── resources/
│           ├── application.yml
│           └── application-route.yml
├── model/                    # Common model module
│   └── src/main/java/com/example/
│       ├── Order.java        # Order entity
│       ├── Product.java      # Product entity
│       └── common/R.java     # Unified response class
├── services/                 # Business service modules
│   ├── service-order/        # Order service
│   │   └── src/main/
│   │       ├── java/com/example/
│   │       │   ├── OrderMainApplication.java
│   │       │   ├── controller/OrderController.java
│   │       │   ├── service/OrderService.java
│   │       │   ├── feign/ProductFeignClient.java
│   │       │   ├── feign/WeatherFeignClient.java
│   │       │   └── interceptor/XTokenRequestInterceptor.java
│   │       └── resources/application.yml
│   └── service-product/      # Product service
│       └── src/main/
│           ├── java/com/example/
│           │   ├── ProductMainApplication.java
│           │   ├── controller/ProductController.java
│           │   └── service/ProductService.java
│           └── resources/application.yml
├── pom.xml                   # Parent POM
└── README.md                 # Project documentation
```

## Service Modules

### 1. Gateway Service (Port: 80)

- **Function**: Unified API entry, routing, and CORS handling
- **Routing Rules**:
  - `/api/order/**` → Forward to service-order
  - `/api/product/**` → Forward to service-product
- **Features**:
  - Global CORS configuration
  - Path rewriting
  - Unified response header addition

### 2. Service-Product (Port: 9000)

- **Function**: Product information query
- **Endpoints**:
  - `GET /product/{id}` - Query product by ID
- **Features**:
  - Nacos service registration
  - Sentinel circuit breaking protection

### 3. Service-Order (Port: 8000)

- **Function**: Order creation and management
- **Endpoints**:
  - `GET /create?userId={userId}&productId={productId}` - Create order
  - `GET /config` - Get configuration information
- **Features**:
  - OpenFeign remote invocation
  - Sentinel circuit breaking and degradation
  - Request interceptor
  - Nacos configuration center

### 4. Model Common Module

- **Function**: Define common entity classes and response classes
- **Includes**:
  - Order - Order entity
  - Product - Product entity
  - R - Unified response class

## Core Features

### Service Registration and Discovery

Using **Nacos** as service registry, all services automatically register with Nacos upon startup, enabling mutual discovery between services.

### Configuration Management

Using **Nacos Config** for centralized configuration management:
- Multi-environment configuration support (dev/prod)
- Dynamic configuration refresh
- Namespace isolation

### API Gateway

Using **Spring Cloud Gateway** to implement:
- Unified entry point management
- Route forwarding
- CORS handling
- Request filters

### Service Invocation

Three invocation methods implemented:
1. **DiscoveryClient**: Manual service discovery
2. **LoadBalancerClient**: Load balancing client
3. **OpenFeign**: Declarative invocation (recommended)

### Circuit Breaking and Degradation

Using **Sentinel** to implement:
- Flow control
- Circuit breaking and degradation
- System protection
- Unified exception handling

### Load Balancing

Using **Spring Cloud LoadBalancer** to implement:
- Client-side load balancing
- Automatic distribution across multiple instances

## Quick Start

### Prerequisites

- JDK 17+
- Maven 3.6+
- Nacos Server (default: 127.0.0.1:8848)
- Sentinel Dashboard (default: 127.0.0.1:8080)

### Install Nacos

1. Download Nacos: [Nacos Official Site](https://nacos.io/en-us/docs/quick-start.html)
2. Start Nacos:
   ```bash
   sh startup.sh -m standalone
   ```
3. Access console: http://localhost:8848/nacos (default username/password: nacos/nacos)

### Install Sentinel

1. Download Sentinel Dashboard: [Sentinel GitHub](https://github.com/alibaba/Sentinel/releases)
2. Start Sentinel:
   ```bash
   java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
   ```
3. Access console: http://localhost:8080 (default username/password: sentinel/sentinel)

### Configure Nacos Configuration Center

Create the following configuration in Nacos:

**Configuration Name**: `service-order.yml`
**Group**: `order`
**Namespace**: `dev`

```yaml
timeout: 5000
autoConfirm: true
```

### Start Project

1. Clone the project:
   ```bash
   git clone <repository-url>
   cd cloud-demo
   ```

2. Build the project:
   ```bash
   mvn clean install
   ```

3. Start services in the following order:
   ```bash
   # Start product service
   cd services/service-product
   mvn spring-boot:run

   # Start order service
   cd ../service-order
   mvn spring-boot:run

   # Start gateway service
   cd ../../gateway
   mvn spring-boot:run
   ```

## API Testing

### 1. Query Product

```bash
# Access product service directly
curl http://localhost:9000/product/1

# Access through gateway
curl http://localhost/api/product/1
```

### 2. Create Order

```bash
# Access order service directly
curl http://localhost:8000/create?userId=1001&productId=1

# Access through gateway
curl http://localhost/api/order/create?userId=1001&productId=1
```

### 3. Get Configuration

```bash
curl http://localhost:8000/config
```

## Project Highlights

- ✅ Complete microservices architecture example
- ✅ Service registration and discovery
- ✅ Configuration center management
- ✅ API gateway routing
- ✅ Service invocation (Feign)
- ✅ Circuit breaking and degradation protection
- ✅ Load balancing
- ✅ CORS handling
- ✅ Unified exception handling
- ✅ Request interceptors

## Learning Points

Through this project, you can learn:

1. **Microservices Architecture Design**: How to split business modules
2. **Service Governance**: Service registration, discovery, and configuration management
3. **API Gateway**: Routing, filtering, and CORS handling
4. **Service Communication**: Synchronous invocation and load balancing
5. **Fault Tolerance**: Circuit breaking, degradation, and rate limiting
6. **Configuration Management**: Centralized configuration and dynamic refresh

## FAQ

### Q: Service startup fails, connection to Nacos timeout?

A: Check if Nacos service is started and confirm the server-addr in configuration is correct.

### Q: Cannot see services in Sentinel console?

A: Ensure Sentinel Dashboard is started and service is configured with `eager: true` for immediate registration.

### Q: Service invocation fails?

A: Check if target service is registered with Nacos and confirm service name is correct.

### Q: Configuration not taking effect?

A: Confirm configuration is created in correct namespace and group in Nacos.

## Future Improvements

- [ ] Integrate distributed transaction (Seata)
- [ ] Add distributed tracing (Sleuth + Zipkin)
- [ ] Implement unified authentication and authorization (Spring Security + JWT)
- [ ] Add caching layer (Redis)
- [ ] Message queue integration (RocketMQ)
- [ ] Add monitoring and alerting (Prometheus + Grafana)
- [ ] Containerized deployment (Docker + Kubernetes)

## Contributing

Welcome to submit Issues and Pull Requests to improve this project. For details, please see [Contributing Guide](../../CONTRIBUTING.md).

## License

This project is licensed under the Apache License 2.0. For details, see [LICENSE.md](../../LICENSE.md).

This project is for learning and demonstration purposes only.

## Contact

If you have any questions, please submit an Issue.

---

**Note**: This project is a learning example. Please conduct thorough testing and optimization before using it in production environments.
