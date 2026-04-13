# 常见问题 (FAQ)

本文档收集了 Cloud Demo 项目的常见问题和解决方案。

## 项目相关

### Q: 这个项目适合什么水平的学习者？

**A**: 
- 🟢 **初学者**: 如果你有 Spring Boot 基础，可以直接上手学习
- 🟡 **中级开发者**: 可以参考架构设计和最佳实践
- 🔴 **高级开发者**: 可以作为微服务项目的参考模板

### Q: 项目是否适合生产环境使用？

**A**: 
本项目主要用于学习和演示目的。如果要用于生产环境，需要：
- 完善认证授权机制
- 添加监控和日志系统
- 进行安全加固
- 性能优化和压力测试
- 完善错误处理和重试机制

### Q: 如何参与项目开发？

**A**: 
请参考 [CONTRIBUTING.md](./CONTRIBUTING.md) 了解详细的贡献指南。主要步骤：
1. Fork 项目
2. 创建功能分支
3. 提交代码
4. 发起 Pull Request

## 环境配置

### Q: Nacos 启动失败怎么办？

**A**: 常见解决方案：
1. 检查端口 8848 是否被占用
2. 确保 Java 版本正确 (JDK 8+)
3. 检查内存是否充足
4. 查看启动日志定位具体错误

```bash
# 检查端口占用
netstat -ano | findstr :8848

# 查看 Nacos 日志
tail -f logs/nacos.log
```

### Q: Sentinel 控制台看不到服务？

**A**: 检查以下几点：
1. 确认 Sentinel Dashboard 已启动
2. 检查服务配置中的 `eager: true`
3. 确认网络连接正常
4. 检查配置的 dashboard 地址是否正确

### Q: 服务启动后无法注册到 Nacos？

**A**: 
1. 检查 Nacos 服务是否正常运行
2. 确认配置中的 `server-addr` 是否正确
3. 检查网络连接
4. 查看 Nacos 控制台的服务列表

## 开发调试

### Q: 如何调试微服务调用？

**A**: 
1. 在 IDE 中设置断点
2. 启动相关服务
3. 使用 Postman 或 curl 发起请求
4. 查看日志输出
5. 使用 Sentinel 控制台监控调用链路

### Q: 如何查看服务调用链路？

**A**: 
- 使用 Sentinel 控制台查看实时监控
- 查看应用日志中的调用信息
- 后续可以集成 Sleuth + Zipkin 实现分布式链路追踪

### Q: 如何修改服务端口？

**A**: 
在各服务的 `application.yml` 中修改 `server.port` 配置：

```yaml
server:
  port: 8081  # 修改为你想要的端口
```

## 功能使用

### Q: 如何测试服务间调用？

**A**: 
```bash
# 测试订单服务调用商品服务
curl "http://localhost:8000/create?userId=1001&productId=1"

# 通过网关测试
curl "http://localhost/api/order/create?userId=1001&productId=1"
```

### Q: 如何配置熔断降级规则？

**A**: 
1. 访问 Sentinel 控制台 (http://localhost:8080)
2. 选择对应的服务
3. 在"流控规则"或"熔断规则"中添加规则
4. 规则会实时生效

### Q: 如何动态刷新配置？

**A**: 
1. 修改 Nacos 中的配置文件
2. 在服务类上添加 `@RefreshScope` 注解
3. 或者通过 Nacos 控制台手动刷新
4. 配置会自动生效

## 部署相关

### Q: 如何使用 Docker 部署？

**A**: 
```bash
# 构建镜像
docker-compose build

# 启动所有服务
docker-compose up -d

# 查看日志
docker-compose logs -f

# 停止服务
docker-compose down
```

### Q: 如何单独部署某个服务？

**A**: 
```bash
# 只启动商品服务
docker-compose up -d product-service

# 只启动网关
docker-compose up -d gateway
```

### Q: 如何查看服务健康状态？

**A**: 
```bash
# 检查 Docker 容器状态
docker-compose ps

# 查看服务健康检查
curl http://localhost:8080/product/1
```

## 故障排查

### Q: 服务启动报错 "Connection refused"

**A**: 
1. 检查依赖服务是否已启动
2. 确认端口配置正确
3. 检查防火墙设置
4. 查看具体错误日志

### Q: 出现 "No service available" 错误

**A**: 
1. 检查服务是否已注册到 Nacos
2. 确认服务名称拼写正确
3. 检查负载均衡配置
4. 查看 Nacos 控制台的服务列表

### Q: 内存溢出怎么办？

**A**: 
1. 增加 JVM 堆内存：
```bash
java -Xms512m -Xmx1024m -jar app.jar
```

2. 在 `application.yml` 中配置：
```yaml
spring:
  jvm:
    args: "-Xms512m -Xmx1024m"
```

## 性能优化

### Q: 如何提高服务性能？

**A**: 
1. 启用 JVM 参数优化
2. 使用连接池
3. 添加缓存层
4. 优化数据库查询
5. 使用异步处理

### Q: 如何进行压力测试？

**A**: 
可以使用以下工具：
- JMeter
- Gatling
- Apache Bench (ab)
- wrk

示例：
```bash
# 使用 Apache Bench
ab -n 1000 -c 10 http://localhost:8080/product/1
```

## 安全相关

### Q: 项目是否安全？

**A**: 
当前版本主要关注功能实现，生产使用需要：
- 添加认证授权
- 数据加密传输 (HTTPS)
- 输入验证和过滤
- SQL 注入防护
- XSS 防护

### Q: 如何添加认证？

**A**: 
可以集成 Spring Security + JWT：
1. 添加 Spring Security 依赖
2. 配置安全规则
3. 实现 JWT 认证
4. 添加权限注解

## 其他问题

### Q: 项目技术栈可以替换吗？

**A**: 
可以，但需要注意：
- 确保替代组件功能兼容
- 更新相关配置
- 进行充分测试
- 更新文档

### Q: 如何联系技术支持？

**A**: 
- 提交 [GitHub Issue](https://github.com/your-username/cloud-demo/issues)
- 发送邮件至 support@cloud-demo.example.com
- 加入我们的 [Discord 社区](https://discord.gg/your-invite-link)

### Q: 项目有版本规划吗？

**A**: 
请查看 [CHANGELOG.md](./CHANGELOG.md) 了解版本历史和规划。

## 仍有疑问？

如果以上FAQ没有解决您的问题，请：

1. 搜索 [GitHub Issues](https://github.com/your-username/cloud-demo/issues)
2. 提交新的 Issue
3. 联系项目维护者

---

**最后更新**: 2025年4月13日
