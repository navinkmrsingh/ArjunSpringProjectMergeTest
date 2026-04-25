package group1.tutoringcenter.services;
import group1.tutoringcenter.models.Student;
import group1.tutoringcenter.repositories.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }
    public List<Student> getStudents() {
        return repo.findAll();
    }
    public Optional<Student> getStudent(Long id) {
        return repo.findById(id);
    }
    public List<Student> searchStudents(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }
    public Student addStudent(Student student) {
        return repo.save(student);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        updatedStudent.setId(id);
        return repo.save(updatedStudent);
    }
    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }
}