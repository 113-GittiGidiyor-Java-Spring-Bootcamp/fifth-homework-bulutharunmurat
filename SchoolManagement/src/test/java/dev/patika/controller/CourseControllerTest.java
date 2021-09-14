package dev.patika.controller;

import dev.patika.datatransferobject.CourseDTO;
import dev.patika.datatransferobject.StudentDTO;
import dev.patika.entity.Course;
import dev.patika.entity.Student;
import dev.patika.service.CourseService;
import dev.patika.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {

    @Mock
    CourseService mockCourseService;
    @InjectMocks
    CourseController courseController;

    @Test
    void findAll() {
    }

    @Test
    void saveCourse() {
        //given
        Course expected = new Course();
        when(mockCourseService.save(any())).thenReturn(expected);

        //when
        CourseDTO courseDTO = new CourseDTO();
        Course actual = this.courseController.saveCourse(courseDTO);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expected, actual)
        );
    }

    @Test
    void findCourseById() {

        //given
        Course expected = new Course();
        when(mockCourseService.findById(anyInt())).thenReturn(expected);

        //when
        CourseDTO courseDTO = new CourseDTO();
        Course actual = this.courseController.findCourseById(1).getBody();

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expected, actual)
        );
    }

    @Test
    void findCourseByNonExistingId() {
        //given
        Course expected = new Course();
        when(mockCourseService.findById(anyInt())).thenReturn(null);

        //when

        CourseDTO courseDTO = new CourseDTO();
        Course actual = this.courseController.findCourseById(1).getBody();

        //then
        assertNull(actual);
    }

    @Test
    void updateCourse() {
        //given
        Course expected = new Course();
        when(mockCourseService.update(any())).thenReturn(expected);

        //when
        CourseDTO courseDTO = new CourseDTO();
        Course actual = this.courseController.updateCourse(courseDTO);

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expected, actual)
        );
    }


    @Test
    void deleteById() {
        //given
        String expected = "course with "+ 1 + " id deleted";
        //when

        String actual = this.courseController.deleteById(1);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void findByNameContaining() {
        //given
        List<Course> expected = new ArrayList<>();
        when(mockCourseService.findByNameContaining(anyString())).thenReturn(expected);

        //when
        List<Course> actual = this.courseController.findByNameContaining("a");

        //then
        assertAll(
                ()-> assertNotNull(actual),
                ()-> assertEquals(expected, actual)
        );
    }

    @Test
    void deleteCourseByName() {
        //given
        String expected = "course with name " + "Math" + " is deleted";
        //when

        String actual = this.courseController.deleteCourseByName("Math");

        //then
        assertEquals(expected, actual);
    }
}