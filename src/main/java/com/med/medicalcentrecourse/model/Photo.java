package com.med.medicalcentrecourse.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "photos")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@ToString
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private byte[] image;
}
