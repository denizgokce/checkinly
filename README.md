# Checkinly

## Overview

Checkinly is a microservices-based application designed to manage hotel bookings. It consists of several services, including User Service, Hotel Service, Booking Service, and Payment Service. Each service is built with Spring Boot and Kotlin, and they communicate with each other using Kafka for event-driven communication. The services are containerized using Docker and can be deployed in a Kubernetes cluster.

## Features

- User management with JWT authentication
- Hotel management with room availability tracking
- Booking management with Kafka integration
- Payment management with MongoDB
- Redis caching for room availability
- Dockerized services for easy deployment
- Kubernetes deployment with NGINX Ingress

## Prerequisites

- Docker
- Docker Compose
- Kubernetes cluster
- Helm (optional, for installing NGINX Ingress Controller)
- MongoDB
- Kafka
- Redis

## Setup Instructions

### 1. Clone the Repository

Clone the repository to your local machine:

```bash
git clone https://github.com/yourusername/checkinly.git
cd checkinly
```

### 2. Build the Services
Navigate to each service directory and build the services using Maven:

```bash
cd backend/user-service
mvn clean install

cd ../hotel-service
mvn clean install

cd ../booking-service
mvn clean install

cd ../payment-service
mvn clean install
```

###  Dockerize the Services
Build the Docker images for each service:

```bash
cd backend/user-service
docker build -t user-service:latest .

cd ../hotel-service
docker build -t hotel-service:latest .

cd ../booking-service
docker build -t booking-service:latest .

cd ../payment-service
docker build -t payment-service:latest .
```

### 4. Run the Services with Docker Compose
Use Docker Compose to run all the services along with MongoDB, Kafka, and Redis:

```bash
docker-compose up --build
```

### 5. Access the Services
The services will be accessible at the following URLs:

- **User Service: http://localhost:8081
- **Hotel Service: http://localhost:8082
- **Booking Service: http://localhost:8083
- **Payment Service: http://localhost:8084

### 6. Kubernetes Deployment

#### Install NGINX Ingress Controller

Install the NGINX Ingress Controller in your Kubernetes cluster:

##### Using Helm

```bash
helm repo add ingress-nginx https://kubernetes.github.io/ingress-nginx
helm repo update
helm install nginx-ingress ingress-nginx/ingress-nginx
```

##### Using YAML Manifests

```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/cloud/deploy.yaml
```

### Deploy the Services

Apply the Kubernetes deployment and service files for each service:

```bash
kubectl apply -f file:///c%3A/Users/Deniz/JavaPlayground/checkinly/kubernetes/user-service-deployment.yml
kubectl apply -f file:///c%3A/Users/Deniz/JavaPlayground/checkinly/kubernetes/user-service-service.yml

kubectl apply -f file:///c%3A/Users/Deniz/JavaPlayground/checkinly/kubernetes/hotel-service-deployment.yml
kubectl apply -f file:///c%3A/Users/Deniz/JavaPlayground/checkinly/kubernetes/hotel-service-service.yml

kubectl apply -f file:///c%3A/Users/Deniz/JavaPlayground/checkinly/kubernetes/booking-service-deployment.yml
kubectl apply -f file:///c%3A/Users/Deniz/JavaPlayground/checkinly/kubernetes/booking-service-service.yml

kubectl apply -f file:///c%3A/Users/Deniz/JavaPlayground/checkinly/kubernetes/payment-service-deployment.yml
kubectl apply -f file:///c%3A/Users/Deniz/JavaPlayground/checkinly/kubernetes/payment-service-service.yml
```

### Create Ingress Resource

Create an Ingress resource to define the routing rules for your services:

```bash
apiVersion: networking.k8s.io/v1
kind: Ingress
metadata:
  name: checkinly-ingress
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /
    nginx.ingress.kubernetes.io/auth-url: "http://user-service/api/user/authenticate"
    nginx.ingress.kubernetes.io/auth-signin: "http://user-service/login"
spec:
  rules:
  - host: checkinly.local
    http:
      paths:
      - path: /api/user/authenticate
        pathType: Prefix
        backend:
          service:
            name: user-service
            port:
              number: 80
      - path: /api/user
        pathType: Prefix
        backend:
          service:
            name: user-service
            port:
              number: 80
      - path: /api/booking
        pathType: Prefix
        backend:
          service:
            name: booking-service
            port:
              number: 80
      - path: /api/payment
        pathType: Prefix
        backend:
          service:
            name: payment-service
            port:
              number: 80
      - path: /
        pathType: Prefix
        backend:
          service:
            name: frontend
            port:
              number: 80
```

Apply the Ingress resource:

```bash
kubectl apply -f file:///c%3A/Users/Deniz/JavaPlayground/checkinly/kubernetes/ingress.yml
```

### 7. Access the Application

Add an entry to your `/etc/hosts` file to map `checkinly.local` to your Kubernetes cluster's IP address:

```plaintext
<your-cluster-ip> checkinly.local
```

Now you can access the application at `http://checkinly.local`.

## API Endpoints

### User Service

- **Get All Users:** `GET /users`
- **Get User by ID:** `GET /users/{id}`
- **Create User:** `POST /users`
- **Update User:** `PUT /users/{id}`
- **Delete User:** `DELETE /users/{id}`
- **Authenticate User:** `POST /users/authenticate`

### Hotel Service

- **Get All Hotels:** `GET /hotels`
- **Get Hotel by ID:** `GET /hotels/{id}`
- **Create Hotel:** `POST /hotels`
- **Update Hotel:** `PUT /hotels/{id}`
- **Delete Hotel:** `DELETE /hotels/{id}`
- **Get All Rooms:** `GET /rooms`
- **Get Rooms by Hotel ID:** `GET /rooms/hotel/{hotelId}`
- **Get Available Rooms by Hotel ID:** `GET /rooms/hotel/{hotelId}/available`
- **Create Room:** `POST /rooms`
- **Update Room:** `PUT /rooms/{id}`
- **Delete Room:** `DELETE /rooms/{id}`

### Booking Service

- **Get All Bookings:** `GET /bookings`
- **Get Booking by ID:** `GET /bookings/{id}`
- **Create Booking:** `POST /bookings`
- **Update Booking:** `PUT /bookings/{id}`
- **Delete Booking:** `DELETE /bookings/{id}`

### Payment Service

- **Get All Payments:** `GET /payments`
- **Get Payment by ID:** `GET /payments/{id}`
- **Create Payment:** `POST /payments`
- **Update Payment:** `PUT /payments/{id}`
- **Delete Payment:** `DELETE /payments/{id}`

## Environment Variables

The following environment variables can be configured for each service:

- `SPRING_DATA_MONGODB_URI`: MongoDB connection URI
- `SPRING_KAFKA_BOOTSTRAP_SERVERS`: Kafka bootstrap servers
- `REDIS_HOST`: Redis host
- `REDIS_PORT`: Redis port

## Running Tests

To run the tests for each service, navigate to the service directory and use the following command:

```bash
mvn test
```

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any improvements or bug fixes.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact

For any questions or support, please contact [denizgokce93@gmail.com](mailto:denizgokce93@gmail.com).