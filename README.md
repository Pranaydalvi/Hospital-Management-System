# Hospital Management System

## Project Overview

The Hospital Management System is a project developed using Spring Boot. It is designed to manage hospital operations such as user requests and roles within the system. The project uses JPA for entity management and Lombok to reduce boilerplate code.

## Project Structure

### Entities

1. **Login**
   - Represents the login details of users.
   - Fields: `id`, `email`, `password`
   - Relationships: One-to-One with `UserRequest`

2. **UserRequest**
   - Represents user requests and their details.
   - Fields: `id`, `usernumber`, `firstname`, `lastname`, `address`, `zipcode`, `country`, `gender`, `mobNumber`
   - Relationships: One-to-One with `Role`

3. **Role**
   - Represents the roles assigned to users.
   - Fields: `id`, `rolename`

### Lombok Annotations

- `@Getter` and `@Setter`: Automatically generate getter and setter methods.
- `@AllArgsConstructor`: Generates a constructor with parameters for all fields.
- `@NoArgsConstructor`: Generates a no-argument constructor.
- `@ToString`: Generates a `toString` method.

## Setup

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/hospital-management-system.git

2. **Navigate to the project directory:**
    ```bash
    cd YourRepositoryName
    ```
3. **Install dependencies:**
    ```bash
    // Commands to install dependencies
    ```
4. **Run the application:**
    ```bash
    // Command to run the application
    ```

## Usage

Once the application is running, you can interact with the endpoints as per the defined controllers in your Spring Boot application.


## Contact

If you have any questions or suggestions, feel free to reach out to me at pranaydalvi122@gmail.com

