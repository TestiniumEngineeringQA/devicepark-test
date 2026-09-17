# Device Park – Regression

## 1. Purpose

- The purpose of this document is to clearly and sustainably explain the rationale behind the technology stack used for API and Appium-based test automation developed for the Device Park Control Center Web Application.

## 2. Overall Approach

- The test automation strategy is defined based on the following core principles:
- End-to-End (E2E) testability
- Executing API and mobile tests within the same technology ecosystem
- Seamless integration with CI/CD pipelines
- Low maintenance cost and long-term sustainability

## 3. Selected Technologies and Rationale

### 3.1 Programming Language – Java

- Mature and well-established ecosystem for both Appium and API testing
- Strong type safety, reducing runtime errors in test code
- Widely adopted in enterprise environments with long-term support

### 3.2 Build & Dependency Management – Maven

- Centralized and versioned dependency management
- Flexible structure for defining test profiles (API, Appium, regression, etc.)
- High compatibility with CI/CD tools (GitHub Actions, Jenkins, etc.)
- Standardized and readable project structure

### 3.3 API Test Automation – Rest Assured

- Java-based DSL, eliminating the need for additional languages or frameworks
- Clean and readable syntax for REST and JSON-based API testing
- Native support for token-based authentication flows
- Powerful assertion capabilities and schema validation support

### 3.4 JSON Serialization / Deserialization – Jackson

- One of the most widely used JSON processing libraries in the Java ecosystem
- Simplifies POJO ↔ JSON conversion for API request and response bodies
- Enables safe and maintainable parsing of complex response models (nested objects, arrays, enums)
- Seamlessly integrates with Rest Assured and TestNG
- Provides long-term stability and strong community support

### 3.5 Mobile Test Automation – Appium

- Single framework support for both Android and iOS platforms
- Compatible with real devices and emulators
- Well-aligned with Device Park features such as video recording and session management
- Java client support enables sharing the same codebase with API tests

### 3.6 Test Framework – TestNG

- Fine-grained control over the test lifecycle (Before / After hooks)
- Native support for parallel test execution
- Grouping, prioritization, and suite-based test management
- Ability to execute API and Appium tests within the same test suite

### 3.7 Logging – SLF4J + Log4j

- Clear visibility of test steps and API request/response flows
- Configurable log levels for efficient debugging and analysis
- Centralized log collection support in CI environments

## 4. Automation Scope

- Using this technology stack, the following test areas are covered:
- Authentication & Authorization (Access Token generation)
- Device Allocation & Queue management scenarios
- Session & Video Recording lifecycle validations
- Storage and file management API tests
- End-to-End scenarios combining API and Appium tests