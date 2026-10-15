package library;

	public class StudentMember extends Member {
	    private String semester;
	    private String department;
	    private int borrowLimit;
	    private double fineRate;

	    public StudentMember(String memberId, String name, String email, String phone,
	                         String semester, String department, int borrowLimit, double fineRate) {
	        super(memberId, name, email, phone);
	        this.semester = semester;
	        this.department = department;
	        this.borrowLimit = borrowLimit;
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
	        return "Student";
	    }

	    public String getSemester() { return semester; }
	    public String getDepartment() { return department; }
	    public int getStudentBorrowLimit() { return borrowLimit; }
	    public double getFineRate() { return fineRate; }

	    public void setSemester(String semester) { this.semester = semester; }
	    public void setDepartment(String department) { this.department = department; }
	    public void setBorrowLimit(int borrowLimit) { this.borrowLimit = Math.max(1, borrowLimit); }
	    public void setFineRate(double fineRate) { this.fineRate = Math.max(0, fineRate); }

	    public void displayStudentDetails() {
	        displayDetails();
	        System.out.println("Type: Student | Semester: " + semester
	                + " | Department: " + department + " | Fine/day: Rs." + fineRate);
	    }
	}
