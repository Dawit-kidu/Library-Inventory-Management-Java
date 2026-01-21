import java.util.Scanner;

public class LibraryInventorySystem {

    private Book[] inventory;
    private int bookCount;
    private static int nextId = 101;
    private final int MAX_CAPACITY = 100;
    private Scanner scanner;

    public LibraryInventorySystem() {
        inventory = new Book[MAX_CAPACITY]; 
        bookCount = 0;
        scanner = new Scanner(System.in);
    }

    public void runMenu() {
        int choice;
        do {
            System.out.println("\n===== Library Inventory Menu =====");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Search Book by Author");
            System.out.println("5. Issue Book");
            System.out.println("6. Return Book");
            System.out.println("7. Remove Book");
            System.out.println("8. Sort by Price (Ascending)");
            System.out.println("9. Sort by Title (A-Z)");
            System.out.println("10. Show Issued Books");
            System.out.println("11. Show Available Books");
            System.out.println("12. Show Books by Price Range");
            System.out.println("13. Show Total Inventory Value");
            System.out.println("14. Show Most Expensive and Cheapest Book");
            System.out.println("15. Count of Books per Author");
            System.out.println("16. Exit");
            System.out.print("Enter choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter a number (1-16): ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1: addBooks(); break;
                case 2: displayAllBooks(); break;
                case 3: searchByTitle(); break;
                case 4: searchByAuthor(); break;
                case 5: issueBook(); break;
                case 6: returnBook(); break;
                case 7: removeBook(); break;
                case 8: sortByPrice(); break;
                case 9: sortByTitle(); break;
                case 10: showIssuedBooks(); break;
                case 11: showAvailableBooks(); break;
                case 12: showBooksByPriceRange(); break;
                case 13: showTotalInventoryValue(); break;
                case 14: showMostExpensiveAndCheapest(); break;
                case 15: countBooksPerAuthor(); break;
                case 16: System.out.println("Exiting system."); break;
                default: System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 16);
    }

    private void addBooks() {
        System.out.print("How many books do you want to add? "); 
        int count = scanner.nextInt();
        scanner.nextLine(); 

        if (bookCount + count > MAX_CAPACITY) {
            System.out.println("Error: Not enough space in inventory array.");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println("\nEntering details for book " + (i + 1) + ":");
            
            String title;
            
            do {
                System.out.print("Enter Title: ");
                title = scanner.nextLine().trim();
                if (title.isEmpty()) System.out.println("Error: Title cannot be empty.");
            } while (title.isEmpty());

            String author;
            do {
                System.out.print("Enter Author: ");
                author = scanner.nextLine().trim();
                if (author.isEmpty()) System.out.println("Error: Author cannot be empty.");
            } while (author.isEmpty());

            double price = -1;
            do {
                System.out.print("Enter Price: ");
                if (scanner.hasNextDouble()) {
                    price = scanner.nextDouble();
                    if (price < 0) System.out.println("Error: Price cannot be negative.");
                } else {
                    System.out.println("Error: Invalid price input.");
                    scanner.next(); 
                }
            } while (price < 0);
            scanner.nextLine(); 

            int bookId = nextId++; 
            inventory[bookCount] = new Book(bookId, title, author, price);
            bookCount++;
            System.out.println("Book added successfully with ID: " + bookId);
        }
    }

    
    private void displayAllBooks() {
        if (bookCount == 0) {
            System.out.println("No books in inventory.");
            return;
        }
        System.out.println("\n--- All Books ---");
        for (int i = 0; i < bookCount; i++) {
            System.out.println(inventory[i].toString());
        }
    }

    private void searchByTitle() {
        System.out.print("Enter title to search (partial match allowed): "); 
        String query = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (inventory[i].getTitle().toLowerCase().contains(query)) {
                System.out.println(inventory[i]);
                found = true;
            }
        }
        if (!found) System.out.println("No books found matching that title.");
    }

    private void searchByAuthor() {
        System.out.print("Enter author to search (partial match allowed): "); 
        String query = scanner.nextLine().toLowerCase();
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (inventory[i].getAuthor().toLowerCase().contains(query)) {
                System.out.println(inventory[i]);
                found = true;
            }
        }
        if (!found) System.out.println("No books found matching that author.");
    }

   
    private int findIndexById(int id) {
        for (int i = 0; i < bookCount; i++) {
            if (inventory[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }

    
    private void issueBook() {
        System.out.print("Enter Book ID to issue: "); 
        int id = scanner.nextInt();
        scanner.nextLine();
        int index = findIndexById(id);

        if (index != -1) {
            if (inventory[index].isAvailable()) {
                inventory[index].setAvailable(false); 
                System.out.println("Book issued successfully.");
            } else {
                System.out.println("Error: Book is already issued.");
            }
        } else {
            System.out.println("Error: Book ID not found.");
        }
    }

    private void returnBook() {
        System.out.print("Enter Book ID to return: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        int index = findIndexById(id);

        if (index != -1) {
            if (!inventory[index].isAvailable()) {
                inventory[index].setAvailable(true); 
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Error: Book was not issued.");
            }
        } else {
            System.out.println("Error: Book ID not found.");
        }
    }

    private void removeBook() {
        System.out.print("Enter Book ID to remove: "); 
        int id = scanner.nextInt();
        scanner.nextLine();
        int index = findIndexById(id);

        if (index != -1) {
            System.out.println("Removing: " + inventory[index].getTitle());
            
            for (int i = index; i < bookCount - 1; i++) {
                inventory[i] = inventory[i + 1];
            }
            inventory[bookCount - 1] = null; 
            bookCount--;
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Error: Book ID not found.");
        }
    }

    private void sortByPrice() {
        for (int i = 0; i < bookCount - 1; i++) {
            for (int j = 0; j < bookCount - i - 1; j++) {
                if (inventory[j].getPrice() > inventory[j + 1].getPrice()) {
                    // Swap temp and inventory[i]
                    Book temp = inventory[j];
                    inventory[j] = inventory[j + 1];
                    inventory[j + 1] = temp;
                }
            }
        }
        System.out.println("Inventory sorted by price (ascending).");
        displayAllBooks();
    }

    private void sortByTitle() {
        for (int i = 0; i < bookCount - 1; i++) {
            for (int j = 0; j < bookCount - i - 1; j++) {
                if (inventory[j].getTitle().compareToIgnoreCase(inventory[j + 1].getTitle()) > 0) {
                    Book temp = inventory[j];
                    inventory[j] = inventory[j + 1];
                    inventory[j + 1] = temp;
                }
            }
        }
        System.out.println("Inventory sorted by title (A-Z).");
        displayAllBooks();
    }

    
    private void showIssuedBooks() {
        System.out.println("\n--- Issued Books ---");
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (!inventory[i].isAvailable()) {
                System.out.println(inventory[i]);
                found = true;
            }
        }
        if (!found) System.out.println("No books are currently issued.");
    }

    private void showAvailableBooks() {
        System.out.println("\n--- Available Books ---");
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (inventory[i].isAvailable()) {
                System.out.println(inventory[i]);
                found = true;
            }
        }
        if (!found) System.out.println("No books available.");
    }

    private void showBooksByPriceRange() {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("\n--- Books between $" + min + " and $" + max + " ---");
        boolean found = false;
        for (int i = 0; i < bookCount; i++) {
            if (inventory[i].getPrice() >= min && inventory[i].getPrice() <= max) {
                System.out.println(inventory[i]);
                found = true;
            }
        }
        if (!found) System.out.println("No books found in this price range.");
    }

    private void showTotalInventoryValue() {
        double total = 0;
        for (int i = 0; i < bookCount; i++) {
            total += inventory[i].getPrice();
        }
        System.out.printf("\nTotal Inventory Value: $%.2f%n", total);
    }

    private void showMostExpensiveAndCheapest() {
        if (bookCount == 0) {
            System.out.println("Inventory is empty.");
            return;
        }
        Book expensive = inventory[0];
        Book cheapest = inventory[0];

        for (int i = 1; i < bookCount; i++) {
            if (inventory[i].getPrice() > expensive.getPrice()) {
                expensive = inventory[i];
            }
            if (inventory[i].getPrice() < cheapest.getPrice()) {
                cheapest = inventory[i];
            }
        }
        System.out.println("\n--- Extremes ---");
        System.out.println("Most Expensive Book: " + expensive.getTitle() + " ($" + expensive.getPrice() + ")");
        System.out.println("Cheapest Book:       " + cheapest.getTitle() + " ($" + cheapest.getPrice() + ")");
    }

    private void countBooksPerAuthor() {
        if (bookCount == 0) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- Book Count per Author ---");
        String[] uniqueAuthors = new String[bookCount];
        int[] authorCounts = new int[bookCount];
        int uniqueCount = 0;

        for (int i = 0; i < bookCount; i++) {
            String currentAuthor = inventory[i].getAuthor();
            boolean exists = false;
            for (int j = 0; j < uniqueCount; j++) {
                if (uniqueAuthors[j].equalsIgnoreCase(currentAuthor)) {
                    authorCounts[j]++;
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                uniqueAuthors[uniqueCount] = currentAuthor;
                authorCounts[uniqueCount] = 1;
                uniqueCount++;
            }
        }

        for (int i = 0; i < uniqueCount; i++) {
            System.out.println(uniqueAuthors[i] + ": " + authorCounts[i] + " book(s)");
        }
    }

    public static void main(String[] args) {
        LibraryInventorySystem system = new LibraryInventorySystem();
        system.runMenu();
    }
}