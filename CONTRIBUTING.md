# 贡献指南

感谢您对 Cloud Demo 项目的兴趣！我们欢迎任何形式的贡献，包括但不限于：

- 🐛 报告 bug
- 💡 提出新功能建议
- 📖 改进文档
- 🔧 提交代码修复
- 🌍 帮助翻译
- 💬 参与讨论

## 如何贡献

### 1. 报告 Bug

在提交 bug 之前，请先检查是否已有相关问题。如果没有，请创建新的 Issue，并包含以下信息：

- **Bug 描述**：清晰简洁地描述问题
- **复现步骤**：如何重现这个 bug
- **预期行为**：您期望发生什么
- **实际行为**：实际发生了什么
- **环境信息**：
  - 操作系统
  - Java 版本
  - Maven 版本
  - Spring Cloud Alibaba 版本
- **日志/截图**：相关的错误日志或截图
- **额外信息**：任何其他有助于理解问题的信息

### 2. 提出新功能建议

在提交功能建议前，请先考虑：

- 这个功能对大多数人有用吗？
- 是否已经有人提出过类似建议？
- 您愿意帮助实现这个功能吗？

提交时请包含：

- **功能描述**：清晰描述您想要的功能
- **使用场景**：这个功能解决了什么问题
- **解决方案**：您对实现的想法
- **替代方案**：您考虑过的其他解决方案

### 3. 提交代码

#### 设置开发环境

1. **Fork 项目**：点击页面右上角的 Fork 按钮
2. **克隆您的 Fork**：
   ```bash
   git clone https://github.com/your-username/cloud-demo.git
   cd cloud-demo
   ```

3. **添加上游仓库**：
   ```bash
   git remote add upstream https://github.com/original-owner/cloud-demo.git
   ```

4. **创建开发分支**：
   ```bash
   git checkout -b feature/your-feature-name
   ```

#### 开发流程

1. **遵循代码规范**：
   - 遵循 Java 代码规范
   - 使用有意义的变量和方法命名
   - 添加必要的注释
   - 保持代码简洁和可读性

2. **编写测试**：
   - 为新功能编写单元测试
   - 确保所有测试通过
   ```bash
   mvn test
   ```

3. **构建项目**：
   ```bash
   mvn clean install
   ```

4. **提交更改**：
   ```bash
   git add .
   git commit -m "feat: 添加用户登录功能"
   ```

#### 提交信息规范

我们使用 [Conventional Commits](https://www.conventionalcommits.org/) 规范：

```
<type>(<scope>): <subject>

<body>

<footer>
```

**类型 (type)**：
- `feat`: 新功能
- `fix`: 修复 bug
- `docs`: 文档更新
- `style`: 代码格式调整（不影响功能）
- `refactor`: 重构代码
- `test`: 测试相关
- `chore`: 构建过程或辅助工具的变动

**示例**：
```
feat(order): 添加订单批量创建功能

- 支持一次性创建多个订单
- 添加批量验证逻辑
- 优化数据库查询性能

Closes #123
```

#### 提交 Pull Request

1. **推送到您的 Fork**：
   ```bash
   git push origin feature/your-feature-name
   ```

2. **创建 Pull Request**：
   - 在 GitHub 上创建 PR
   - 填写 PR 模板中的所有信息
   - 关联相关的 Issue
   - 等待代码审查

3. **PR 检查清单**：
   - [ ] 代码遵循项目规范
   - [ ] 添加了必要的测试
   - [ ] 所有测试通过
   - [ ] 更新了相关文档
   - [ ] 提交信息清晰明确
   - [ ] 没有引入不必要的依赖

### 4. 改进文档

文档改进是非常有价值的贡献！您可以：

- 修正错别字和语法错误
- 改进代码注释
- 添加使用示例
- 更新过时的信息
- 翻译文档到其他语言

### 5. 参与讨论

我们鼓励您：

- 在 Issue 中帮助他人解决问题
- 参与功能讨论
- 分享您的使用经验
- 提供建设性的反馈

## 代码审查

所有提交的代码都需要经过审查，审查者会检查：

- 代码质量和风格
- 是否符合项目架构
- 是否有足够的测试覆盖
- 是否有安全漏洞
- 文档是否完整

请保持开放的心态接受反馈，并根据反馈进行修改。

## 发布流程

项目维护者会定期发布新版本：

1. **版本号**：遵循 [Semantic Versioning](https://semver.org/) 规范
2. **变更日志**：更新 CHANGELOG.md
3. **发布说明**：在 GitHub Release 中发布

## 社区准则

请阅读并遵守我们的 [行为准则](CODE_OF_CONDUCT.md)。

## 获取帮助

如果您在贡献过程中遇到任何问题：

- 查看 [文档](README.md)
- 搜索 [Issues](https://github.com/your-username/cloud-demo/issues)
- 提出新的 Issue
- 加入我们的 [讨论区](https://github.com/your-username/cloud-demo/discussions)

## 感谢您的贡献！

每一个贡献都让这个项目变得更好，无论大小。我们真诚感谢您的参与和支持！

---

**注意**：项目维护者保留在不通知的情况下修改本指南的权利。
