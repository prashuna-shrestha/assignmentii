package assignmentII;

public class Student {
	private String firstName;
	private String lastName;
	private String gender;
	private String program;
	private String section;
	
	// creating constructor
	public Student(String firstName, String lastName, String gender, String program, String section) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.gender = gender;
	    this.program = program;
	    this.section = section;
	}

	// getters and setters
	 public String getFirstName() {
	        return firstName;
	    }

	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }

	    public String getGender() {
	        return gender;
	    }

	    public void setGender(String gender) {
	        this.gender = gender;
	    }

	    public String getProgram() {
	        return program;
	    }

	    public void setProgram(String program) {
	        this.program = program;
	    }

	    public String getSection() {
	        return section;
	    }

	    public void setSection(String section) {
	        this.section = section;
	    }
	}
