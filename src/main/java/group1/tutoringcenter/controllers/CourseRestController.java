// Husna Syeda
// UID: 393005210
// REST Controller: CourseRestController
// ISTE 240 - Group 1


package group1.tutoringcenter.controllers;

import group1.tutoringcenter.models.Course;
import group1.tutoringcenter.models.Tutor;
import group1.tutoringcenter.repositories.TutorRepository;
import group1.tutoringcenter.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/courses")
@CrossOrigin
public class CourseRestController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private TutorRepository tutorRepository;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok(courseService.getCourses());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable int id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourses(@RequestParam String name) {
        return ResponseEntity.ok(courseService.searchByName(name));
    }
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody Map<String, Object> body) {
        try {
            Course course = buildCourseFromBody(body);
            Object tutorIdObj = body.get("tutorId");
            if (tutorIdObj != null) {
                int tutorId = Integer.parseInt(tutorIdObj.toString());
                tutorRepository.findById(tutorId).ifPresent(course::setTutor);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(courseService.addCourse(course));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating course: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable int id,
                                          @RequestBody Map<String, Object> body) {
        try {
            Course updated = buildCourseFromBody(body);
            Object tutorIdObj = body.get("tutorId");
            if (tutorIdObj != null) {
                int tutorId = Integer.parseInt(tutorIdObj.toString());
                tutorRepository.findById(tutorId).ifPresent(updated::setTutor);
            }
            return courseService.updateCourse(id, updated)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error updating course: " + e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable int id) {
        if (courseService.deleteCourse(id)) {
            return ResponseEntity.ok("Course " + id + " deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }

    private Course buildCourseFromBody(Map<String, Object> body) {
        Course course = new Course();
        if (body.get("courseName")  != null) course.setCourseName(body.get("courseName").toString());
        if (body.get("courseCode")  != null) course.setCourseCode(body.get("courseCode").toString());
        if (body.get("description") != null) course.setDescription(body.get("description").toString());
        if (body.get("department")  != null) course.setDepartment(body.get("department").toString());
        if (body.get("credits")     != null) course.setCredits(Integer.parseInt(body.get("credits").toString()));
        return course;
    }

}


