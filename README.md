**Stack used:**

- Java 8
- Spring Boot
- Embedded H2 Database
- NextJS

**To run backend:**
- `mvn spring-boot:run`
- Server will be running at http://localhost:8080

**To run frontend:**
- `npm install`
- `npm run dev`
- Client will be running at http://localhost:3000


**Credentails:**

Admin:

- email: admin@email.com
- password: admin123

User:

- email: user@email.com
- password: user123


**Workflow**:

- Admin can create an event and add n number of tickets for the event. Admin decides the price of the ticket.

- Event created are listed under Hot Events.

- User can also view Hot Events. User can click on an event to save/reserve/buy m <= n number of tickets for an event (where m is the number of tickets the user wants to buy and n is the total number of tickets available)

- User can save their details for the ticket and edit later.
- User can reserve tickets and pay later. Once reserved, tickets cannot be further edited.
- User can immediately pay for all the tickets when creating the ticket details, or user can pay for each ticket separately while editing information regarding the ticket in case of saved ticket, and also in case of reserved tickets.

