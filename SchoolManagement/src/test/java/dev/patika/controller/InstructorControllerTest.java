package dev.patika.controller;

import dev.patika.datatransferobject.InstructorDTO;
import dev.patika.datatransferobject.PermanentInstructorDTO;
import dev.patika.datatransferobject.StudentDTO;
import dev.patika.datatransferobject.VisitingResearcherDTO;
import dev.patika.entity.Instructor;
import dev.patika.entity.PermanentInstructor;
import dev.patika.entity.Student;
import dev.patika.entity.VisitingResearcher;
import dev.patika.service.InstructorService;
import dev.patika.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InstructorControllerTest {

    @Mock
    InstructorService mockInstructorService;
    @InjectMocks
    InstructorController instructorController;

    @Test
    void findAll() {
    }

    @Test
    void findInstructorsById() {
    }

    @Test
    void saveInstructor() {
        //given
        Instructor expexted = new Instructor();
        when(mockInstructorService.save(any())).thenReturn(expexted);

        //when
        InstructorDTO instructorDTO = new InstructorDTO();
        Instructor actual = this.instructorController.saveInstructor(instructorDTO);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expexted, actual)
        );
    }

    @Test
    void saveVisitingResearcher() {
        //given
        VisitingResearcher expexted = new VisitingResearcher();
        when(mockInstructorService.save(any())).thenReturn(expexted);

        //when
        VisitingResearcherDTO instructorDTO = new VisitingResearcherDTO();
        Instructor actual = this.instructorController.saveInstructor(instructorDTO);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expexted, actual)
        );
    }

    @Test
    void savePermanentInstructor() {
        //given
        PermanentInstructor expexted = new PermanentInstructor();
        when(mockInstructorService.save(any())).thenReturn(expexted);

        //when
        PermanentInstructorDTO instructorDTO = new PermanentInstructorDTO();
        Instructor actual = this.instructorController.saveInstructor(instructorDTO);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expexted, actual)
        );
    }

    @Test
    void updateInstructor() {
        Instructor expected = new Instructor();
        when(mockInstructorService.update(any())).thenReturn(expected);

        //when
        InstructorDTO studentDTO = new InstructorDTO();
        Instructor actual = this.instructorController.updateInstructor(studentDTO);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expected, actual)
        );
    }

    @Test
    void deleteInstructorById() {
        //given
        String expected = "instructor with "+ 1 + " id deleted";
        //when
        String actual = this.instructorController.deleteInstructorById(1);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void findByNameContaining() {
        //given
        List<Instructor> expected = new ArrayList<>();
        when(mockInstructorService.findByNameContaining(anyString())).thenReturn(expected);

        //when
        List<Instructor> actual = this.instructorController.findByNameContaining("a");

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expected, actual)
        );
    }

    @Test
    void deleteInstructorByName() {
        //given
        String expected = "instructor with name " + "Murat" + " is deleted";
        //when

        String actual = this.instructorController.deleteInstructorByName("Murat");

        //then
        assertEquals(expected, actual);
    }

    @Test
    void getThreeMostEarningInstructor() {
    }

    @Test
    void updateInstructorSalary() {
    }

    @Test
    void getAllTransactionsById() {
    }
}