package library;
	public class Book {
	    private String bookId;
	    private String isbn;
	    private String title;
	    private String author;
	    private String category;
	    private boolean available;

	    public Book(String bookId, String isbn, String title, String author, String category) {
	        this.bookId = bookId;
	        this.isbn = isbn;
	        this.title = title;
	        this.author = author;
	        this.category = category;
	        this.available = true;
	    }

	    public boolean isAvailable() { return available; }

	    public boolean issue() {
	        if (!available) return false;
	        available = false;
	        return true;
	    }

	    public void returnBook() {
	        available = true;
	    }

	    public boolean matchesTitle(String keyword) {
	        return title.toLowerCase().contains(keyword.toLowerCase());
	    }

	    public boolean matchesAuthor(String keyword) {
	        return author.toLowerCase().contains(keyword.toLowerCase());
	    }

	    public boolean matchesIsbn(String keyword) {
	        return isbn.toLowerCase().contains(keyword.toLowerCase())
	                || bookId.toLowerCase().contains(keyword.toLowerCase());
	    }

	    public void displayDetails() {
	        System.out.println(bookId + " | ISBN: " + isbn + " | " + title
	                + " | Author: " + author + " | Category: " + category
	                + " | " + (available ? "Available" : "Borrowed"));
	    }

	    public String getBookId() { return bookId; }
	    public String getIsbn() { return isbn; }
	    public String getTitle() { return title; }
	    public String getAuthor() { return author; }
	    public String getCategory() { return category; }

	    public void setTitle(String title) { this.title = title; }
	    public void setAuthor(String author) { this.author = author; }
	    public void setCategory(String category) { this.category = category; }
	}

