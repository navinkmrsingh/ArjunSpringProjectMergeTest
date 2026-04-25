package group1.tutoringcenter.controllers;

import group1.tutoringcenter.models.Student;
import group1.tutoringcenter.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    private final StudentService service;

    public StudentRestController(StudentService service) {
        this.service = service;
    }

    @GetMapping
    public List<Student> getStudents() {
        return service.getStudents();
    }

    @GetMapping("/{id}")
    public Optional<Student> getStudent(@PathVariable Long id) {
        return service.getStudent(id);
    }

    @GetMapping("/search")
    public List<Student> searchStudents(@RequestParam String name) {
        return service.searchStudents(name);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return service.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
    }
}

