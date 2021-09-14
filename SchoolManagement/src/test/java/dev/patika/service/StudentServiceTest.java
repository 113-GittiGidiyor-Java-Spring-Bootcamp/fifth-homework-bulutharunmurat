package dev.patika.service;

import dev.patika.datatransferobject.StudentDTO;
import dev.patika.entity.Student;
import dev.patika.exceptions.StudentAgeNotValidException;
import dev.patika.mappers.StudentMapper;
import dev.patika.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;


import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    StudentRepository mockStudentRepository;
    @Mock
    StudentMapper mockStudentMapper;
    @InjectMocks
    StudentService studentService;

    @Test
    void findAll() {
    }

    @Test
    void findById() {
        //given
        Student expexted = new Student();
        when(mockStudentRepository.findById(anyInt())).thenReturn(java.util.Optional.of(expexted));

        //when
        Student actual = this.studentService.findById(1);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expexted, actual)
        );
    }

    @Test
    void save() {

        //given
        Student expexted = new Student();
        expexted.setBirthDate(LocalDate.of(1994, Month.MAY,04));
        when(mockStudentRepository.save(any())).thenReturn(expexted);
        when(mockStudentMapper.mapFromStudentDTOtoStudent(any())).thenReturn(expexted);


        //when
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setBirthDate(LocalDate.of(1994, Month.MAY,04));
        Student actual = this.studentService.save(studentDTO);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expexted, actual)
        );
    }

    @Test
    void saveWithRestrictedAgeLessThan18() {

        //given
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setBirthDate(LocalDate.of(2005, Month.MAY,04));
        Executable executable = ()-> this.studentService.save(studentDTO);
        //then
        assertThrows(StudentAgeNotValidException.class, executable);
    }

    @Test
    void saveWithRestrictedAgeOlderThan40() {

        //given
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setBirthDate(LocalDate.of(1900, Month.MAY,04));
        Executable executable = ()-> this.studentService.save(studentDTO);
        //then
        assertThrows(StudentAgeNotValidException.class, executable);
    }

    @Test
    void update() {
        //given
        Student expexted = new Student();
        expexted.setBirthDate(LocalDate.of(1994, Month.MAY,04));
        when(mockStudentRepository.save(any())).thenReturn(expexted);
        when(mockStudentMapper.mapFromStudentDTOtoStudent(any())).thenReturn(expexted);


        //when
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setBirthDate(LocalDate.of(1994, Month.MAY,04));
        Student actual = this.studentService.update(studentDTO);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expexted, actual)
        );

    }

    @Test
    void deleteById() {
    }

    @Test
    void getNumberOfStudents() {
    }

    @Test
    void getGenderWithGrouping() {
    }

    @Test
    void getGenderWithGroupingWithNativeQuery() {
    }

    @Test
    void getStudentWithName() {

        List<Student> expected = new ArrayList<>();
        when(mockStudentRepository.findByName(anyString())).thenReturn(expected);


        List<Student> actual = this.studentService.getStudentWithName("Murat");

        assertEquals(expected, actual);
    }

    @Test
    void findByNameContaining() {
        List<Student> expected = new ArrayList<>();
        when(mockStudentRepository.findByNameContaining(anyString())).thenReturn(expected);


        List<Student> actual = this.studentService.findByNameContaining("a");

        assertEquals(expected, actual);
    }

    @Test
    void deleteByName() {

    }

    @Test
    void deleteAll() {
    }

    @Test
    void studentNumberOfCourse() {
    }
}