package assignmentII;

public class BookIssue {
    private String studentName;
    private String bookIssued;
    private String issuedDate;
    private String dueDate;

    // Constructor
    public BookIssue(String studentName, String bookIssued, String issuedDate, String dueDate) {
        this.studentName = studentName;
        this.bookIssued = bookIssued;
        this.issuedDate = issuedDate;
        this.dueDate = dueDate;
    }

    // Getters and Setters
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getBookIssued() {
        return bookIssued;
    }

    public void setBookIssued(String bookIssued) {
        this.bookIssued = bookIssued;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
