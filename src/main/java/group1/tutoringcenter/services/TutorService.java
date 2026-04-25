package group1.tutoringcenter.services;

import group1.tutoringcenter.models.Course;
import group1.tutoringcenter.models.Tutor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TutorService {

    private List<Tutor> tutors = new ArrayList<>();

    public TutorService(CourseService courseService) {
        Tutor t1 = new Tutor(1, "Ivan Ivanov", "demo1@abd.com", "password", 1, "EMPTY");
        Tutor t2 = new Tutor(2, "Aimen Jafrani", "demo2@abd.com", "password", 2, "EMPTY");

        for (Course course : courseService.getCourses()) {
            if (course.getCourseId() == 1) {
                t1.assignCourse(course);
            } else if (course.getCourseId() == 2 || course.getCourseId() == 3) {
                t2.assignCourse(course);
            }
        }

        tutors.add(t1);
        tutors.add(t2);
    }

    public List<Tutor> findAll() {
        return tutors;
    }

    public Tutor getTutorById(int id) {
        for (Tutor tutor : tutors) {
            if (tutor.getId() == id) {
                return tutor;
            }
        }
        return null;
    }

    public void addTutor(Tutor tutor) {
        // Assign a unique ID using the current list size + 1
        if (tutor.getId() == 0) {
            tutor.setId(tutors.size() + 1);
        }
        this.tutors.add(tutor);
    }
}