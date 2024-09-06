package org.practice.unittest.controller;


import lombok.RequiredArgsConstructor;
import org.practice.unittest.dto.StudentRequestDto;
import org.practice.unittest.dto.StudentResponseDto;
import org.practice.unittest.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public StudentResponseDto createStudent(@RequestBody StudentRequestDto student) {
        return studentService.createStudent(student);
    }

    @GetMapping
    public List<StudentResponseDto> getStudents() {

        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public StudentResponseDto getStudent(@PathVariable String id) {

        return studentService.getStudent(id);
    }

    @PutMapping("/{id}")
    public StudentResponseDto updateStudent(@PathVariable String id, @RequestBody StudentRequestDto student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable String id) {
        studentService.deleteStudent(id);
    }


}
