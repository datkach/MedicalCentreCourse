package com.med.medicalcentrecourse.util.mapper;

import com.med.medicalcentrecourse.dto.DoctorRequestDto;
import com.med.medicalcentrecourse.dto.DoctorResponseDto;
import com.med.medicalcentrecourse.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DoctorMapper {
    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    Doctor fromDto(DoctorRequestDto dto);

    DoctorResponseDto toDto(Doctor doctor);

    List<DoctorResponseDto> toListDto(List<Doctor> doctors);

}
