package dev.patika.service;

import dev.patika.datatransferobject.InstructorDTO;
import dev.patika.datatransferobject.PermanentInstructorDTO;
import dev.patika.datatransferobject.StudentDTO;
import dev.patika.entity.Instructor;
import dev.patika.entity.PermanentInstructor;
import dev.patika.entity.Student;
import dev.patika.entity.VisitingResearcher;
import dev.patika.exceptions.InstructorIsAlreadyExistException;
import dev.patika.exceptions.StudentAgeNotValidException;
import dev.patika.mappers.InstructorMapper;
import dev.patika.mappers.StudentMapper;
import dev.patika.repository.InstructorRepository;
import dev.patika.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InstructorServiceTest {

    @Mock
    InstructorRepository mockInstructorRepository;
    @Mock
    InstructorMapper mockInstructorMapper;
    @InjectMocks
    InstructorService instructorService;

    @Test
    void findAll() {
    }

    @Test
    void findById() {
        //given
        Instructor expexted = new Instructor();
        when(mockInstructorRepository.findById(anyInt())).thenReturn(java.util.Optional.of(expexted));

        //when
        Instructor actual = this.instructorService.findById(1);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expexted, actual)
        );
    }

    @Test
    void save() {
        //given
        Instructor expexted = new Instructor();
        when(mockInstructorRepository.save(any())).thenReturn(expexted);
        when(mockInstructorMapper.mapFromInstructorDTOtoInstructor(any())).thenReturn(expexted);


        //when
        InstructorDTO instructorDTO = new InstructorDTO();
        Instructor actual = this.instructorService.save(instructorDTO);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expexted, actual)
        );
    }

    @Test
    void saveExistingPhoneNumber() {
        //given
        PermanentInstructorDTO instructorDTO = new PermanentInstructorDTO();
        instructorDTO.setPhoneNumber("05314054416");
        when(mockInstructorRepository.selectExistsPhoneNumber(anyString())).thenReturn(true);
        Executable executable = ()-> this.instructorService.savePermanentInstructor(instructorDTO);
        //then
        assertThrows(InstructorIsAlreadyExistException.class, executable);
    }

    @Test
    void update() {
        //given
        Instructor expexted = new Instructor();
        when(mockInstructorRepository.save(any())).thenReturn(expexted);
        when(mockInstructorMapper.mapFromInstructorDTOtoInstructor(any())).thenReturn(expexted);


        //when
        InstructorDTO instructorDTO = new InstructorDTO();
        Instructor actual = this.instructorService.update(instructorDTO);

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
    void findByNameContaining() {
        List<Instructor> expected = new ArrayList<>();
        when(mockInstructorRepository.findByNameContaining(anyString())).thenReturn(expected);


        List<Instructor> actual = this.instructorService.findByNameContaining("a");

        assertEquals(expected, actual);
    }

    @Test
    void deleteByName() {
    }

    @Test
    void getThreeMostEarningInstructor() {
    }

    @Test
    void saveVisitingResearcher() {
        //given
        VisitingResearcher expexted = new VisitingResearcher();
        when(mockInstructorRepository.save(any())).thenReturn(expexted);
        when(mockInstructorMapper.mapFromInstructorDTOtoInstructor(any())).thenReturn(expexted);


        //when
        InstructorDTO instructorDTO = new InstructorDTO();
        Instructor actual = this.instructorService.save(instructorDTO);

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
        when(mockInstructorRepository.save(any())).thenReturn(expexted);
        when(mockInstructorMapper.mapFromInstructorDTOtoInstructor(any())).thenReturn(expexted);


        //when
        InstructorDTO instructorDTO = new InstructorDTO();
        Instructor actual = this.instructorService.save(instructorDTO);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expexted, actual)
        );
    }

    @Test
    void deleteAll() {
    }

    @Test
    void updateInstructorSalary() {
    }

    @Test
    void saveSalaryUpdateToDatabase() {
    }

}