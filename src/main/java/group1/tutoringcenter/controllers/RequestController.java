package group1.tutoringcenter.controllers;

import group1.tutoringcenter.TutoringCenterService;
import group1.tutoringcenter.models.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RequestController {

    private final TutoringCenterService service;

    public RequestController(TutoringCenterService service) {
        this.service = service;
    }

    @GetMapping("/request")
    public String showRequestsPage(Model model) {
        model.addAttribute("requests", service.getRequests());
        return "request";
    }

    @GetMapping("/request/add")
    public String showAddRequestPage() {
        return "request_add";
    }

    @PostMapping("/request/add")
    public String addRequest(@RequestParam String studentName,
                             @RequestParam String status) {

        int newId = service.getRequests().size() + 1;

        Request request = new Request();
        request.setId(newId);
        request.setStudentName(studentName);
        request.setStatus(status);

        service.addRequest(request);

        return "redirect:/success";
    }
}