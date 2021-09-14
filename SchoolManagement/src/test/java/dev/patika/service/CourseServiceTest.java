package dev.patika.service;

import dev.patika.datatransferobject.CourseDTO;
import dev.patika.datatransferobject.StudentDTO;
import dev.patika.entity.Course;
import dev.patika.entity.Student;
import dev.patika.mappers.CourseMapper;
import dev.patika.mappers.StudentMapper;
import dev.patika.repository.CourseRepository;
import dev.patika.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class CourseServiceTest {

    @Mock
    CourseRepository mockCourseRepository;
    @Mock
    CourseMapper mockCourseMapper;
    @InjectMocks
    CourseService courseService;
    @Test
    void findAll() {
    }

    @Test
    void findById() {
        //given
        Course expexted = new Course();
        when(mockCourseRepository.findById(anyInt())).thenReturn(java.util.Optional.of(expexted));

        //when
        Course actual = this.courseService.findById(1);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expexted, actual)
        );
    }

    @Test
    void save() {
        //given
        Course expected = new Course();
        expected.setCode("math101");
        when(mockCourseRepository.selectExistsCode(anyString())).thenReturn(false);
        when(mockCourseRepository.save(any())).thenReturn(expected);
        when(mockCourseMapper.mapFromCourseDTOtoCourse(any())).thenReturn(expected);


        //when
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setCode("math101");
        Course actual = this.courseService.save(courseDTO);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expected, actual)
        );
    }

    @Test
    void update() {
        Course expexted = new Course();
        when(mockCourseRepository.save(any())).thenReturn(expexted);
        when(mockCourseMapper.mapFromCourseDTOtoCourse(any())).thenReturn(expexted);


        //when
        CourseDTO courseDTO = new CourseDTO();
        Course actual = this.courseService.update(courseDTO);

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
        List<Course> expected = new ArrayList<>();
        when(mockCourseRepository.findByNameContaining(anyString())).thenReturn(expected);


        List<Course> actual = this.courseService.findByNameContaining("a");

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