package org.practice.unittest.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class StudentRequestDto {
    private String name;
    private Integer age;
    private Integer grade;
    private Date dob;
    private Integer rollNumber;
    private String address;
    private String city;
}
