package dev.patika.controller;


import dev.patika.datatransferobject.CourseDTO;
import dev.patika.entity.Course;
import dev.patika.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>> findAll(){
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/courses")
    public Course saveCourse(@RequestBody CourseDTO courseDTO){
        return courseService.save(courseDTO);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> findCourseById(@PathVariable int id){
        return new ResponseEntity<>(courseService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/courses")
    public Course updateCourse(@RequestBody CourseDTO courseDTO){
        return courseService.update(courseDTO);
    }

    @DeleteMapping("/courses/{id}")
    public String deleteById(@PathVariable int id){
        courseService.deleteById(id);
        return "course with "+ id + " id deleted";
    }

    @GetMapping("/courses/findByNameContaining/{name}")
    public List<Course> findByNameContaining(@PathVariable String name){
        return courseService.findByNameContaining(name);
    }

    @DeleteMapping("/courses/byname/{name}")
    public String deleteCourseByName(@PathVariable String name){
        courseService.deleteByName(name);
        return "course with name " + name + " is deleted";
    }
}
