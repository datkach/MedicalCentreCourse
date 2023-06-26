package com.med.medicalcentrecourse.repository;

import com.med.medicalcentrecourse.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo,Integer> {
}
