# Contributing Guide

Thank you for your interest in the Cloud Demo project! We welcome contributions of all kinds, including but not limited to:

- 🐛 Report bugs
- 💡 Suggest new features
- 📖 Improve documentation
- 🔧 Submit code fixes
- 🌍 Help with translations
- 💬 Participate in discussions

## How to Contribute

### 1. Report Bugs

Before submitting a bug, please check if a related issue already exists. If not, please create a new Issue and include the following information:

- **Bug Description**: Clearly and concisely describe the problem
- **Reproduction Steps**: How to reproduce this bug
- **Expected Behavior**: What you expected to happen
- **Actual Behavior**: What actually happened
- **Environment Information**:
  - Operating System
  - Java Version
  - Maven Version
  - Spring Cloud Alibaba Version
- **Logs/Screenshots**: Relevant error logs or screenshots
- **Additional Information**: Any other information that helps understand the problem

### 2. Suggest New Features

Before submitting feature suggestions, please consider:

- Is this feature useful for most people?
- Has anyone else suggested something similar?
- Are you willing to help implement this feature?

When submitting, please include:

- **Feature Description**: Clearly describe the feature you want
- **Use Cases**: What problem does this feature solve
- **Solution**: Your thoughts on implementation
- **Alternatives**: Other solutions you've considered

### 3. Submit Code

#### Setting Up Development Environment

1. **Fork the Project**: Click the Fork button in the top right corner
2. **Clone Your Fork**:
   ```bash
   git clone https://github.com/your-username/cloud-demo.git
   cd cloud-demo
   ```

3. **Add Upstream Repository**:
   ```bash
   git remote add upstream https://github.com/original-owner/cloud-demo.git
   ```

4. **Create Development Branch**:
   ```bash
   git checkout -b feature/your-feature-name
   ```

#### Development Process

1. **Follow Code Standards**:
   - Follow Java coding standards
   - Use meaningful variable and method names
   - Add necessary comments
   - Keep code concise and readable

2. **Write Tests**:
   - Write unit tests for new features
   - Ensure all tests pass
   ```bash
   mvn test
   ```

3. **Build Project**:
   ```bash
   mvn clean install
   ```

4. **Commit Changes**:
   ```bash
   git add .
   git commit -m "feat: add user login functionality"
   ```

#### Commit Message Conventions

We use the [Conventional Commits](https://www.conventionalcommits.org/) specification:

```
<type>(<scope>): <subject>

<body>

<footer>
```

**Types (type)**:
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation updates
- `style`: Code formatting changes (no functional changes)
- `refactor`: Code refactoring
- `test`: Test related
- `chore`: Build process or auxiliary tool changes

**Example**:
```
feat(order): add batch order creation functionality

- Support creating multiple orders at once
- Add batch validation logic
- Optimize database query performance

Closes #123
```

#### Submit Pull Request

1. **Push to Your Fork**:
   ```bash
   git push origin feature/your-feature-name
   ```

2. **Create Pull Request**:
   - Create a PR on GitHub
   - Fill in all information in the PR template
   - Link related Issues
   - Wait for code review

3. **PR Checklist**:
   - [ ] Code follows project standards
   - [ ] Necessary tests have been added
   - [ ] All tests pass
   - [ ] Relevant documentation has been updated
   - [ ] Commit messages are clear and specific
   - [ ] No unnecessary dependencies introduced

### 4. Improve Documentation

Documentation improvements are very valuable contributions! You can:

- Fix typos and grammatical errors
- Improve code comments
- Add usage examples
- Update outdated information
- Translate documentation to other languages

### 5. Participate in Discussions

We encourage you to:

- Help others solve problems in Issues
- Participate in feature discussions
- Share your usage experience
- Provide constructive feedback

## Code Review

All submitted code needs to go through review. Reviewers will check:

- Code quality and style
- Compliance with project architecture
- Sufficient test coverage
- Security vulnerabilities
- Completeness of documentation

Please maintain an open mind to accept feedback and make modifications accordingly.

## Release Process

Project maintainers will regularly release new versions:

1. **Version Number**: Follow [Semantic Versioning](https://semver.org/) specification
2. **Changelog**: Update CHANGELOG.md
3. **Release Notes**: Publish in GitHub Release

## Community Guidelines

Please read and follow our [Code of Conduct](CODE_OF_CONDUCT.md).

## Getting Help

If you encounter any problems during contribution:

- Check [Documentation](README.md)
- Search [Issues](https://github.com/your-username/cloud-demo/issues)
- Submit a new Issue
- Join our [Discussions](https://github.com/your-username/cloud-demo/discussions)

## Thank You for Your Contribution!

Every contribution makes this project better, regardless of size. We sincerely appreciate your participation and support!

---

**Note**: Project maintainers reserve the right to modify this guide without notice.
