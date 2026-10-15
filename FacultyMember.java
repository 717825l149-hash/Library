package library;

	public class FacultyMember extends Member {
	    private String designation;
	    private int borrowLimit;
	    private int priority;
	    private double fineRate;

	    public FacultyMember(String memberId, String name, String email, String phone,
	                         String designation, int borrowLimit, int priority, double fineRate) {
	        super(memberId, name, email, phone);
	        this.designation = designation;
	        this.borrowLimit = borrowLimit;
	        this.priority = priority;
	        this.fineRate = fineRate;
	    }

	    @Override
	    public int getBorrowLimit() {
	        return borrowLimit;
	    }

	    @Override
	    public double calcFine(int overdueDays) {
	        return Math.max(0, overdueDays) * fineRate;
	    }

	    @Override
	    public String getMemberType() {
	        return "Faculty";
	    }

	    public String getDesignation() { return designation; }
	    public int getFacultyBorrowLimit() { return borrowLimit; }
	    public int getPriority() { return priority; }
	    public double getFineRate() { return fineRate; }

	    public void setDesignation(String designation) { this.designation = designation; }
	    public void setBorrowLimit(int borrowLimit) { this.borrowLimit = Math.max(1, borrowLimit); }
	    public void setPriority(int priority) { this.priority = Math.max(1, priority); }
	    public void setFineRate(double fineRate) { this.fineRate = Math.max(0, fineRate); }

	    public void displayFacultyDetails() {
	        displayDetails();
	        System.out.println("Type: Faculty | Designation: " + designation
	                + " | Priority: " + priority + " | Fine/day: Rs." + fineRate);
	    }
	}


