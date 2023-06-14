package com.med.medicalcentrecourse.restControllers;

import com.med.medicalcentrecourse.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class EmailController {
    private final EmailService emailService;
    @PatchMapping("users/sendEmails")
    @ResponseStatus(HttpStatus.OK)
    public String sendEmailByEmployeeWithDeprecatedPhoto() {
           emailService.sendSimpleMessage(
            "9482159@stud.op.edu.ua",//e.getEmail();
            "e.getName()",
            "Please update your photo"
    );
           return "mail";
    }

}
