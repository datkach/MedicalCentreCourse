package com.med.medicalcentrecourse.service;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
