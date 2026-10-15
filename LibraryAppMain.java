package library;
import java.util.List;
import java.util.Scanner;
public class LibraryAppMain {
	    private Scanner scanner;
	    private LibraryService library;
	    private boolean running;
	    private int menuChoice;

	    public LibraryAppMain() {
	        scanner = new Scanner(System.in);
	        library = new LibraryService();
	        running = true;
	        menuChoice = 0;
	        addSampleData();
	    }

	    public static void main(String[] args) {
	        LibraryAppMain app = new LibraryAppMain();
	        app.start();
	    }

	    public void start() {
	        while (running) {
	            showMenu();
	            System.out.print("Enter your choice: ");
	            if (!scanner.hasNextInt()) {
	                System.out.println("Please enter a number.");
	                scanner.nextLine();
	                continue;
	            }
	            menuChoice = scanner.nextInt();
	            scanner.nextLine();

	            switch (menuChoice) {
	                case 1: library.listBooks(); break;
	                case 2: library.listMembers(); break;
	                case 3: handleIssue(); break;
	                case 4: handleReturn(); break;
	                case 5: handleSearch(); break;
	                case 6: library.listBorrowedBooks(); break;
	                case 7: library.printDailyReport(); break;
	                case 8:
	                    running = false;
	                    System.out.println("Thank you for using the Library System.");
	                    break;
	                default:
	                    System.out.println("Invalid choice. Please select 1-8.");
	            }
	        }
	        scanner.close();
	    }

	    public void showMenu() {
	        System.out.println("\\n========== LIBRARY MANAGEMENT SYSTEM ==========");
	        System.out.println("1. List all books");
	        System.out.println("2. List all members");
	        System.out.println("3. Issue a book");
	        System.out.println("4. Return a book and calculate fine");
	        System.out.println("5. Search books");
	        System.out.println("6. List borrowed books");
	        System.out.println("7. Print daily issue/return report");
	        System.out.println("8. Exit");
	    }

	    public void addSampleData() {
	        library.addBook(new Book("B101", "9780135166307", "Java Basics", "Herbert Schildt", "Programming"));
	        library.addBook(new Book("B102", "9780596009205", "Head First Java", "Kathy Sierra", "Programming"));
	        library.addBook(new Book("B103", "9780132350884", "Clean Code", "Robert C. Martin", "Software"));
	        library.addBook(new Book("B104", "9780262033848", "Introduction to Algorithms", "Thomas H. Cormen", "Algorithms"));

	        library.addMember(new StudentMember("S101", "Anu", "anu@example.com", "9876543210",
	                "III", "CSE", 3, 5.0));
	        library.addMember(new FacultyMember("F101", "Dr. Kumar", "kumar@example.com", "9876501234",
	                "Assistant Professor", 5, 1, 2.0));
	    }

	    public void handleIssue() {
	        System.out.print("Enter member ID (e.g., S101): ");
	        String memberId = scanner.nextLine().trim();
	        System.out.print("Enter book ID or ISBN: ");
	        String bookId = scanner.nextLine().trim();
	        library.issueBook(memberId, bookId);
	    }

	    public void handleReturn() {
	        System.out.print("Enter book ID or ISBN to return: ");
	        String bookId = scanner.nextLine().trim();
	        System.out.print("Enter overdue days (0 if returned on time): ");
	        int overdueDays;
	        if (scanner.hasNextInt()) {
	            overdueDays = scanner.nextInt();
	            scanner.nextLine();
	            if (overdueDays < 0) overdueDays = 0;
	            library.returnBook(bookId, overdueDays);
	        } else {
	            System.out.println("Please enter the number of overdue days.");
	            scanner.nextLine();
	        }
	    }

	    public void handleSearch() {
	        System.out.println("Search by: 1. All fields  2. Title  3. Author  4. ISBN/Book ID");
	        System.out.print("Choose search type: ");
	        if (!scanner.hasNextInt()) {
	            System.out.println("Invalid choice.");
	            scanner.nextLine();
	            return;
	        }
	        int type = scanner.nextInt();
	        scanner.nextLine();
	        System.out.print("Enter search text: ");
	        String keyword = scanner.nextLine().trim();

	        List<Book> results;
	        if (type == 1) results = library.search(keyword);
	        else if (type == 2) results = library.search(keyword, "title");
	        else if (type == 3) results = library.search(keyword, "author");
	        else if (type == 4) results = library.search(keyword, "isbn");
	        else {
	            System.out.println("Invalid search type.");
	            return;
	        }

	        if (results.isEmpty()) {
	            System.out.println("No matching books found.");
	        } else {
	            System.out.println("Search results:");
	            for (Book book : results) book.displayDetails();
	        }
	    }
}
	


