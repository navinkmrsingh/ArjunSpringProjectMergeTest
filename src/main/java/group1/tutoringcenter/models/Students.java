package group1.tutoringcenter.models;



public class Students {
    private int id;
    private String name;
    private String email;


    private String enrolledCourse;

    public Students() {
    }


    public Students(String name, String enrolledCourse) {
        this.name = name;
        this.enrolledCourse = enrolledCourse;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEnrolledCourse() { return enrolledCourse; }
    public void setEnrolledCourse(String enrolledCourse) { this.enrolledCourse = enrolledCourse; }
}