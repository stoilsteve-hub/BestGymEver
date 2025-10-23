#  Best Gym Ever – Membership Checker

A simple **Java console program** for *Best Gym Ever*.

The program helps the receptionist check if a visitor is a **current member**, **former member**, or **unauthorized person**, based on information from a text file.  
If the visitor is a current member, their visit is automatically logged for the personal trainer.

---

##  Files

| File | Description |
|------|--------------|
| `members.txt` | Contains all gym members and their details. |
| `visits.txt` | Stores logs of visits from current members. |

---

## ️ How It Works

1. The program loads all members from `members.txt`.
2. The receptionist types a **name** or **personal number** (`YYMMDD-XXXX`).
3. The program determines:
    -  **Current member** – fee paid within the last year → visit logged.
    -  **Former member** – fee expired → warning message.
    -  **Unauthorized** – person not found → denied access.
4. All valid visits are appended to `visits.txt` automatically.

---

##  How to Run

1. Open the project in **IntelliJ IDEA**.
2. Ensure both `members.txt` and `visits.txt` are located in the **project root** (same level as `src`).
3. Run the program from `MainApp.java`.
4. Enter a **name** or **personal number** when prompted.

---

##  Features

- Uses `BufferedReader` and `BufferedWriter` for safe file handling.
- Validates input (only letters, digits, spaces, and `-` allowed).
- Uses `LocalDate` to calculate active memberships.
- Clean console messages for receptionist.

---

##  Author

Project created by ** Stoil Zhelyazkov **  
Nackademin – Java Programming Course  
October 2025
