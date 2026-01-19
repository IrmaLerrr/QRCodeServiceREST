# QR Code Generator API

![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green?logo=springboot)
![Gradle](https://img.shields.io/badge/Gradle-7%2B-blue?logo=gradle)
![QR Code](https://img.shields.io/badge/QR%20Code-Generator-blueviolet)
![API Status](https://img.shields.io/badge/API-REST%20ful-informational)

A Spring Boot REST API for generating customizable QR codes on-the-fly. Generate QR codes for URLs, text, contact information, or any content with configurable size, format, and error correction.
This project was completed as part of the [Hyperskill](https://hyperskill.org/projects/385) educational project.

## Project Structure

```
qr-code-generator/
├── src/main/java/com/example/demo/
│   ├── MainApplication.java           # Spring Boot application entry point
│   ├── QRCodeController.java          # REST controller for QR code generation
│   ├── QRCodeGenerator.java           # Core QR code generation logic
│   ├── QRCodeConfiguration.java       # Application configuration
│   └── GlobalExceptionHandler.java    # Global exception handling
├── src/main/resources/
│   └── application.properties         # Application configuration
└── build.gradle                       # Gradle build configuration
```

## Features

- **Dynamic QR Generation**: Generate QR codes for any text or URL
- **Customizable Parameters**: 
  - Size (150-350 pixels)
  - Format (PNG, JPEG, GIF)
  - Error Correction Level (L, M, Q, H)
- **Health Check**: API health monitoring endpoint
- **Input Validation**: Comprehensive parameter validation
- **Error Handling**: Meaningful error responses

## API Endpoints

### 1. **Health Check**
```http
GET /api/health
```
**Response:** `200 OK` (empty response)

### 2. **Generate QR Code**
```http
GET /api/qrcode?contents={text}&size={pixels}&type={format}&correction={level}
```

**Query Parameters:**
| Parameter | Required | Default | Description | Valid Values |
|-----------|----------|---------|-------------|--------------|
| `contents` | ✅ Yes | - | Text to encode in QR code | Any string |
| `size` | ❌ No | 250 | Image size in pixels | 150-350 |
| `type` | ❌ No | png | Image format | png, jpeg, gif |
| `correction` | ❌ No | L | Error correction level | L, M, Q, H |

## Error Correction Levels

| Level | Recovery Capacity | Use Case |
|-------|------------------|----------|
| **L** | ~7% | Small QR codes, good readability |
| **M** | ~15% | Standard usage |
| **Q** | ~25% | Damaged environments |
| **H** | ~30% | Critical applications |

## Usage Examples

### Basic QR Code (Default Parameters)
```http
GET /api/qrcode?contents=https://example.com
```
Generates a 250px PNG QR code with low error correction.

### Custom QR Code
```http
GET /api/qrcode?contents=WIFI:S:MyNetwork;T:WPA;P:Password123;;&size=300&type=jpeg&correction=H
```
Generates a 300px JPEG QR code with high error correction for WiFi credentials.

### URL Encoding
```http
GET /api/qrcode?contents=https%3A%2F%2Fexample.com%2Fpage%3Fid%3D123
```
URL-encoded content is automatically decoded.

## Technology Stack

- **Spring Boot 3.x** - Web framework
- **Java 17+** - Programming language
- **Gradle** - Build automation
- **ZXing** (likely) - QR code generation library
- **BufferedImage** - Java image processing

## Getting Started

### Prerequisites
- Java 17 or higher
- Gradle 7+ (wrapper included)

### Build and Run

```bash
# Build the application
./gradlew build

# Run the application
./gradlew bootRun
```
