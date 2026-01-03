GraphQL API for a **Books Library** with Authors and Categories.  
This README documents all functionalities of the API with example queries and screenshots.

---

##  1. List Books (Paginated + Filters + Recursive)

**Description:**  
Fetch books with optional pagination, filters, and recursive category search. Default page size is 10.  

### a) Default list of books
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 5 08 34 PM" src="https://github.com/user-attachments/assets/61c67a45-d99b-492a-a5bf-06c3ee113830" />
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 5 08 43 PM" src="https://github.com/user-attachments/assets/2a2671c2-1a31-4e26-834a-d43b0e804e1a" />

### b) Custom page & size
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 5 13 01 PM" src="https://github.com/user-attachments/assets/c0dbc340-3e6f-4e7d-a30a-dfd3e0c7edd3" />
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 5 13 09 PM" src="https://github.com/user-attachments/assets/052b133b-d0bc-4847-a95d-39310eed11fb" />

### c) Filter by publication year
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 5 14 15 PM" src="https://github.com/user-attachments/assets/ec86d360-9b3b-475c-8d5f-1d998c95d0e5" />
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 5 14 20 PM" src="https://github.com/user-attachments/assets/75219e08-c320-4cb9-a977-3b95626dafb2" />

### d) Filter by language
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 5 16 13 PM" src="https://github.com/user-attachments/assets/15b714fd-7914-4f59-8d6d-4a04359eee32" />


### e) Filter by category (non-recursive)
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 6 13 20 PM" src="https://github.com/user-attachments/assets/97724cb8-295c-4151-ba65-2310baa17f34" />


### f) Filter by category (recursive)
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 6 13 11 PM" src="https://github.com/user-attachments/assets/e894c8b8-02b2-40c3-bfe6-9d301ab1b22a" />



## 2. Books by Author (ID or Name)

**Description:**
Fetch all books by a specific author using ID or Name.
### a) By Author ID
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 5 26 54 PM" src="https://github.com/user-attachments/assets/a004d764-c2dc-44e1-9d2e-0bd8293cb4ce" />

### b) By Author Name
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 5 27 35 PM" src="https://github.com/user-attachments/assets/b778dc0e-86d8-4570-bbcf-ac8b2064d619" />

## 3. Search by Keyword

**Description:**
Search in books, authors, or categories using a keyword. Supports pagination and type filtering.
### a) Search all types (default)
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 5 35 56 PM" src="https://github.com/user-attachments/assets/ddd1df6d-2f7b-4446-875e-583b72903d01" />

<img width="1340" height="696" alt="Screenshot 2026-01-03 at 5 35 05 PM" src="https://github.com/user-attachments/assets/60a95b80-f0f8-4c56-94ad-40b929739e1d" />

### b) Search only Books
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 5 39 00 PM" src="https://github.com/user-attachments/assets/0f16c1bc-8785-4f8a-9fa7-30140d52edf2" />

### c) Search only Authors

<img width="1340" height="696" alt="Screenshot 2026-01-03 at 6 01 16 PM" src="https://github.com/user-attachments/assets/0f8282f8-d275-41e6-ae9e-66bfe3814be2" />

### d) Search only Categories
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 6 02 55 PM" src="https://github.com/user-attachments/assets/abeeb9a6-35e9-4cd0-8ca9-10f2851d2864" />

## 4. Add a Book (Admin)
**Description:**
Add a new book to the database. Requires admin authentication. Checks for duplicate title by the same author.

### a) Attempt by non-admin
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 6 04 41 PM" src="https://github.com/user-attachments/assets/592fb65f-3456-417f-ac3d-fe511aacc6e6" />

### b) admin
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 6 07 55 PM" src="https://github.com/user-attachments/assets/01b48ca7-7243-4a58-897c-cf87e6ed9fbe" />

### c) adding a duplicate book
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 6 08 44 PM" src="https://github.com/user-attachments/assets/573efdef-e1f2-41f3-a447-4266afa3e779" />

## 5) Delete an Author (Admin)
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 6 10 04 PM" src="https://github.com/user-attachments/assets/b24d6a07-6be6-495a-85b4-804e64f36fcb" />
<img width="1340" height="696" alt="Screenshot 2026-01-03 at 6 11 15 PM" src="https://github.com/user-attachments/assets/22c41bd6-9e72-42b0-9bb5-1a6f4e5f8d00" />








