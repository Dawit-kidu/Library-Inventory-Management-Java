Library Inventory Management System (Java)
📌 Project Overview
A console-based application that acts as a digital bookshelf to manage library stock. This project demonstrates core Java principles and data structure management without the use of an external database.
🛠️ Key Programming Concepts
 * Object-Oriented Programming (OOP): Utilizes a dedicated Book class to represent data as individual objects.
 * Data Structures: Manages an internal array (Book[] inventory) to store and track up to 100 items.
 * Input Validation: Features a robust menu system that prevents crashes by validating user input, ensuring numbers are entered for choices and prices.
 * Algorithms: * Sorting: Implements Bubble Sort to organize books by price or title.
   * Search: Uses linear search with partial-match capability for titles and authors.
   * CRUD Operations: Supports Adding, Reading (Displaying), Updating (Issuing/Returning), and Deleting (Array shifting) records.
📊 Analytical Features
 * Calculates total inventory value.
 * Identifies the most expensive and cheapest items in stock.
 * Tracks unique book counts per author using parallel arrays.
📂 Project Files & Source Code
* [📄 View Source Code: Main System](./LibraryInventorySystem.java)
* [📄 View Source Code: Book Class](./Book.java)
* [📥 Download Technical Documentation (PDF)](./Library%20Inventory%20System%20Documentation.pdf)

🚀 How to Run
 * Ensure you have the Java JDK installed.
 * Download Book.java and LibraryInventorySystem.java into the same folder.
 * Open your terminal and compile: javac LibraryInventorySystem.java
 * Run the application: java LibraryInventorySystem
