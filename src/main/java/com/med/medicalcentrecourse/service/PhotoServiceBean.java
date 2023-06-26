package com.med.medicalcentrecourse.service;

import com.med.medicalcentrecourse.model.Photo;
import com.med.medicalcentrecourse.repository.PhotoRepository;
import com.med.medicalcentrecourse.util.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@AllArgsConstructor
@Service
public class PhotoServiceBean implements PhotoService{
    private final PhotoRepository photoRepository;
    @Override
    public void addPhoto(MultipartFile file) throws IOException {
        Photo photo = new Photo();
        photo.setImage(file.getBytes());
        photoRepository.save(photo);
    }

    @Override
    public byte[] getPhoto(Integer photoId) {
        Photo photo = photoRepository.findById(photoId).orElseThrow(ResourceNotFoundException::new);
        return photo.getImage();
    }
}

