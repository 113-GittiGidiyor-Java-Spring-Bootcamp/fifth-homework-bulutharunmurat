package dev.patika.repository;

import dev.patika.entity.InstructorSalaryUpdateLogger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UpdateSalaryRepository extends PagingAndSortingRepository<InstructorSalaryUpdateLogger, Long> {

    @Query("SELECT i FROM InstructorSalaryUpdateLogger i WHERE i.InstructorId=?1")
    Page<List<InstructorSalaryUpdateLogger>> findByIdWithPage(int id, Pageable pageable);
}
