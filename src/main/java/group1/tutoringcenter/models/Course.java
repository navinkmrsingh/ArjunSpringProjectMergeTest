// Husna Syeda
// UID: 393005210
// Entity: Course
// ISTE 240 - Group 1

package group1.tutoringcenter.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int courseId;

    @Column(name = "course_name",nullable=false,length=100)
    private String courseName;

    @Column(name = "course_code",nullable=false,unique=true,length=20)
    private String courseCode;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "credits", nullable = false)
    private int credits;

    @Column(name = "department", length = 50)
    private String department;

    //Relationship with Tutor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tutor_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"courses_taught","password","hibernateLazyInitializer"})
    private Tutor tutor;

    public Course() {}

    public Course(String courseName, String courseCode, String description, int credits, String department, Tutor tutor) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.description = description;
        this.credits = credits;
        this.department = department;
        this.tutor = tutor;
    }

    public int getCourseId() { return courseId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public Tutor getTutor() { return tutor; }
    public void setTutor(Tutor tutor) { this.tutor = tutor; }

    public String getTutorName() { return tutor != null ? tutor.getName() : null; }
}