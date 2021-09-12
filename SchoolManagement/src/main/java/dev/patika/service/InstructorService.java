package dev.patika.service;


import dev.patika.datatransferobject.InstructorDTO;
import dev.patika.datatransferobject.PermanentInstructorDTO;
import dev.patika.datatransferobject.VisitingResearcherDTO;
import dev.patika.entity.Instructor;
import dev.patika.entity.InstructorSalaryUpdateLogger;
import dev.patika.entity.PermanentInstructor;
import dev.patika.entity.VisitingResearcher;
import dev.patika.exceptions.InstructorIsAlreadyExistException;
import dev.patika.mappers.InstructorMapper;
import dev.patika.mappers.PermanentInstructorMapper;
import dev.patika.mappers.VisitingResearcherMapper;
import dev.patika.repository.InstructorRepository;
import dev.patika.repository.UpdateSalaryRepository;
import dev.patika.util.ClientRequestInfo;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService implements BaseService<Instructor>{

    private final InstructorRepository repository;
    public static Logger logger = Logger.getLogger(InstructorService.class);


    private final InstructorMapper instructorMapper;
    private final PermanentInstructorMapper permanentInstructorMapper;
    private final VisitingResearcherMapper visitingResearcherMapper;
    private final ClientRequestInfo clientRequestInfo;
    private final UpdateSalaryRepository updateSalaryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Instructor> findAll() {

        List<Instructor> courseList = new ArrayList<>();
        Iterable<Instructor> students = repository.findAll();
        students.iterator().forEachRemaining(courseList::add);
        return courseList;
    }

    @Override
    @Transactional(readOnly = true)
    public Instructor findById(int id) {
        return repository.findById(id).get();
    }


    @Transactional
    public Instructor save(InstructorDTO instructorDTO) {
        Instructor instructor = instructorMapper.mapFromInstructorDTOtoInstructor(instructorDTO);
        return repository.save(instructor);
    }

    @Transactional
    public Instructor update(InstructorDTO instructorDTO) {
        Instructor instructor = instructorMapper.mapFromInstructorDTOtoInstructor(instructorDTO);
        return repository.save(instructor);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public List<Instructor> findByNameContaining(String name) {
        return repository.findByNameContaining(name);
    }

    @Transactional
    public void deleteByName(String name){
        repository.deleteByName(name);
    }

    public List<Instructor>  getThreeMostEarningInstructor(){

        List<Instructor> allInstructors = repository.getThreeMostEarningInstructor();
        List<Instructor> instructorsOfThreeMost = new ArrayList<>();

        for(int i=0; i<3; i++){
            instructorsOfThreeMost.add(allInstructors.get(i));
        }
       return instructorsOfThreeMost;
    }

    public Instructor saveVisitingResearcher(VisitingResearcherDTO visitingResearcherDTO) {
        boolean isExist = repository.selectExistsPhoneNumber(visitingResearcherDTO.getPhoneNumber());
        if(isExist){

            //CREATE LOG AND SAVE TO DATABASE LOGS TABLE WITH LOG4J
            logger.info("Instructor with Phone Number : " + visitingResearcherDTO.getPhoneNumber() + " is already exists!");
           
            throw new InstructorIsAlreadyExistException("Instructor with Phone Number : " + visitingResearcherDTO.getPhoneNumber() + " is already exists!");
        }
        VisitingResearcher visitingResearcher = visitingResearcherMapper.mapFromVisitingResearcherDTOtoVisitingResearcher(visitingResearcherDTO);
        return repository.save(visitingResearcher);
    }

    public Instructor savePermanentInstructor(PermanentInstructorDTO permanentInstructorDTO) {
        boolean isExist = repository.selectExistsPhoneNumber(permanentInstructorDTO.getPhoneNumber());
        if(isExist){

            //CREATE LOG AND SAVE TO DATABASE LOGS TABLE WITH LOG4J
            logger.info("Instructor with Phone Number : " + permanentInstructorDTO.getPhoneNumber() + " is already exists!");

            throw new InstructorIsAlreadyExistException("Instructor with Phone Number : " + permanentInstructorDTO.getPhoneNumber() + " is already exists!");
        }
        PermanentInstructor permanentInstructor = permanentInstructorMapper.mapFromPermanentInstructorDTOtoPermanentInstructor(permanentInstructorDTO);
        return repository.save(permanentInstructor);
    }

    @Transactional
    public void deleteAll() {
        repository.deleteAll();
    }

    public Instructor updateInstructorSalary(int instructorId, double percentage) {
        Instructor instructor = this.findById(instructorId);
        float beforeSalary;
        float afterSalary;
        if (instructor instanceof VisitingResearcher){
            beforeSalary = ((VisitingResearcher) instructor).getHourlySalary();
            afterSalary = (float) (beforeSalary + (beforeSalary * (percentage/100)));

            ((VisitingResearcher) instructor).setHourlySalary(afterSalary);
        }

        else {
            beforeSalary = ((PermanentInstructor) instructor).getFixedSalary();
            afterSalary = (float) (beforeSalary + (beforeSalary * (percentage/100)));
            ((PermanentInstructor) instructor).setFixedSalary(afterSalary);
        }

        this.saveSalaryUpdateToDatabase(instructor, percentage, beforeSalary, afterSalary );
        return repository.save(instructor);
    }

    public void saveSalaryUpdateToDatabase(Instructor instructor, double percentage, float beforeSalary, float afterSalary){

        InstructorSalaryUpdateLogger instructorSalaryUpdateLogger = new InstructorSalaryUpdateLogger();
        instructorSalaryUpdateLogger.setInstructorId(instructor.getId());
        instructorSalaryUpdateLogger.setPercentage(percentage);
        instructorSalaryUpdateLogger.setBeforeSalary(beforeSalary);
        instructorSalaryUpdateLogger.setAfterSalary(afterSalary);
        instructorSalaryUpdateLogger.setTransactionDate(LocalDate.now());
        instructorSalaryUpdateLogger.setClientIpAddress(clientRequestInfo.getClientIpAddress());
        instructorSalaryUpdateLogger.setClientUrl(clientRequestInfo.getClientUrl());
        instructorSalaryUpdateLogger.setSessionActivityId(clientRequestInfo.getSessionActivityId());

        updateSalaryRepository.save(instructorSalaryUpdateLogger);

    }

    public Page<List<InstructorSalaryUpdateLogger>> getAllTransactionsById(int id, Pageable pageable) {
        return updateSalaryRepository.findByIdWithPage((long) id, pageable);
    }
}
