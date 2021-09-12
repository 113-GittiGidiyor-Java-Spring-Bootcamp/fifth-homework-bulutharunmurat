package dev.patika.mappers;

import dev.patika.datatransferobject.VisitingResearcherDTO;
import dev.patika.entity.Course;
import dev.patika.entity.VisitingResearcher;
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
public abstract class VisitingResearcherMapper {

    protected CourseService courseService;

    @Autowired
    public VisitingResearcherMapper(@Lazy CourseService courseService){
        this.courseService = courseService;
    }

    @Mapping(target = "courseList", expression = "java(getCourseList(visitingResearcherDTO))")
    public abstract VisitingResearcher mapFromVisitingResearcherDTOtoVisitingResearcher(VisitingResearcherDTO visitingResearcherDTO);

    @Mapping(target = "courseIdList", expression = "java(getCourseIdList(visitingResearcher))")
    public abstract VisitingResearcherDTO mapFromVisitingResearchertoVisitingResearcherDTO(VisitingResearcher visitingResearcher);

    /**
     *
     * @param visitingResearcherDTO
     * @return courseList
     */
    protected List<Course> getCourseList(VisitingResearcherDTO visitingResearcherDTO){

        List<Course> courses = new ArrayList<>();
        visitingResearcherDTO.getCourseIdList().iterator().forEachRemaining(permanent_id -> courses.add(courseService.findById(permanent_id)));
        return courses;
    }

    /**
     *
     * @param visitingResearcher
     * @return course'sIdList
     */
    protected List<Integer> getCourseIdList(VisitingResearcher visitingResearcher){

        List<Integer> courselists = new ArrayList<>();
        visitingResearcher.getCourseList().iterator().forEachRemaining(visiting -> courselists.add(visiting.getId()));
        return courselists;
    }
}
