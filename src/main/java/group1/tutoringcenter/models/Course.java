package group1.tutoringcenter.models;

import org.springframework.stereotype.Component;

@Component
public class Course {
    private int courseId;
    private String courseName;
    private String courseCode;
    private String description;
    private int credits;
    private String department;

    // This is the default constructor
    public Course() {
    }

    // Making the constructors with parameters
    public Course(int courseId, String courseName, String courseCode, String description, int credits, String department) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.description = description;
        this.credits = credits;
        this.department = department;
    }

    // The getters and setters for the course class
    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}