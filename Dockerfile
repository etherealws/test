# 多阶段构建 - 构建阶段
FROM maven:3.8.6-openjdk-17-slim AS build

WORKDIR /app

# 复制 pom.xml 并下载依赖
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 复制源代码
COPY src ./src
COPY gateway ./gateway
COPY services ./services
COPY model ./model

# 构建项目
RUN mvn clean package -DskipTests -B

# 运行阶段 - 网关服务
FROM openjdk:17-slim AS gateway

WORKDIR /app

# 复制构建产物
COPY --from=build /app/gateway/target/*.jar app.jar

# 创建非root用户
RUN groupadd -r appuser && useradd -r -g appuser appuser
RUN chown -R appuser:appuser /app
USER appuser

# 暴露端口
EXPOSE 80

# 健康检查
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:80/actuator/health || exit 1

# 启动应用
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# 运行阶段 - 商品服务
FROM openjdk:17-slim AS product-service

WORKDIR /app

# 复制构建产物
COPY --from=build /app/services/service-product/target/*.jar app.jar

# 创建非root用户
RUN groupadd -r appuser && useradd -r -g appuser appuser
RUN chown -R appuser:appuser /app
USER appuser

# 暴露端口
EXPOSE 9000

# 健康检查
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:9000/actuator/health || exit 1

# 启动应用
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# 运行阶段 - 订单服务
FROM openjdk:17-slim AS order-service

WORKDIR /app

# 复制构建产物
COPY --from=build /app/services/service-order/target/*.jar app.jar

# 创建非root用户
RUN groupadd -r appuser && useradd -r -g appuser appuser
RUN chown -R appuser:appuser /app
USER appuser

# 暴露端口
EXPOSE 8000

# 健康检查
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
  CMD curl -f http://localhost:8000/actuator/health || exit 1

# 启动应用
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
