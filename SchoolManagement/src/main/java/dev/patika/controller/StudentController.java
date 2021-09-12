package dev.patika.controller;



import dev.patika.datatransferobject.StudentDTO;
import dev.patika.entity.Student;
import dev.patika.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> findAll(){
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/students")
    public Student saveStudent(@RequestBody StudentDTO studentDTO){
        return studentService.save(studentDTO);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable int id){
        return new ResponseEntity<>(studentService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody StudentDTO studentDTO){
        return studentService.update(studentDTO);
    }

    @DeleteMapping("/students/{id}")
    public String deleteStudentById(@PathVariable int id){
        studentService.deleteById(id);
        return "student with " + id + " id deleted";
    }

    @GetMapping("/getNumberOfStudents")
    public String getNumberOfStudents(){
        int studentNumber= studentService.getNumberOfStudents();
        return "Total student number: " + studentNumber;
    }

    @GetMapping("/getGenderWithGrouping")
    public List<?> getAgesWithGrouping(){
        return studentService.getGenderWithGrouping();
    }

    @GetMapping("/getGenderWithGroupingWithNativeQuery")
    public List<?> getGenderWithGroupingWithNativeQuery(){
        return studentService.getGenderWithGroupingWithNativeQuery();
    }

    @GetMapping("/findByName/{name}")
    public List<Student> getStudentWithName(@PathVariable String name){
        return studentService.getStudentWithName(name);
    }

    @GetMapping("/students/findByNameContaining/{name}")
    public List<Student> findByNameContaining(@PathVariable String name){
        return studentService.findByNameContaining(name);
    }

    @DeleteMapping("/students/byname/{name}")
    public String deleteStudentByName(@PathVariable String name){
        studentService.deleteByName(name);
        return "student with name " + name + " is deleted";
    }
}


