package dev.patika.service;


import dev.patika.datatransferobject.CourseDTO;
import dev.patika.entity.Course;
import dev.patika.exceptions.CourseIsAlreadyExistException;
import dev.patika.exceptions.StudentNumberForOneCourseExceededException;
import dev.patika.mappers.CourseMapper;
import dev.patika.repository.CourseRepository;
import dev.patika.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CourseService implements BaseService<Course> {

    private final CourseRepository repository;
    private final CourseMapper courseMapper;
    private final LogRepository logRepository;

    private static Logger logger = Logger.getLogger(CourseService.class);

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {

        List<Course> courseList = new ArrayList<>();
        Iterable<Course> students = repository.findAll();
        students.iterator().forEachRemaining(courseList::add);
        return courseList;
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(int id) {
        return repository.findById(id).get();
    }

    /**
     *
     * @param courseDTO
     * @return repository.save(course)
     */
    @Transactional
    public Course save(CourseDTO courseDTO) {
        boolean isExist = repository.selectExistsCode(courseDTO.getCode());
        if(isExist){


            //CREATE LOG AND SAVE TO DATABASE LOGS TABLE WITH LOG4J
            logger.info("Course with Code : " + courseDTO.getCode() + " is already exists!");

            /*
            MANUEL MAPPING OPTION

            Log log = new Log(System.currentTimeMillis(),"Course with Code : " + courseDTO.getCode() +
                    " is already exists!", "course error");
            logRepository.save(log);
            */

            throw new CourseIsAlreadyExistException("Course with Code : " + courseDTO.getCode() + " is already exists!");
        }

        studentNumberOfCourse(courseDTO);
        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
        return repository.save(course);
    }

    @Transactional
    public Course update(CourseDTO courseDTO) {

        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
        return repository.save(course);

    }

    @Override
    @Transactional
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    /**
     *
     * @param name
     * @return courseList
     */
    public List<Course> findByNameContaining(String name) {
        return repository.findByNameContaining(name);
    }


    @Transactional
    public void deleteByName(String name){
        repository.deleteByName(name);
    }

    @Transactional
    public void deleteAll(){
        repository.deleteAll();
    }

    /**
     *
     * @param courseDTO
     */
    public void studentNumberOfCourse(CourseDTO courseDTO){

        Course course = courseMapper.mapFromCourseDTOtoCourse(courseDTO);
        if (course.getStudentList().size() > 20) {
            throw new StudentNumberForOneCourseExceededException("Maximum allowed student for any course cannot exceed 20");
        }
    }
}
