# MedSync - Patient Management System

MedSync is a comprehensive patient management system designed to streamline the process of managing healthcare information. This system is built using a microservices architecture, with each service focused on specific functionalities such as patient management, doctor management, appointment scheduling, billing, and authentication. Communication between services is handled using technologies like gRPC and Kafka for asynchronous event-driven messaging.

## Table of Contents

- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Setup and Installation](#setup-and-installation)
- [Running the Project](#running-the-project)
- [Service Communication](#service-communication)
- [Eureka Server](#eureka-server)

## Tech Stack

- **Frontend**: Not included (This project is focused on backend microservices).
- **Backend**:
  - **Spring Boot** (Java) for all microservices.
  - **gRPC** for synchronous communication between patient and billing services.
  - **Kafka** for event-driven communication, particularly the `PATIENT_CREATED_EVENT`.
  - **Eureka** for service discovery.
- **Databases**: Each service has its own database and all of them are postgresql
- **Containerization**: Docker for each microservice.

## Architecture

The MedSync system is designed with a microservices architecture where each service is responsible for a specific domain and is independently deployable. The communication between the services happens through RESTful APIs and gRPC for synchronous calls, and Kafka for asynchronous messaging. The system uses **Eureka Server** for service discovery to ensure smooth communication between the services.

<img width="617" alt="image" src="https://github.com/user-attachments/assets/7c504cb8-2dda-45f6-bc35-a9a859e3f811" />

### Components:

1. **Patient Service**: Manages patient-related data, including creation and updates. Uses gRPC to communicate with the Billing Service.
2. **Doctor Service**: Handles doctor information and availability.
3. **Appointment Service**: Manages appointment creation and tracking.
4. **Auth Service**: Responsible for authentication and user management.
5. **Billing Service**: Handles the billing accounts and transactions related to patients.
6. **API Gateway**: Acts as the entry point for client requests, routing them to appropriate microservices.
7. **Eureka Server**: A service registry to enable dynamic service discovery.

## Setup and Installation

To set up MedSync locally, follow these steps:

### 1. Clone the repository
```bash
git clone https://github.com/EssamMohamedAbo-ElMkarem/medsync.git
cd medsync
```

### 2. Build Docker Images (UNDER DEV)
```bash
docker-compose build
```

### 3. Start the Services
```bash
docker-compose up
```

The services will start, and Eureka Server will be available for service discovery.

## Running the Project

Once all services are up, the API Gateway will be accessible, routing requests to various microservices. For example:

- **Patient Service**: `http://localhost:4005/patients`
- **Doctor Service**: `http://localhost:4005/doctors`
- **Appointment Service**: `http://localhost:4005/appointments`
- **Auth Service**: `http://localhost:4005/auth`

## Service Communication

- **gRPC**: Patient Service and Billing Service communicate synchronously via gRPC for patient account creation.
- **Kafka**: When a new patient is created, the `PATIENT_CREATED_EVENT` is published to Kafka. All interested services (e.g., Billing, Analytics(Future)) consume this event to perform required actions.

## Eureka Server

Eureka Server is used for service discovery. All services (including the API Gateway) register with the Eureka Server, allowing them to discover each other dynamically. This means the API Gateway can route requests to the correct microservice even if its IP address or port changes.
