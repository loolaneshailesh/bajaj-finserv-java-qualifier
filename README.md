# Bajaj Finserv Health Java Qualifier Solution

This Spring Boot application automatically executes the required workflow for the Bajaj Finserv Health Java Qualifier challenge.

## Features

- **Automatic Execution**: Runs on application startup using CommandLineRunner
- **Webhook Generation**: Sends POST request to generate webhook URL and access token
- **SQL Problem Solving**: Determines and solves SQL question based on registration number
- **JWT Authentication**: Submits solution using JWT token in Authorization header
- **RESTful API Integration**: Uses RestTemplate for HTTP communications

## Project Structure

```
src/main/java/com/bajajfinserv/webhooksolution/
├── WebhookSolutionApplication.java     # Main application class
├── WebhookService.java                 # Handles webhook generation and submission
├── SqlSolutionService.java             # Solves SQL problems based on regNo
├── dto/
│   ├── WebhookRequest.java             # Request DTO for webhook generation
│   ├── WebhookResponse.java            # Response DTO from webhook generation
│   └── SolutionRequest.java            # Request DTO for solution submission
└── config/
    └── ApplicationConfig.java          # Configuration beans
```

## How It Works

1. **Startup**: Application starts and triggers the workflow via CommandLineRunner
2. **Webhook Generation**: Sends POST request with name, regNo, and email to generate webhook
3. **Question Assignment**: Determines SQL question based on last two digits of regNo:
   - Odd digits → Question 1
   - Even digits → Question 2
4. **Solution Submission**: Sends the SQL solution using JWT token authentication

## Configuration

Update the following in `SqlSolutionService.java`:
- Replace placeholder SQL queries with actual solutions from Google Drive links
- Modify personal details in `WebhookService.java` if needed

## Google Drive Links

- **Question 1 (Odd regNo)**: https://drive.google.com/file/d/1IeSI6l6KoSQAFfRihIT9tEDICtoz-G/view?usp=sharing
- **Question 2 (Even regNo)**: https://drive.google.com/file/d/143MR5cLFrlNEuHzzWJ5RHnEWuijuM9X/view?usp=sharing

## Build and Run

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build JAR
```bash
mvn clean package
```

### Run Application
```bash
java -jar target/webhook-solution-1.0.0.jar
```

### Run with Maven
```bash
mvn spring-boot:run
```

## API Endpoints Used

1. **Generate Webhook**:
   - URL: `https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
   - Method: POST
   - Body: `{"name": "John Doe", "regNo": "REG12347", "email": "john@example.com"}`

2. **Submit Solution**:
   - URL: `https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA`
   - Method: POST
   - Headers: `Authorization: <accessToken>`, `Content-Type: application/json`
   - Body: `{"finalQuery": "YOUR_SQL_QUERY_HERE"}`

## Important Notes

- No controllers or endpoints trigger the flow - everything runs automatically on startup
- Uses RestTemplate for HTTP client operations
- JWT token is used for authentication in the final submission
- Application logs all important steps for debugging

## Customization

Before running:
1. Access the Google Drive links to get the actual SQL questions
2. Update the SQL solutions in `SqlSolutionService.java`
3. Modify personal details (name, regNo, email) in `WebhookService.java` if needed

## Project Setup

1. Create the following directory structure:
```
webhook-solution/
├── pom.xml
├── README.md
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── bajajfinserv/
│       │           └── webhooksolution/
│       │               ├── WebhookSolutionApplication.java
│       │               ├── WebhookService.java
│       │               ├── SqlSolutionService.java
│       │               ├── dto/
│       │               │   ├── WebhookRequest.java
│       │               │   ├── WebhookResponse.java
│       │               │   └── SolutionRequest.java
│       │               └── config/
│       │                   └── ApplicationConfig.java
│       └── resources/
│           └── application.properties
```

2. Copy all the provided Java files to their respective directories
3. Build and run the application

## JAR File

The built JAR file will be available at: `target/webhook-solution-1.0.0.jar`

## Submission Requirements

- Public GitHub repository with code
- Built JAR file
- Raw downloadable link to JAR
- Submit via: https://forms.office.com/r/5Kzb1h7fre

## Author

Created for Bajaj Finserv Health Java Qualifier Challenge