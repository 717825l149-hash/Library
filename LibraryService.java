package library;

	import java.time.LocalDate;
	import java.util.ArrayList;
	import java.util.LinkedHashMap;
	import java.util.List;
	import java.util.Map;

	public class LibraryService {
	    private List<Book> books;
	    private List<Member> members;
	    private Map<String, BorrowRecord> borrowedBooks;
	    private List<String> dailyReport;
	    private double totalFines;

	    public LibraryService() {
	        books = new ArrayList<>();
	        members = new ArrayList<>();
	        borrowedBooks = new LinkedHashMap<>();
	        dailyReport = new ArrayList<>();
	        totalFines = 0.0;
	    }

	    public void addBook(Book book) {
	        if (findBook(book.getBookId()) == null) {
	            books.add(book);
	            dailyReport.add(LocalDate.now() + " - Added book: " + book.getTitle());
	        } else {
	            System.out.println("A book with this ID already exists.");
	        }
	    }

	    public void addMember(Member member) {
	        if (findMember(member.getMemberId()) == null) {
	            members.add(member);
	            dailyReport.add(LocalDate.now() + " - Added member: " + member.getName());
	        } else {
	            System.out.println("A member with this ID already exists.");
	        }
	    }

	    public boolean issueBook(String memberId, String bookId) {
	        Member member = findMember(memberId);
	        Book book = findBook(bookId);

	        if (member == null) {
	            System.out.println("Member not found.");
	            return false;
	        }
	        if (book == null) {
	            System.out.println("Book not found.");
	            return false;
	        }
	        if (!book.isAvailable()) {
	            System.out.println("Book is already borrowed.");
	            return false;
	        }
	        if (!member.borrowBook()) {
	            System.out.println("Borrowing limit reached for " + member.getName() + ".");
	            return false;
	        }

	        book.issue();
	        borrowedBooks.put(bookId, new BorrowRecord(member, book, LocalDate.now()));
	        dailyReport.add(LocalDate.now() + " - Issued " + book.getTitle()
	                + " to " + member.getName());
	        System.out.println("Book issued successfully. Return it within 14 days.");
	        return true;
	    }

	    public double returnBook(String bookId, int overdueDays) {
	        BorrowRecord record = borrowedBooks.remove(bookId);
	        if (record == null) {
	            System.out.println("This book is not currently recorded as borrowed.");
	            return 0.0;
	        }

	        record.book.returnBook();
	        record.member.returnBook();
	        double fine = record.member.calcFine(overdueDays);
	        totalFines += fine;
	        dailyReport.add(LocalDate.now() + " - Returned " + record.book.getTitle()
	                + " by " + record.member.getName() + " | Fine: Rs." + fine);
	        System.out.printf("Book returned successfully. Fine: Rs.%.2f%n", fine);
	        return fine;
	    }

	    // Overloaded search method: search all fields.
	    public List<Book> search(String keyword) {
	        List<Book> result = new ArrayList<>();
	        for (Book book : books) {
	            if (book.matchesTitle(keyword) || book.matchesAuthor(keyword)
	                    || book.matchesIsbn(keyword)) {
	                result.add(book);
	            }
	        }
	        return result;
	    }

	    // Overloaded search method: search a selected field (title, author, or isbn).
	    public List<Book> search(String keyword, String searchBy) {
	        List<Book> result = new ArrayList<>();
	        for (Book book : books) {
	            boolean match = false;
	            if ("title".equalsIgnoreCase(searchBy)) {
	                match = book.matchesTitle(keyword);
	            } else if ("author".equalsIgnoreCase(searchBy)) {
	                match = book.matchesAuthor(keyword);
	            } else if ("isbn".equalsIgnoreCase(searchBy) || "id".equalsIgnoreCase(searchBy)) {
	                match = book.matchesIsbn(keyword);
	            }
	            if (match) result.add(book);
	        }
	        return result;
	    }

	    public void listBooks() {
	        if (books.isEmpty()) {
	            System.out.println("No books in the library.");
	            return;
	        }
	        for (Book book : books) book.displayDetails();
	    }

	    public void listMembers() {
	        if (members.isEmpty()) {
	            System.out.println("No members registered.");
	            return;
	        }
	        for (Member member : members) {
	            System.out.println(member.getMemberType() + " | " + member.getMemberId()
	                    + " | " + member.getName());
	        }
	    }

	    public void listBorrowedBooks() {
	        if (borrowedBooks.isEmpty()) {
	            System.out.println("No books are currently borrowed.");
	            return;
	        }
	        for (BorrowRecord record : borrowedBooks.values()) {
	            System.out.println(record.book.getTitle() + " (ID: " + record.book.getBookId()
	                    + ") borrowed by " + record.member.getName()
	                    + " on " + record.issueDate);
	        }
	    }

	    public void printDailyReport() {
	        System.out.println("\\n===== DAILY ISSUE/RETURN REPORT =====");
	        if (dailyReport.isEmpty()) System.out.println("No activity recorded today.");
	        for (String line : dailyReport) System.out.println(line);
	        System.out.printf("Total fines collected: Rs.%.2f%n", totalFines);
	    }

	    public Member findMember(String memberId) {
	        for (Member member : members) {
	            if (member.getMemberId().equalsIgnoreCase(memberId)) return member;
	        }
	        return null;
	    }

	    public Book findBook(String bookId) {
	        for (Book book : books) {
	            if (book.getBookId().equalsIgnoreCase(bookId)
	                    || book.getIsbn().equalsIgnoreCase(bookId)) return book;
	        }
	        return null;
	    }

	    public int getBookCount() { return books.size(); }
	    public int getMemberCount() { return members.size(); }
	    public int getBorrowedCount() { return borrowedBooks.size(); }
	    public double getTotalFines() { return totalFines; }

	    private static class BorrowRecord {
	        private Member member;
	        private Book book;
	        private LocalDate issueDate;
	        private LocalDate dueDate;

	        private BorrowRecord(Member member, Book book, LocalDate issueDate) {
	            this.member = member;
	            this.book = book;
	            this.issueDate = issueDate;
	            this.dueDate = issueDate.plusDays(14);
	        }
	    }
	}


