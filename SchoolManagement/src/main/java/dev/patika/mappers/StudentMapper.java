package dev.patika.mappers;


import dev.patika.datatransferobject.StudentDTO;
import dev.patika.entity.Course;
import dev.patika.entity.Student;
import dev.patika.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.Set;


@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class StudentMapper {


    protected CourseService courseService;

    @Autowired
    public StudentMapper(@Lazy CourseService courseService){
        this.courseService = courseService;
    }

    @Mapping(target = "courseList", expression = "java(getCourseList(studentDTO))")
    public abstract Student mapFromStudentDTOtoStudent(StudentDTO studentDTO);

    @Mapping(target = "courseListId", expression = "java(getCourseListId(student))")
    public abstract StudentDTO mapFromStudenttoStudentDTO(Student student);

    /**
     *
     * @param studentDTO
     * @return courseList
     */
    public Set<Course> getCourseList(StudentDTO studentDTO){

        Set<Course> courses = new HashSet<>();
        studentDTO.getCourseListId().iterator().forEachRemaining(course_id -> courses.add(courseService.findById(course_id)));
        return courses;
    }

    /**
     *
     * @param student
     * @return course'sIdList
     */
    protected Set<Integer> getCourseListId(Student student){

        Set<Integer> courseListId = new HashSet<>();
        student.getCourseList().iterator().forEachRemaining(course -> courseListId.add(course.getId()));
        return courseListId;
    }


}
