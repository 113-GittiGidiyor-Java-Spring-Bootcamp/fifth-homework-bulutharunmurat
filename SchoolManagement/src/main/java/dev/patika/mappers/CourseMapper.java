package dev.patika.mappers;

import dev.patika.datatransferobject.CourseDTO;
import dev.patika.entity.Course;
import dev.patika.entity.Student;
import dev.patika.service.InstructorService;
import dev.patika.service.StudentService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class CourseMapper {

    @Autowired
    protected InstructorService instructorService;

    @Autowired
    protected StudentService studentService;

    // target = mapping to course in course entity, expression = converting id to course object
    @Mapping(target = "instructor", expression = "java(instructorService.findById(courseDTO.getInstructorId()))")
    @Mapping(target = "studentList", expression = "java(getStudentList(courseDTO))")
    public abstract Course mapFromCourseDTOtoCourse(CourseDTO courseDTO);

    @Mapping(target = "instructorId", expression = "java(getInstructorId(course))")
    @Mapping(target = "studentListId", expression = "java(getStudentsId(course))")
    public abstract CourseDTO mapFromCoursetoCourseDTO(Course course);

    /**
     *
     * @param courseDTO
     * @return student'sList
     */
    protected Set<Student> getStudentList(CourseDTO courseDTO){
        Set<Student> students = new HashSet<>();
        courseDTO.getStudentListId().iterator().forEachRemaining(student_id -> students.add(studentService.findById(student_id)));
        return students;
    }

    /**
     *
     * @param course
     * @return students'sIdList
     */
    protected Set<Integer> getStudentsId(Course course){
        Set<Integer> studentsId = new HashSet<>();
        course.getStudentList().iterator().forEachRemaining(student -> studentsId.add(student.getId()));
        return studentsId;
    }

    /**
     *
     * @param course
     * @return instructorId
     */
    protected int getInstructorId(Course course){
        int instructorId = course.getInstructor().getId();
        return instructorId;
    }


}
