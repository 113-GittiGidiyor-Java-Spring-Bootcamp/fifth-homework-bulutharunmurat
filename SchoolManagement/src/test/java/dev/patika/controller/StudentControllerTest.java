package dev.patika.controller;

import dev.patika.datatransferobject.StudentDTO;
import dev.patika.entity.Student;
import dev.patika.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {


    @Mock
    StudentService mockStudentService;
    @InjectMocks
    StudentController studentController;

    @Test
    void findAll() {
    }

    @Test
    void saveStudent() {
        //given
        Student expexted = new Student();
        expexted.setGender("male");
        when(mockStudentService.save(any())).thenReturn(expexted);

        //when
        StudentDTO studentDTO = new StudentDTO();
        Student actual = this.studentController.saveStudent(studentDTO);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expexted, actual),
                ()-> assertEquals(expexted.getGender(), actual.getGender())
        );

    }

    @Test
    void findStudentById() {
        //given
        Student expexted = new Student();
        when(mockStudentService.findById(anyInt())).thenReturn(expexted);

        //when
        StudentDTO studentDTO = new StudentDTO();
        Student actual = this.studentController.findStudentById(1).getBody();

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expexted, actual)
        );
    }

    @Test
    void findStudentByNonExistingId() {
        //given
        Student expexted = new Student();
        when(mockStudentService.findById(anyInt())).thenReturn(null);

        //when

        StudentDTO studentDTO = new StudentDTO();
        Student actual = this.studentController.findStudentById(1).getBody();

        //then
        assertNull(actual);
    }

    @Test
    void updateStudent() {
        //given
        Student expected = new Student();
        when(mockStudentService.update(any())).thenReturn(expected);

        //when
        StudentDTO studentDTO = new StudentDTO();
        Student actual = this.studentController.updateStudent(studentDTO);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expected, actual)
        );
    }

    @Test
    void deleteStudentById() {

        //given
        String expected = "student with " + 1 + " id deleted";
        //when

        StudentDTO studentDTO = new StudentDTO();
        String actual = this.studentController.deleteStudentById(1);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void getNumberOfStudents() {
        //given
        when(mockStudentService.getNumberOfStudents()).thenReturn(20);

        //when
        StudentDTO studentDTO = new StudentDTO();
        String actual = this.studentController.getNumberOfStudents();

        //then
        assertEquals("Total student number: " + 20, actual);

    }

    @Test
    void getAgesWithGrouping() {
    }

    @Test
    void getGenderWithGroupingWithNativeQuery() {
    }

    @Test
    void getStudentWithName() {
    }

    @Test
    void findByNameContaining() {
    }

    @Test
    void deleteStudentByName() {
    }
}