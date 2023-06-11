package com.med.medicalcentrecourse.WebController;

import com.med.medicalcentrecourse.model.Doctor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MenuController {
    @GetMapping("/")
    public String getMenu() {
        return "menu";
    }
}
