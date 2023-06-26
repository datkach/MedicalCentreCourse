package com.med.medicalcentrecourse.restControllers;

import com.med.medicalcentrecourse.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@AllArgsConstructor
@RestController
public class PhotoController {
    private final PhotoService photoService;
    // Upload photo by ID
    @PostMapping("/photo")
    @ResponseStatus(HttpStatus.OK)
    public void uploadPhoto(@RequestPart(name = "image") MultipartFile multipartFile) throws IOException {
        photoService.addPhoto(multipartFile);
    }

    // Get Photo By id
    @GetMapping(value = "/photo/{photoId}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] getPhotoImage(@PathVariable Integer photoId) {
        return photoService.getPhoto(photoId);
    }
}
