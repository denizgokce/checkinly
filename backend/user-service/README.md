# User Service

## Overview

The User Service is a microservice responsible for managing user data and handling authentication using JWT (JSON Web Token). It is built with Spring Boot and Kotlin, and it uses PostgreSQL for data storage. The service is designed to run in a Kubernetes cluster with NGINX Ingress acting as an application layer load balancer.

## Features

- CRUD operations for user management
- JWT authentication
- Integration with PostgreSQL
- Kubernetes deployment with NGINX Ingress

## Prerequisites

- Docker
- Kubernetes cluster
- Helm (optional, for installing NGINX Ingress Controller)

## Setup Instructions

### 1. Build the User Service

Navigate to the `user-service` directory and build the service using Maven:

```bash
cd checkinly/backend/user-service
mvn clean install