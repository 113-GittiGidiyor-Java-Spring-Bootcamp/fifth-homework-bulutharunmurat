package dev.patika.controller;


import dev.patika.datatransferobject.InstructorDTO;
import dev.patika.datatransferobject.PermanentInstructorDTO;
import dev.patika.datatransferobject.VisitingResearcherDTO;
import dev.patika.entity.Instructor;
import dev.patika.entity.InstructorSalaryUpdateLogger;
import dev.patika.service.InstructorService;
import dev.patika.util.ClientRequestInfo;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class InstructorController {

    InstructorService instructorService;
    ClientRequestInfo clientRequestInfo;

    @Autowired
    public InstructorController(InstructorService instructorService, ClientRequestInfo clientRequestInfo) {
        this.instructorService = instructorService;
        this.clientRequestInfo = clientRequestInfo;
    }

    @GetMapping("/instructors")
    public ResponseEntity<List<Instructor>> findAll(){
        return new ResponseEntity<>(instructorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/instructors/{id}")
    public ResponseEntity<Instructor> findInstructorsById(@PathVariable int id){
        return new ResponseEntity<>(instructorService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/instructors")
    public Instructor saveInstructor(@RequestBody InstructorDTO instructorDTO){
        return instructorService.save(instructorDTO);
    }

    @PostMapping("/instructors/visitingResearcher")
    public Instructor saveVisitingResearcher(@RequestBody VisitingResearcherDTO visitingResearcherDTO){
        return instructorService.saveVisitingResearcher(visitingResearcherDTO);
    }

    @PostMapping("/instructors/permanentInstructor")
    public Instructor savePermanentInstructor(@RequestBody PermanentInstructorDTO permanentInstructorDTO){
        return instructorService.savePermanentInstructor(permanentInstructorDTO);
    }

    @PutMapping("/instructors")
    public Instructor updateInstructor(@RequestBody InstructorDTO instructorDTO){
        return instructorService.update(instructorDTO);
    }

    @DeleteMapping("/instructors/{id}")
    public String deleteInstructorById(@PathVariable int id){
        instructorService.deleteById(id);
        return "instructor with "+ id + " id deleted";
    }

    @GetMapping("/instructors/findByNameContaining/{name}")
    public List<Instructor> findByNameContaining(@PathVariable String name){
        return instructorService.findByNameContaining(name);
    }

    @DeleteMapping("/instructors/byname/{name}")
    public String deleteInstructorByName(@PathVariable String name){
        instructorService.deleteByName(name);
        return "instructor with name " + name + " is deleted";
    }

    @GetMapping("/instructors/getThreeMostEarningInstructor")
    public List<Instructor> getThreeMostEarningInstructor(){
        return instructorService.getThreeMostEarningInstructor();
    }

    @PutMapping("/instructors/updateSalary/{instructorId}/{percentage}")
    public Instructor updateInstructorSalary(@PathVariable int instructorId,
                                             @PathVariable float percentage
                                             ){
        log.info(String.valueOf(clientRequestInfo));
        return instructorService.updateInstructorSalary(instructorId,percentage);
    }

    @GetMapping("/getSalaryUpdateById/{id}")
    public ResponseEntity<Page<List<InstructorSalaryUpdateLogger>>> getAllTransactionsById(
            @PathVariable int id,
            @PageableDefault(page = 0, size = 10)Pageable pageable){
        return new ResponseEntity<>(instructorService.getAllTransactionsById(id, pageable), HttpStatus.OK);
    }
}
