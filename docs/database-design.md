# PG Connect - Database Design & ER Diagram

## Multi-Tenancy Strategy
We are using a **Shared Database, Shared Schema** architecture. Every table that belongs to a specific PG Owner will have a `pg_owner_id` column to isolate data. This approach is highly scalable and cost-effective for a SaaS platform starting out.

## Entities and Relationships

```mermaid
erDiagram
    Users {
        UUID id PK
        String email "UNIQUE"
        String password_hash
        String full_name
        String role "SUPER_ADMIN, PG_OWNER, STAFF, RESIDENT"
        DateTime created_at
        DateTime updated_at
    }

    PgProperties {
        UUID id PK
        UUID pg_owner_id FK
        String name
        String address
        String city
        String state
        String pincode
        DateTime created_at
    }

    Rooms {
        UUID id PK
        UUID pg_property_id FK
        String room_number
        Int capacity
        String room_type "AC, NON_AC"
        Double base_rent
    }

    Beds {
        UUID id PK
        UUID room_id FK
        String bed_number
        Boolean is_occupied
    }

    Residents {
        UUID id PK
        UUID user_id FK "References Users table if resident has app access"
        UUID pg_property_id FK
        UUID bed_id FK "Allocated bed"
        String emergency_contact
        String id_proof_url
        Double agreed_rent
        Date move_in_date
    }

    RentPayments {
        UUID id PK
        UUID resident_id FK
        UUID pg_property_id FK
        Double amount
        Date due_date
        Date paid_date
        String payment_status "PENDING, PAID, OVERDUE"
        String payment_mode "UPI, CASH, BANK_TRANSFER"
        String transaction_reference
    }

    Complaints {
        UUID id PK
        UUID resident_id FK
        UUID pg_property_id FK
        String title
        String description
        String status "OPEN, IN_PROGRESS, RESOLVED"
        String category "ELECTRICAL, PLUMBING, FOOD, OTHER"
        DateTime created_at
    }

    Users ||--o{ PgProperties : "Owns (if PG_OWNER)"
    PgProperties ||--o{ Rooms : "Has"
    Rooms ||--o{ Beds : "Has"
    PgProperties ||--o{ Residents : "Houses"
    Residents |o--|| Beds : "Occupies"
    Residents ||--o{ RentPayments : "Pays"
    Residents ||--o{ Complaints : "Raises"
```

## Explanation
*   **Users:** Stores all user accounts (Admin, Owners, Staff, Residents) for authentication.
*   **PgProperties:** Represents the physical PG buildings. An owner can have multiple.
*   **Rooms & Beds:** Hierarchical mapping of physical space inside a PG.
*   **Residents:** Stores resident-specific information, linking a User to a Bed in a specific PG.
*   **RentPayments:** Tracks the billing cycle for residents.
*   **Complaints:** Ticketing system for maintenance/issues.
