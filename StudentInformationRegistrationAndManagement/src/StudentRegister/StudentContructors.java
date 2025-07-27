package StudentRegister;

public class StudentContructors {
    String studentId, firstname, lastname, year, course, section;

    public StudentContructors(String studentId, String firstname, String lastname, String year, String course, String section) {
        this.studentId = studentId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.year = year;
        this.course = course;
        this.section = section;
    }
    public String getStudentId(){
        return studentId;
    }
    public String getFirstname(){
        return firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public String getYear(){
        return year;
    }
    public String getCourse(){
        return course;
    }
    public String getSection(){
        return section;
    }
}
