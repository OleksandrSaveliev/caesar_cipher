Saveliev Oleksandr

The JAR file is available in the root of this repository.

# Caesar Cipher

This is a command-line tool for encrypting and decrypting text using the Caesar cipher algorithm. The project is written in Java and built with Maven.

## Project Structure

The project is organized into several packages to ensure a clear separation of concerns:

*   `main`: The entry point of the application.
*   `action`: Contains classes that represent the different actions the program can perform, such as encryption, decryption, and brute-force attacks.
*   `app`: The main application runner that orchestrates the execution of the program.
*   `exception`: Custom exception classes for handling specific errors that may occur during program execution.
*   `model`: Contains the data models used in the application, such as `Command` and `ProgramOptions`.
*   `service`: Implements the core business logic, including the Caesar cipher algorithm and file I/O operations.
*   `ui`: Provides the command-line interface for interacting with the user.
*   `utils`: Includes utility classes for parsing and validating command-line arguments.
