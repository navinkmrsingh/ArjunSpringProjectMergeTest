package group1.tutoringcenter.services;

import group1.tutoringcenter.models.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TutoringCenterService {

    private List<Tutors> tutors = new ArrayList<>();
    private List<Students> students = new ArrayList<>();
    private List<Admins> admins = new ArrayList<>();
    // private List<Course> courses = new ArrayList<>(); // ill add after courses model part is done

    public TutoringCenterService() {

        tutors.add(new Tutors("Ivan Ivanov", "Java Programming"));
        tutors.add(new Tutors("Aimen Jafrani", "Web Development"));

        // test student
        students.add(new Students("Safiia Musaeva", "Web Development"));
    }

    public List<Tutors> getAllTutors() { return tutors; }
    public List<Students> getAllStudents() { return students; }

    public void addTutor(Tutors tutor) { tutors.add(tutor); }
    public void addStudent(Students student) { students.add(student); }

    // public void addCourse(Course course) { courses.add(course); } //ill add after courses model part is done
}