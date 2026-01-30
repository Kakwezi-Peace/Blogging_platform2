Blogging Platform (Spring Boot + GraphQL + REST)

 Overview
This project is a Java Spring Boot blogging platform that supports both REST APIs and GraphQL endpoints.
It allows users to:

Create, read, update, and delete blog posts

Add and manage comments, reviews, tags, and post views

Track user activity with an Activity Log

Search posts by title or content

 GraphQL queries/mutations for flexible data access

 Tech Stack
Java 21

Spring Boot

Spring GraphQL

PostgreSQL (or any JDBC-compatible database)

Maven for dependency management

Swagger/OpenAPI for API documentation

📂 Project Structure
Code
src/main/java/com/example/Blogging_platform2/
│
├── controller/        # REST + GraphQL controllers
├── dto/               # Data Transfer Objects (DTOs)
├── exception/         # Custom exceptions
├── model/             # Entity models (Post, Comment, Review, Tag, PostView, ActivityLog)
├── repository/        # JDBC repositories
└── service/           # Business logic services
🔑 Key Features
Posts: CRUD operations with ownership checks

Comments: Linked to posts, created/deleted via GraphQL/REST

Reviews: Ratings (1–5) with validation

Tags: Many-to-many relationship with posts

Post Views: Tracks when a user views a post

Activity Logs: Records actions (VIEW_POST, CREATE_POST, UPDATE_POST, DELETE_POST)

Search: Simple text search on title/content

GraphQL Schema: Queries and mutations for all entities

 Running the Project
Clone the repository:

bash
git clone https://github.com/your-username/blogging-platform.git
cd blogging-platform
Configure your database in application.properties:

properties
spring.datasource.url=jdbc:postgresql://localhost:5432/blogdb
spring.datasource.username=youruser
spring.datasource.password=yourpassword
Build and run:

bash
mvn spring-boot:run
Access:

REST APIs → http://localhost:8080/api/posts

Swagger UI → http://localhost:8080/swagger-ui.html

GraphQL → http://localhost:8080/graphql


 Example GraphQL Queries/Mutations
Get Post by ID
graphql
query {
  getPost(id: 1) {
    id
    title
    content
    createdAt
  }
}
Create Comment
graphql
mutation {
  createComment(request: { postId: 1, userId: 2, content: "Nice post!" }) {
    commentId
    content
    createdAt
  }
}

 Error Handling
ResourceNotFoundException → when an entity is missing

PostNotFoundException → specific to posts

Validation errors (e.g., rating must be between 1–5)
