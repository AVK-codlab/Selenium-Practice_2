# 🚀 Selenium Java – Advanced Automation Practice

This repository contains practical examples of advanced Selenium automation concepts using Java.  
It focuses on real-world scenarios like **data-driven testing**, **file handling**, **DevTools-based validation**, and **authentication workflows**.

---

## 📚 Topics Covered

### 📊 Data-Driven Testing with Excel
- Read test data from Excel files using **Apache POI**
- Drive test inputs and validations from external files
- Separate test logic from test data (flexible & scalable)

### 📁 File Upload & Download
- Automate file uploads using:
  - `sendKeys()` method
  - AutoIt
- Automate file downloads

### 🧪 Authentication Handling
- Handle basic HTTP authentication using:
  - URL credentials (`https://username:password@site.com`)
  - Alert popups (using `driver.switchTo().alert()`)
- Automate login via UI for form-based authentication

### 🧰 Chrome DevTools Protocol (CDP)
- Use Chrome DevTools APIs in Selenium (via `DevTools` class)
- Capture network traffic (e.g., API calls, status codes)
- Intercept and validate requests and responses
- Simulate network throttling and geolocation

---

## 🔧 Tech Stack

| Tool | Purpose |
|------|---------|
| **Java** | Core language |
| **Selenium WebDriver** | Web automation |
| **TestNG** | Test framework |
| **Apache POI** | Read/write Excel files |
| **Maven** | Build and dependency management |
| **Chrome DevTools** | Advanced browser-level interactions |
