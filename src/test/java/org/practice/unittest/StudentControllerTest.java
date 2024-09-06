package org.practice.unittest;

import org.junit.jupiter.api.Test;
import org.practice.unittest.controller.StudentController;
import org.practice.unittest.dto.StudentRequestDto;
import org.practice.unittest.dto.StudentResponseDto;
import org.practice.unittest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void getStudents() throws Exception {
        // Mock the service layer
        StudentResponseDto student1 = new StudentResponseDto("1", "John Doe", 20, 3, new Date(), 123, "123 Main St", "Anytown");
        StudentResponseDto student2 = new StudentResponseDto("2", "Jane Doe", 22, 4, new Date(), 456, "456 Elm St", "Othertown");
        when(studentService.getStudents()).thenReturn(Arrays.asList(student1, student2));

        // Perform the request and verify the response
        mockMvc.perform(get("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':'1','name':'John Doe','age':20,'grade':3,'dob':null,'rollNumber':123,'address':'123 Main St','city':'Anytown'},{'id':'2','name':'Jane Doe','age':22,'grade':4,'dob':null,'rollNumber':456,'address':'456 Elm St','city':'Othertown'}]"));
    }

    @Test
    public void createStudent() throws Exception {
        // Mock the service layer
        StudentRequestDto studentRequestDto = new StudentRequestDto("John Doe", 20, 3, new Date(), 123, "123 Main St", "Anytown");
        StudentResponseDto studentResponseDto = new StudentResponseDto("1", "John Doe", 20, 3, new Date(), 123, "123 Main St", "Anytown");
        when(studentService.createStudent(studentRequestDto)).thenReturn(studentResponseDto);

        // Perform the request and verify the response
        mockMvc.perform(post("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"age\":20,\"grade\":3,\"dob\":null,\"rollNumber\":123,\"address\":\"123 Main St\",\"city\":\"Anytown\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().json("{'id':'1','name':'John Doe','age':20,'grade':3,'dob':null,'rollNumber':123,'address':'123 Main St','city':'Anytown'}"));
    }

    @Test
    public void updateStudent() throws Exception {
        // Mock the service layer
        StudentRequestDto studentRequestDto = new StudentRequestDto("John Doe", 21, 3, new Date(), 123, "123 Main St", "Anytown");
        StudentResponseDto studentResponseDto = new StudentResponseDto("1", "John Doe", 21, 3, new Date(), 123, "123 Main St", "Anytown");
        when(studentService.updateStudent("1", studentRequestDto)).thenReturn(studentResponseDto);

        // Perform the request and verify the response
        mockMvc.perform(put("/api/v1/student/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"age\":21,\"grade\":3,\"dob\":null,\"rollNumber\":123,\"address\":\"123 Main St\",\"city\":\"Anytown\"}"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':'1','name':'John Doe','age':21,'grade':3,'dob':null,'rollNumber':123,'address':'123 Main St','city':'Anytown'}"));
    }

//    @Test
//    public void deleteStudent() throws Exception {
//        // Mock the service layer
//        when(studentService.deleteStudent("8b0fc7b9-aae4-4447-a089-0a74dfef07bc")).thenReturn(true);
//
//        // Perform the request and verify the response
//        mockMvc.perform(delete("/api/v1/student/8b0fc7b9-aae4-4447-a089-0a74dfef07bc")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status()
//                        .isNoContent());
//    }
}