package dev.patika.mappers;


import dev.patika.datatransferobject.PermanentInstructorDTO;
import dev.patika.entity.Course;
import dev.patika.entity.PermanentInstructor;
import dev.patika.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
@RequiredArgsConstructor
public abstract class PermanentInstructorMapper {


    protected CourseService courseService;

    @Autowired
    public PermanentInstructorMapper(@Lazy CourseService courseService){
        this.courseService = courseService;
    }


    @Mapping(target = "courseList", expression = "java(getCourseList(permanentInstructorDTO))")
    public abstract PermanentInstructor mapFromPermanentInstructorDTOtoPermanentInstructor(PermanentInstructorDTO permanentInstructorDTO);

    @Mapping(target = "courseIdList", expression = "java(getCourseIdList(permanentInstructor))")
    public abstract PermanentInstructorDTO mapFromPermanentInstructortoPermanentInstructorDTO(PermanentInstructor permanentInstructor);

    /**
     *
     * @param permanentInstructorDTO
     * @return courseList
     */
    protected List<Course> getCourseList(PermanentInstructorDTO permanentInstructorDTO){

        List<Course> courses = new ArrayList<>();
        permanentInstructorDTO.getCourseIdList().iterator().forEachRemaining(permanent_id -> courses.add(courseService.findById(permanent_id)));
        return courses;
    }

    /**
     *
     * @param permanentInstructor
     * @return course'sIdList
     */
    protected List<Integer> getCourseIdList(PermanentInstructor permanentInstructor){

        List<Integer> courselists = new ArrayList<>();
        permanentInstructor.getCourseList().iterator().forEachRemaining(permanent -> courselists.add(permanent.getId()));
        return courselists;
    }
}
