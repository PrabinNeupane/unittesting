package org.practice.unittest.dto;

import org.practice.unittest.entity.Student;

import java.util.Date;

public record StudentResponseDto(String id, String name, Integer age, Integer grade, Date dob, Integer rollNumber,
                                 String address, String city) {

    public StudentResponseDto(Student student) {
        this(student.getId(), student.getName(), student.getAge(), student.getGrade(), student.getDob(), student.getRollNumber(), student.getAddress(), student.getCity());
    }

    public StudentResponseDto(String id, String name, Integer age, Integer grade, Date dob, Integer rollNumber, String address, String city) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.dob = dob;
        this.rollNumber = rollNumber;
        this.address = address;
        this.city = city;
    }
}
