package group1.tutoringcenter.controllers;

import group1.tutoringcenter.models.Admin;
import group1.tutoringcenter.services.AdminService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
@CrossOrigin
public class AdminRestController {

    private final AdminService service;

    public AdminRestController(AdminService service) {
        this.service = service;
    }

    @GetMapping
    public List<Admin> getAdmins() {
        return service.getAdmins();
    }

    @GetMapping("/{id}")
    public Optional<Admin> getAdmin(@PathVariable Long id) {
        return service.getAdmin(id);
    }

    @GetMapping("/search")
    public List<Admin> searchAdmins(@RequestParam String name) {
        return service.searchAdmins(name);
    }

    @PostMapping
    public Admin addAdmin(@RequestBody Admin admin) {
        return service.addAdmin(admin);
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable Long id, @RequestBody Admin admin) {
        return service.updateAdmin(id, admin);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable Long id) {
        service.deleteAdmin(id);
    }
}