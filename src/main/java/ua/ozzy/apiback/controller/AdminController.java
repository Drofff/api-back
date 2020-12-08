package ua.ozzy.apiback.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ozzy.apiback.dto.MessageDto;
import ua.ozzy.apiback.service.AdminService;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v0/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/default")
    public ResponseEntity<MessageDto> createDefaultAdmin() {
        adminService.createDefaultAdmin();
        return ok(new MessageDto("Request has been accepted"));
    }

}
