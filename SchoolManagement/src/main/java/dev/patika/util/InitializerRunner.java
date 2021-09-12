package dev.patika.util;


import dev.patika.mappers.CourseMapper;
import dev.patika.mappers.InstructorMapper;
import dev.patika.mappers.StudentMapper;
import dev.patika.service.CourseService;
import dev.patika.service.InstructorService;
import dev.patika.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class InitializerRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(InitializerRunner.class);


    @Autowired
    StudentMapper studentMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    InstructorMapper instructorMapper;

    @Autowired
    StudentService studentService;

    @Autowired
    CourseService courseService;

    @Autowired
    InstructorService instructorService;

    @Override
    public void run(String... args) throws Exception {
//
//        courseService.deleteAll();
//        studentService.deleteAll();
//        instructorService.deleteAll();
//
//        StudentDTO student1 = studentMapper.mapFromStudenttoStudentDTO(Student.builder()
//                .name("Harun Murat")
//                .address("Istanbul")
//                .birthDate(LocalDate.of(2001, Month.AUGUST,18))
//                .gender("male")
//                .build());
//
//        StudentDTO student2 = studentMapper.mapFromStudenttoStudentDTO(Student.builder()
//                .name("Bulut")
//                .address("Bursa")
//                .birthDate( LocalDate.of(1994, Month.MAY,04))
//                .gender("male")
//                .build());
//
//
//        studentService.save(student1);
//        studentService.save(student2);
        /*
        instructorService.save(instructorMapper.mapFromInstructortoInstructorDTO(
            PermanentInstructor.builder()
                    .name("Tolga Ovatman")
                    .address("Istanbul")
                    .phoneNumber("00212220")
                    .fixedSalary(50.00F)));

        instructorService.save(instructorMapper.mapFromInstructortoInstructorDTO(
                VisitingResearcher.builder()
                        .name("Tacettin")
                        .address("Istanbul")
                        .phoneNumber("051231")
                        .hourlySalary(40.00F)));
        */

//        CourseDTO course1 = courseMapper.mapFromCoursetoCourseDTO(Course.builder()
//                .code("CAL101")
//                .creditScore(3.50F)
//                .name("CALCULUS")
//                .build());
//
//        CourseDTO course2 = courseMapper.mapFromCoursetoCourseDTO(Course.builder()
//                .code("CS101")
//                .creditScore(4.0F)
//                .name("COMPUTER SCIENCE")
//                .build());
//
//        CourseDTO course3 = courseMapper.mapFromCoursetoCourseDTO(Course.builder()
//                .code("ENG101")
//                .creditScore(2.0F)
//                .name("ENGLISH")
//                .build());



//        Student student1_ = studentMapper.mapFromStudentDTOtoStudent(student1);
//        Course course1_ = courseMapper.mapFromCourseDTOtoCourse(course1);
//        student1_.addCourse(course1_);


//
//        courseService.save(course1);
//        courseService.save(course2);
//        courseService.save(course3);
//
//        courseService.findAll().forEach(course -> logger.info("{}", course));

    }
}
