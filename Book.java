
class Book {
    private int id; 
    private String title; 
    private String author; 
    private double price; 
    private boolean isAvailable; 

    public Book(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.isAvailable = true; 
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Issued";
        return String.format("ID: %-4d | Title: %-30s | Author: %-20s | Price: $%-8.2f | Status: %s",
                id, title, author, price, status);
    }
}