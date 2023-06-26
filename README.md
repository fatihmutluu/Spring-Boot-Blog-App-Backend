# Blog App Backend

A backend application built with Spring Boot, Spring Security, Spring Data JPA, and MySQL. This application provides a feature-rich environment for bloggers to create, publish, and manage their articles. With its robust backend architecture.

## Features
- **User Authentication:** Blogify ensures secure user authentication and authorization, allowing bloggers to create accounts, manage their profiles, and protect their content.
- **Article Creation and Editing:** Users can easily create and edit blog articles, enabling them to express their thoughts and ideas effortlessly.
- **Categories:** Blogify offers a flexible categorization system, enabling users to organize their articles and enhance discoverability.
- **Commenting System:** Readers can engage with blog posts by leaving comments, promoting discussions, and sharing their opinions on various topics.
- **Search:** Visitors can explore articles through a robust search functionality, helping them find relevant content based on keywords or categories.

## Installation
### Prerequisites
- Install Java Development Kit (JDK) and set up the environment variables.
- Install MySQL and create a new database named "myblog".

1. Clone this repository: `git clone https://github.com/your-username/your-repo.git`
2. Navigate to the backend directory: `cd your-repo`
3. Import the project into your preferred IDE (e.g., IntelliJ, Eclipse).
4. Update the MySQL database connection details in the `application.properties` file.
5. Build and run the Spring Boot application.

### Postman Collection
1. Import the provided Postman collection file (`your-repo/postman_collection.json`) into your Postman application.
2. Update the API endpoints in the collection to match your backend server URL.

Make sure to update the `application.properties` file with the correct MySQL database connection details. This file allows you to configure various aspects of the application, such as database credentials and server settings.

## Contributing
Contributions are welcome! If you encounter any issues or have suggestions for improvements, please open an issue or submit a pull request.

## Acknowledgements
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [MySQL](https://www.mysql.com/)

Feel free to customize the installation instructions based on your specific project structure and additional requirements. Ensure that you have a MySQL database set up and running before launching the application.
