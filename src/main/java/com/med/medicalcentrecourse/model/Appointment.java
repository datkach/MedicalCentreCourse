package com.med.medicalcentrecourse.model;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "appointment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(targetEntity =Patient.class )
    private Patient patients;
    @ManyToOne(targetEntity = Doctor.class)
    private Doctor doctors;
    @Column(name = "action_time", nullable = false)
    private LocalDateTime actionTime;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id")
    private Set<Diagnosis> diagnosis = new HashSet<>();
}

