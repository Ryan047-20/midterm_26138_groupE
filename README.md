#  Bus Stop Finder

A Spring Boot REST API designed to help commuters in Rwanda easily locate bus stops and view essential information about routes that serve each stop.

##  Project Overview

Bus Stop Finder addresses the problem of inaccessible public transport information by providing a centralized platform where users can quickly access bus stop details and route information.

###  Target Users
- Daily commuters
- Students and workers using public transport
- Visitors unfamiliar with local bus routes

---

##  Database Design

### Entity Relationship Diagram
The system uses **7 database tables** with the following relationships:

| Relationship | Tables | Type |
|---|---|---|
| Province → Location | 1 province, many locations | One-to-Many |
| Location → BusStop | 1 location, 1 bus stop | One-to-One |
| BusStop ↔ Route | via bus_stop_route join table | Many-to-Many |
| Province → User | 1 province, many users | One-to-Many |

### Entities
- **Province** - Rwanda's provinces (e.g. Kigali, Northern...)
- **Location** - Physical address with cell, sector, district details
- **BusStop** - Named bus stop at a specific location
- **Route** - Bus line with start and end points
- **User** - Registered commuter belonging to a province

---

##  Technologies Used

- **Java 21**
- **Spring Boot 3.3.x**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**

---

## Setup Instructions

### Prerequisites
- Java 21
- PostgreSQL
- Maven

### Steps
1. Clone the repository:
```
git clone https://github.com/Ryan047-20/bus-stop-finder.git
```

2. Create a PostgreSQL database:
```sql
CREATE DATABASE busstopfinder;
```

3. Update `application.properties` with your database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/busstopfinder
spring.datasource.username=postgres
spring.datasource.password=yourpassword
```

4. Run the application:
```
mvn spring-boot:run
```

---

##  API Endpoints

### Provinces
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/provinces/save | Save a new province |
| GET | /api/provinces/all | Get all provinces |
| GET | /api/provinces/{id} | Get province by ID |

### Locations
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/locations/save | Save a new location |
| GET | /api/locations/all | Get all locations |
| GET | /api/locations/{id} | Get location by ID |

### Bus Stops
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/bus-stops/save | Save a new bus stop |
| GET | /api/bus-stops/all | Get all bus stops |
| GET | /api/bus-stops/{id} | Get bus stop by ID |

### Routes
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/routes/save | Save a new route |
| GET | /api/routes/all?page=0&size=10&sortBy=routeNumber | Get routes with pagination and sorting |

### Users
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/users/save | Register a new user |
| GET | /api/users/province/code/{code} | Get users by province code |
| GET | /api/users/province/name/{name} | Get users by province name |

---

##  Key Features Implemented

- ✅ **One-to-One** relationship between BusStop and Location
- ✅ **One-to-Many** relationship between Province and Location
- ✅ **Many-to-Many** relationship between BusStop and Route
- ✅ **Pagination and Sorting** on Routes endpoint
- ✅ **existsBy()** checks to prevent duplicate data
- ✅ **Query by province** code or name

---

##  Author

*SHEMA RYAN*
*ID:26138*
Adventist University of Central Africa (AUCA)
