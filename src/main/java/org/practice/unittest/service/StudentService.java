package org.practice.unittest.service;


import lombok.RequiredArgsConstructor;
import org.practice.unittest.StudentRepository;
import org.practice.unittest.dto.StudentRequestDto;
import org.practice.unittest.dto.StudentResponseDto;
import org.practice.unittest.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private static final String STUDENT_NOT_FOUND = "Student not found";
    private final StudentRepository studentRepository;

    public StudentResponseDto createStudent(StudentRequestDto studentRequestDto) {
        var student = mapStudentRequestDtoToStudent(studentRequestDto);
        var savedStudent = studentRepository.save(student);
        return new StudentResponseDto(savedStudent);
    }


    public List<StudentResponseDto> getStudents() {
        var students = studentRepository.findAll();
        return students.stream()
                .map(StudentResponseDto::new)
                .toList();
    }

    public StudentResponseDto getStudent(String studentId) {
        var student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException(STUDENT_NOT_FOUND));
        return new StudentResponseDto(student);
    }

    public StudentResponseDto updateStudent(String studentId, StudentRequestDto studentRequestDto) {
        var isStudentExists = studentRepository.existsById(studentId);
        if (!isStudentExists) {
            throw new RuntimeException(STUDENT_NOT_FOUND);
        }
        var student = mapStudentRequestDtoToStudent(studentRequestDto);
        student.setId(studentId);
        var updatedStudent = studentRepository.save(student);
        return new StudentResponseDto(updatedStudent);

    }

    public void deleteStudent(String studentId) {
        var isStudentExists = studentRepository.existsById(studentId);
        if (!isStudentExists) {
            throw new RuntimeException(STUDENT_NOT_FOUND);
        }
        studentRepository.deleteById(studentId);
    }

    private Student mapStudentRequestDtoToStudent(StudentRequestDto studentRequestDto) {
        return Student.builder()
                .name(studentRequestDto.getName())
                .age(studentRequestDto.getAge())
                .grade(studentRequestDto.getGrade())
                .dob(studentRequestDto.getDob())
                .rollNumber(studentRequestDto.getRollNumber())
                .address(studentRequestDto.getAddress())
                .city(studentRequestDto.getCity())
                .build();
    }
}
