package group1.tutoringcenter.services;

import group1.tutoringcenter.models.Admin;
import group1.tutoringcenter.repositories.AdminRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final AdminRepository repo;

    public AdminService(AdminRepository repo) {
        this.repo = repo;
    }
    public List<Admin> getAdmins() {
        return repo.findAll();
    }
    public Optional<Admin> getAdmin(Long id) {
        return repo.findById(id);
    }
    public List<Admin> searchAdmins(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }
    public Admin addAdmin(Admin admin) {
        return repo.save(admin);
    }
    public Admin updateAdmin(Long id, Admin admin) {
        admin.setId(id);
        return repo.save(admin);
    }
    public void deleteAdmin(Long id) {
        repo.deleteById(id);
    }
}