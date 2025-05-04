# McpGoogleDrive

## Project Description

McpGoogleDrive is a Spring Boot application that interacts with Google Drive. It provides a set of RESTful API endpoints to perform various operations on Google Drive such as listing files, getting file details, searching files, creating folders, and downloading files as PDF.

## Features

- List files in Google Drive
- Get details of a specific file
- Search files by name
- Create folders in Google Drive
- Download files as PDF

## Prerequisites

- Java 17 or higher
- Gradle
- Google Cloud Platform account
- Google Drive API enabled
- Service account credentials JSON file

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/achrafhardizi/McpGoogleDrive.git
   cd McpGoogleDrive
   ```

2. Build the project using Gradle:
   ```bash
   ./gradlew build
   ```

## Configuration

1. Set up Google Drive API credentials:
   - Go to the [Google Cloud Console](https://console.cloud.google.com/).
   - Create a new project or select an existing project.
   - Enable the Google Drive API for the project.
   - Create a service account and download the JSON key file.
   - Place the JSON key file in the `src/main/resources/credentials` directory.

2. Configure the application properties:
   - Open the `src/main/resources/application.properties` file.
   - Set the `google.service.account.key` property to the path of the service account key file:
     ```properties
     google.service.account.key=credentials/service-account-key.json
     ```

## Usage

The application provides the following API endpoints:

- **List files**: `GET /api/drive/files`
  - Query parameters:
    - `pageSize` (optional): Number of files to list (default: 10)
  - Example request:
    ```bash
    curl -X GET "http://localhost:8080/api/drive/files?pageSize=5"
    ```

- **Get file details**: `GET /api/drive/files/{fileId}`
  - Path parameters:
    - `fileId`: ID of the file
  - Example request:
    ```bash
    curl -X GET "http://localhost:8080/api/drive/files/{fileId}"
    ```

- **Search files**: `GET /api/drive/search`
  - Query parameters:
    - `query`: Search query
  - Example request:
    ```bash
    curl -X GET "http://localhost:8080/api/drive/search?query=example"
    ```

- **Create folder**: `POST /api/drive/folders`
  - Query parameters:
    - `folderName`: Name of the folder to create
  - Example request:
    ```bash
    curl -X POST "http://localhost:8080/api/drive/folders?folderName=NewFolder"
    ```

- **Download file as PDF**: `GET /api/drive/files/{fileId}/pdf`
  - Path parameters:
    - `fileId`: ID of the file
  - Example request:
    ```bash
    curl -X GET "http://localhost:8080/api/drive/files/{fileId}/pdf" -o file.pdf
    ```

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
