package org.practice.unittest.dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
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
