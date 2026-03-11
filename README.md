# Bus Stop Finder

A Spring Boot REST API designed to help commuters in Rwanda easily locate 
bus stops and view essential information about routes that serve each stop.

## Project Overview

Bus Stop Finder addresses the problem of inaccessible public transport 
information by providing a centralized platform where users can quickly 
access bus stop details and route information.

### Target Users
- Daily commuters
- Students and workers using public transport
- Visitors unfamiliar with local bus routes

---

## Database Design

### Entity Relationship Diagram

The system uses **4 core tables** plus a join table:

| Relationship | Description | Type |
|---|---|---|
| Location → Location | Province contains Districts, Districts contain Sectors, Sectors contain Cells, Cells contain Villages | Self-referencing One-to-Many |
| Location → BusStop | 1 location, 1 bus stop | One-to-One |
| BusStop ↔ Route | via bus_stop_route join table | Many-to-Many |
| Location → User | User links to their Village | Many-to-One |

### Entities

- **Location** - Self-referencing table representing Rwanda's full 
administrative hierarchy: Province → District → Sector → Cell → Village
- **BusStop** - Named bus stop at a specific location
- **Route** - Bus line with route number, start point and end point
- **User** - Registered commuter linked to their village
- **bus_stop_route** - Join table managing BusStop and Route relationship

### Rwanda Administrative Hierarchy
```
Province (e.g. Kigali)
    └── District (e.g. Nyarugenge)
        └── Sector (e.g. Nyamirambo)
            └── Cell (e.g. Cyivugiza)
                └── Village (e.g. Karisimbi)
                    └── User (e.g. Shema Ryan)
```

When a user is saved with only their Village, the system automatically 
resolves their full geographic chain all the way up to Province.

---

##  Technologies Used

- **Java 21**
- **Spring Boot 3.3.x**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**

---

##  Setup Instructions

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

## 🔌 API Endpoints

### Locations (Province → District → Sector → Cell → Village)
| Method | Endpoint | Description |
|---|---|---|
| POST | /api/locations/save | Save any location level |
| GET | /api/locations/all | Get all locations |
| GET | /api/locations/{id} | Get location by ID |
| GET | /api/locations/type/{type} | Get locations by type (PROVINCE, DISTRICT etc.) |
| GET | /api/locations/children/{parentId} | Get all children of a location |

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
| POST | /api/users/save | Register a new user with village id |
| GET | /api/users/province/{provinceId} | Get all users in a province by id |
| GET | /api/users/province/name/{name} | Get all users in a province by name |

---

##  Key Features Implemented

-  **Self-referencing One-to-Many** — Location table models Rwanda's 
full administrative hierarchy in one elegant table
-  **One-to-One** relationship between BusStop and Location
-  **Many-to-Many** relationship between BusStop and Route 
via bus_stop_route join table
-  **Pagination and Sorting** on Routes endpoint using Pageable
-  **existsBy()** checks to prevent duplicate data before saving
-  **Retrieve users by province** id or name by walking up the 
parent chain from Village to Province

---

## Sample API Usage

### Save a complete Rwanda location chain:
```json
// 1. Save Province
POST /api/locations/save
{ "name": "Kigali", "type": "PROVINCE", "parent": null }

// 2. Save District
POST /api/locations/save
{ "name": "Nyarugenge", "type": "DISTRICT", "parent": { "id": 1 } }

// 3. Save Sector
POST /api/locations/save
{ "name": "Nyamirambo", "type": "SECTOR", "parent": { "id": 2 } }

// 4. Save Cell
POST /api/locations/save
{ "name": "Cyivugiza", "type": "CELL", "parent": { "id": 3 } }

// 5. Save Village
POST /api/locations/save
{ "name": "Karisimbi", "type": "VILLAGE", "parent": { "id": 4 } }

// 6. Register User with Village only
POST /api/users/save
{
    "username": "Shema Ryan",
    "email": "ryanshema7@gmail.com",
    "password": "yourpassword",
    "village": { "id": 5 }
}
```

---

##  Author

**SHEMA RYAN**
**ID: 26138**
Adventist University of Central Africa (AUCA)
Web Technology and Internet — Midterm Project 20
