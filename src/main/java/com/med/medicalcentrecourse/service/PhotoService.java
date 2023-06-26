package com.med.medicalcentrecourse.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PhotoService {
    void addPhoto(MultipartFile file) throws IOException;
    byte[] getPhoto(Integer photoId);
}

