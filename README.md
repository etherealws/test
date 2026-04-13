# Cloud Demo - Spring Cloud Alibaba 微服务示例

基于 Spring Cloud Alibaba 的微服务电商系统演示项目，展示了微服务架构的核心功能和最佳实践。

## 项目简介

本项目是一个电商微服务系统示例，实现了商品服务和订单服务的核心业务功能，展示了服务注册发现、配置管理、API网关、服务调用、熔断降级等微服务关键技术。

## 技术栈

- **Java**: 17
- **Spring Boot**: 3.3.4
- **Spring Cloud**: 2023.0.3
- **Spring Cloud Alibaba**: 2023.0.3.2
- **Nacos**: 服务注册与配置中心
- **Sentinel**: 流量控制与熔断降级
- **Gateway**: API网关
- **OpenFeign**: 声明式HTTP客户端
- **LoadBalancer**: 客户端负载均衡
- **Maven**: 项目构建工具

## 项目结构

```
cloud-demo/
├── gateway/                  # 网关服务模块
│   └── src/main/
│       ├── java/com/example/
│       │   ├── GatewayApplication.java
│       │   └── filter/RtGlobalFilter.java
│       └── resources/
│           ├── application.yml
│           └── application-route.yml
├── model/                    # 公共模型模块
│   └── src/main/java/com/example/
│       ├── Order.java        # 订单实体
│       ├── Product.java      # 商品实体
│       └── common/R.java     # 统一响应类
├── services/                 # 业务服务模块
│   ├── service-order/        # 订单服务
│   │   └── src/main/
│   │       ├── java/com/example/
│   │       │   ├── OrderMainApplication.java
│   │       │   ├── controller/OrderController.java
│   │       │   ├── service/OrderService.java
│   │       │   ├── feign/ProductFeignClient.java
│   │       │   ├── feign/WeatherFeignClient.java
│   │       │   └── interceptor/XTokenRequestInterceptor.java
│   │       └── resources/application.yml
│   └── service-product/      # 商品服务
│       └── src/main/
│           ├── java/com/example/
│           │   ├── ProductMainApplication.java
│           │   ├── controller/ProductController.java
│           │   └── service/ProductService.java
│           └── resources/application.yml
├── pom.xml                   # 父工程POM
└── README.md                 # 项目文档
```

## 服务模块

### 1. Gateway 网关服务 (端口: 80)

- **功能**: 统一API入口，路由转发，跨域处理
- **路由规则**:
  - `/api/order/**` → 转发到 service-order 服务
  - `/api/product/**` → 转发到 service-product 服务
- **特性**:
  - 全局跨域配置
  - 路径重写
  - 统一响应头添加

### 2. Service-Product 商品服务 (端口: 9000)

- **功能**: 商品信息查询
- **接口**:
  - `GET /product/{id}` - 根据ID查询商品
- **特性**:
  - Nacos服务注册
  - Sentinel熔断保护

### 3. Service-Order 订单服务 (端口: 8000)

- **功能**: 订单创建和管理
- **接口**:
  - `GET /create?userId={userId}&productId={productId}` - 创建订单
  - `GET /config` - 获取配置信息
- **特性**:
  - OpenFeign远程调用
  - Sentinel熔断降级
  - 请求拦截器
  - Nacos配置中心

### 4. Model 公共模块

- **功能**: 定义公共实体类和响应类
- **包含**:
  - Order - 订单实体
  - Product - 商品实体
  - R - 统一响应类

## 核心功能

### 服务注册与发现

使用 **Nacos** 作为服务注册中心，所有服务启动后自动注册到Nacos，实现服务间的相互发现。

### 配置管理

使用 **Nacos Config** 实现配置的集中管理：
- 支持多环境配置（dev/prod）
- 配置动态刷新
- 命名空间隔离

### API网关

使用 **Spring Cloud Gateway** 实现：
- 统一入口管理
- 路由转发
- 跨域处理
- 请求过滤器

### 服务间调用

实现三种调用方式：
1. **DiscoveryClient**: 手动服务发现
2. **LoadBalancerClient**: 负载均衡客户端
3. **OpenFeign**: 声明式调用（推荐）

### 熔断降级

使用 **Sentinel** 实现：
- 流量控制
- 熔断降级
- 系统保护
- 统一异常处理

### 负载均衡

使用 **Spring Cloud LoadBalancer** 实现：
- 客户端负载均衡
- 多实例自动分发

## 快速开始

### 环境要求

- JDK 17+
- Maven 3.6+
- Nacos Server (默认: 127.0.0.1:8848)
- Sentinel Dashboard (默认: 127.0.0.1:8080)

### 安装Nacos

1. 下载Nacos: [Nacos官网](https://nacos.io/zh-cn/docs/quick-start.html)
2. 启动Nacos:
   ```bash
   sh startup.sh -m standalone
   ```
3. 访问控制台: http://localhost:8848/nacos (默认账号密码: nacos/nacos)

### 安装Sentinel

1. 下载Sentinel Dashboard: [Sentinel Github](https://github.com/alibaba/Sentinel/releases)
2. 启动Sentinel:
   ```bash
   java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
   ```
3. 访问控制台: http://localhost:8080 (默认账号密码: sentinel/sentinel)

### 配置Nacos配置中心

在Nacos中创建以下配置：

**配置名**: `service-order.yml`
**分组**: `order`
**命名空间**: `dev`

```yaml
timeout: 5000
autoConfirm: true
```

### 启动项目

1. 克隆项目到本地:
   ```bash
   git clone <repository-url>
   cd cloud-demo
   ```

2. 编译项目:
   ```bash
   mvn clean install
   ```

3. 按以下顺序启动服务:
   ```bash
   # 启动商品服务
   cd services/service-product
   mvn spring-boot:run

   # 启动订单服务
   cd ../service-order
   mvn spring-boot:run

   # 启动网关服务
   cd ../../gateway
   mvn spring-boot:run
   ```

## 接口测试

### 1. 查询商品

```bash
# 直接访问商品服务
curl http://localhost:9000/product/1

# 通过网关访问
curl http://localhost/api/product/1
```

### 2. 创建订单

```bash
# 直接访问订单服务
curl http://localhost:8000/create?userId=1001&productId=1

# 通过网关访问
curl http://localhost/api/order/create?userId=1001&productId=1
```

### 3. 获取配置

```bash
curl http://localhost:8000/config
```

## 项目特色

- ✅ 完整的微服务架构示例
- ✅ 服务注册与发现
- ✅ 配置中心管理
- ✅ API网关路由
- ✅ 服务间调用（Feign）
- ✅ 熔断降级保护
- ✅ 负载均衡
- ✅ 跨域处理
- ✅ 统一异常处理
- ✅ 请求拦截器

## 学习要点

通过本项目可以学习：

1. **微服务架构设计**: 如何拆分业务模块
2. **服务治理**: 服务注册、发现、配置管理
3. **API网关**: 路由、过滤、跨域处理
4. **服务通信**: 同步调用、负载均衡
5. **容错机制**: 熔断、降级、限流
6. **配置管理**: 集中配置、动态刷新

## 常见问题

### Q: 服务启动失败，连接Nacos超时？

A: 请检查Nacos服务是否已启动，并确认配置中的server-addr是否正确。

### Q: Sentinel控制台看不到服务？

A: 确保Sentinel Dashboard已启动，并且服务配置了`eager: true`以便立即注册。

### Q: 服务间调用失败？

A: 检查目标服务是否已注册到Nacos，确认服务名称是否正确。

### Q: 配置不生效？

A: 确认Nacos中的配置是否创建在正确的命名空间和分组下。

## 后续优化

- [ ] 集成分布式事务（Seata）
- [ ] 添加链路追踪（Sleuth + Zipkin）
- [ ] 实现统一认证授权（Spring Security + JWT）
- [ ] 添加缓存层（Redis）
- [ ] 消息队列集成（RocketMQ）
- [ ] 添加监控告警（Prometheus + Grafana）
- [ ] 容器化部署（Docker + Kubernetes）

## 贡献指南

欢迎提交Issue和Pull Request来改进本项目。

## 许可证

本项目采用 Apache License 2.0 开源协议，详情请查看 [开源协议.md](./开源协议.md) 和 [LICENSE.md](./LICENSE.md)。

本项目仅用于学习交流目的。

## 联系方式

如有问题，请提交Issue。

---

**注意**: 本项目为学习示例，生产环境使用请进行充分测试和优化。
