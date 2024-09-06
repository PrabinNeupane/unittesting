package org.practice.unittest.dto;

import org.practice.unittest.entity.Student;

import java.util.Date;

public record StudentResponseDto(
        String id,
        String name,
        Integer age,
        Integer grade,
        Date dob,
        Integer rollNumber,
        String address,
        String city
) {

    public StudentResponseDto(Student student) {
        this(
                student.getId(),
                student.getName(),
                student.getAge(),
                student.getGrade(),
                student.getDob(),
                student.getRollNumber(),
                student.getAddress(),
                student.getCity()
        );
    }
}
