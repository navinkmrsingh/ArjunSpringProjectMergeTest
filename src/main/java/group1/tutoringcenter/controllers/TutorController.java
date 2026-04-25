package group1.tutoringcenter.controllers;

import group1.tutoringcenter.models.Tutor;
import group1.tutoringcenter.services.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TutorController {

    private TutorService tutorService;

    @Autowired
    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    @GetMapping("/tutors")
    public String tutors(Model model) {
        var tutor_list = this.tutorService.findAll();
        model.addAttribute("tutor_list", tutor_list);
        return "tutors";
    }

    @PostMapping("/tutors/add/dummy")
    public String tutors_DUM(Model model) {
        this.tutorService.addTutor(new Tutor("Ivan Ivanov", "demo1@abd.com", 1, "EMPTY"));
        this.tutorService.addTutor(new Tutor("Aimen Jafrani", "demo2@abd.com", 2, "EMPTY"));
        return "redirect:/add/success/Tutor";
    }

    @PostMapping("/tutors")
    public String addTutor(Tutor tutor) {
        this.tutorService.addTutor(tutor);
        return "redirect:/add/success/Tutor";
    }

    @GetMapping("/tutors/add")
    public String addtutor() {
        return "tutors_form";
    }
}
