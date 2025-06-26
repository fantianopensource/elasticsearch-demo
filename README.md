# Elasticsearch Demo

A demonstration project using Spring Boot, MySQL, and Elasticsearch to showcase how to integrate these technologies for building search functionality.

## Features

- Spring Boot 3.2.0 application
- MySQL database storage
- Elasticsearch 8.11.0 search engine
- Kibana 8.11.0 for data visualization and management
- RESTful API endpoints
- Article and user management
- Full-text search capabilities

## Tech Stack

- **Backend Framework**: Spring Boot 3.2.0
- **Database**: MySQL 8.0
- **Search Engine**: Elasticsearch 8.11.0
- **Data Visualization**: Kibana 8.11.0
- **Java Version**: 17
- **Build Tool**: Maven

## Quick Start

### Prerequisites

- Java 17+
- Docker and Docker Compose
- Maven 3.6+

### Getting Started

1. Clone the repository
```bash
git clone git@github.com:fantianopensource/elasticsearch-demo.git
cd elasticsearch-demo
```

2. Start dependencies with Docker Compose
```bash
docker-compose up -d
```

3. Run the Spring Boot application
```bash
mvn spring-boot:run
```

4. Access the application
- Application: http://localhost:8080
- Elasticsearch: http://localhost:9200
- Kibana: http://localhost:5601
- MySQL: localhost:3306

## Services Overview

The project includes the following services:

- **MySQL**: Primary database for storing application data
- **Elasticsearch**: Search engine for full-text search capabilities
- **Kibana**: Web interface for visualizing and managing Elasticsearch data
- **Spring Boot App**: Main application providing REST APIs

## API Endpoints

The project provides the following REST API endpoints:

- `GET /api/articles` - Get all articles
- `POST /api/articles` - Create a new article
- `GET /api/articles/{id}` - Get article by ID
- `GET /api/users` - Get all users
- `POST /api/users` - Create a new user

For detailed API documentation, please refer to the `apis.http` file.

## Project Structure

```
src/main/java/com/example/elasticsearchdemo/
├── controller/          # REST controllers
├── dto/                # Data transfer objects
├── entity/             # JPA entities
├── es/                 # Elasticsearch documents
├── repository/         # Data access layer
└── service/            # Business logic layer
```

## License

This project is licensed under the [MIT License](LICENSE).

## Contributing

Issues and pull requests are welcome to improve this project.

## Contact

For questions, please contact the project maintainers. 