package org.practice.unittest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;
import org.practice.unittest.controller.StudentController;
import org.practice.unittest.dto.StudentRequestDto;
import org.practice.unittest.dto.StudentResponseDto;
import org.practice.unittest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    private static final String API_URL = "/api/v1/student";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private StudentController studentController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();

        objectMapper = new ObjectMapper();

        when(studentService.createStudent(ArgumentMatchers.any(StudentRequestDto.class)))
                .thenReturn(new StudentResponseDto(
                        "123saodl13",
                        "John",
                        25,
                        12,
                        new Date(1643723400000L),
                        11,
                        "New York",
                        "New York"
                ));
    }

    @Test
    void testCreateStudent() throws Exception {
        // Arrange
        StudentRequestDto studentRequestDto = new StudentRequestDto();
        studentRequestDto.setName("John");
        studentRequestDto.setAge(25);
        studentRequestDto.setGrade(12);
        studentRequestDto.setAddress("New York");
        studentRequestDto.setDob(new Date(1643723400000L)); // February 1, 2023

        String requestBody = objectMapper.writeValueAsString(studentRequestDto);

        // Act & Assert
        mockMvc.perform(post(API_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.age").value(25))
                .andExpect(jsonPath("$.grade").value(12))
                .andExpect(jsonPath("$.address").value("New York"))
                .andExpect(jsonPath("$.dob").exists())
                .andExpect(jsonPath("$.id").exists())
                .andDo(print());
    }
}
