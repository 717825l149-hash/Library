package library;

		public class Member {
		    private String memberId;
		    private String name;
		    private String email;
		    private String phone;
		    private int borrowedCount;

		    public Member(String memberId, String name, String email, String phone) {
		        this.memberId = memberId;
		        this.name = name;
		        this.email = email;
		        this.phone = phone;
		        this.borrowedCount = 0;
		    }

		    public boolean borrowBook() {
		        if (borrowedCount < getBorrowLimit()) {
		            borrowedCount++;
		            return true;
		        }
		        return false;
		    }

		    public void returnBook() {
		        if (borrowedCount > 0) {
		            borrowedCount--;
		        }
		    }

		    public double calcFine(int overdueDays) {
		        return Math.max(0, overdueDays) * 3.0;
		    }

		    public int getBorrowLimit() {
		        return 2;
		    }

		    public boolean search(String keyword) {
		        if (keyword == null) return false;
		        String q = keyword.toLowerCase();
		        return memberId.toLowerCase().contains(q)
		                || name.toLowerCase().contains(q)
		                || email.toLowerCase().contains(q);
		    }

		    public String getMemberId() { return memberId; }
		    public String getName() { return name; }
		    public String getEmail() { return email; }
		    public String getPhone() { return phone; }
		    public int getBorrowedCount() { return borrowedCount; }

		    public void setName(String name) { this.name = name; }
		    public void setEmail(String email) { this.email = email; }
		    public void setPhone(String phone) { this.phone = phone; }

		    public void displayDetails() {
		        System.out.println("Member ID: " + memberId + " | Name: " + name
		                + " | Email: " + email + " | Phone: " + phone
		                + " | Borrowed: " + borrowedCount + "/" + getBorrowLimit());
		    }

		    public String getMemberType() {
		        return "General Member";
		    }
		}
		
	


