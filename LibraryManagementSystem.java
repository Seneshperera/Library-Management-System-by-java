import java.util.ArrayList;
import java.util.Scanner;

class LibraryManagementSystem {
    static class Book {
        String title;
        String author;
        String isbn;
        String status;
        String time;

        Book(String title, String author, String isbn, String status, String time) {
            this.title = title;
            this.author = author;
            this.isbn = isbn;
            this.status = status;
            this.time = time;
        }
    }

    static ArrayList<Book> bookList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Adding an initial book to the library
        bookList.add(new Book("Sherlock Holmes", "Sir Arthur Conan Doyle", "001", "available", null));

        while (true) {
            System.out.println("\n------------------- Library Management System -------------------");
            System.out.println("1. Add new book");
            System.out.println("2. Borrow book");
            System.out.println("3. View available books");
            System.out.println("4. View borrowed books");
            System.out.println("5. Return book");
            System.out.println("6. Quit the system");
            System.out.println("-----------------------------------------------------------------");

            System.out.print("Enter your command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    addBook(scanner);
                    break;
                case "2":
                    borrowBook(scanner);
                    break;
                case "3":
                    viewAvailableBooks();
                    break;
                case "4":
                    viewBorrowedBooks();
                    break;
                case "5":
                    returnBook(scanner);
                    break;
                case "6":
                    System.out.println("Leaving the system. Have a great day!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid command! Please try again.");
            }
        }
    }

    public static void addBook(Scanner scanner) {
        System.out.print("Enter book name: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        bookList.add(new Book(title, author, isbn, "available", null));
        System.out.println("\"" + title + "\" has been added to the library.");
    }

    public static void borrowBook(Scanner scanner) {
        System.out.print("Enter book ISBN No: ");
        String isbn = scanner.nextLine();

        for (Book book : bookList) {
            if (book.isbn.equals(isbn)) {
                if (book.status.equals("available")) {
                    book.status = "borrowed";
                    book.time = java.time.LocalDateTime.now().toString();
                    System.out.println("'" + book.title + "' has been successfully borrowed. Enjoy your reading!");
                    return;
                }
                System.out.println("The book is already borrowed.");
                return;
            }
        }
        System.out.println("Book not found.");
    }

    public static void viewAvailableBooks() {
        System.out.println("\nAvailable Books:");
        boolean found = false;
        for (Book book : bookList) {
            if (book.status.equals("available")) {
                found = true;
                System.out.println("Title  - " + book.title);
                System.out.println("Author - " + book.author);
                System.out.println("ISBN   - " + book.isbn);
                System.out.println("----------------------------------------------------");
            }
        }
        if (!found) {
            System.out.println("No books available.");
        }
    }

    public static void viewBorrowedBooks() {
        System.out.println("\nBorrowed Books:");
        boolean found = false;
        for (Book book : bookList) {
            if (book.status.equals("borrowed")) {
                found = true;
                System.out.println("Title  - " + book.title);
                System.out.println("Author - " + book.author);
                System.out.println("ISBN   - " + book.isbn);
                System.out.println("Borrowed at: " + book.time);
                System.out.println("----------------------------------------------------");
            }
        }
        if (!found) {
            System.out.println("No books are currently borrowed.");
        }
    }

    public static void returnBook(Scanner scanner) {
        System.out.print("Enter book ISBN No: ");
        String isbn = scanner.nextLine();

        for (Book book : bookList) {
            if (book.isbn.equals(isbn)) {
                if (book.status.equals("borrowed")) {
                    book.status = "available";
                    book.time = java.time.LocalDateTime.now().toString();
                    System.out.println("'" + book.title + "' has been successfully returned. Thank you!");
                    return;
                }
                System.out.println("This book is not borrowed.");
                return;
            }
        }
        System.out.println("Book not found.");
    }
}

