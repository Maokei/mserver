package se.maokei.mserver.api.v1.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping("/admin")
public class AdminController {

    public AdminController() {
    }
}