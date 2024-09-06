package org.practice.unittest.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "student_table")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Integer age;
    private Integer grade;
    private Date dob;
    private Integer rollNumber;
    private String address;
    private String city;
}
