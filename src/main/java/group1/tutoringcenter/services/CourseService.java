// Husna Syeda
// UID: 393005210
// Service: CourseService
// ISTE 240 - Group 1

package group1.tutoringcenter.services;

import group1.tutoringcenter.models.Course;
import group1.tutoringcenter.repositories.CourseRepository;
import group1.tutoringcenter.repositories.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import  java.util.Optional;


@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TutorRepository tutorRepository;

    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(int id) {
        return courseRepository.findById(id);
    }

    public List<Course> searchByName(String name) {
        return courseRepository.findByCourseName(name);
    }

    public List<Course> getCoursesByDepartment(String department) {
        return courseRepository.findByDepartment(department);
    }

    public List<Course> getCoursesByTutor(int tutorId) {
        return courseRepository.findByTutorId(tutorId);
    }


    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> updateCourse(int id, Course updatedCourse) {
        return courseRepository.findById(id).map(existing -> {
            existing.setCourseName(updatedCourse.getCourseName());
            existing.setCourseCode(updatedCourse.getCourseCode());
            existing.setDescription(updatedCourse.getDescription());
            existing.setCredits(updatedCourse.getCredits());
            existing.setDepartment(updatedCourse.getDepartment());
            if (updatedCourse.getTutor() != null) {
                existing.setTutor(updatedCourse.getTutor());
            }
            return courseRepository.save(existing);
        });
    }


    public boolean deleteCourse(int id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }
}