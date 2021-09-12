package dev.patika.repository;

import dev.patika.entity.Instructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Integer> {

    List<Instructor> findByName(String i);
    List<Instructor> findByNameContaining(String i);
    void deleteByName(String i);

    @Query(nativeQuery = true, value = "SELECT HOURLY_SALARY as SALARY FROM VISITING_RESEARCHER " +
            "UNION SELECT FIXED_SALARY as SALARY FROM PERMANENT_INSTRUCTOR " +
            "ORDER BY SALARY DESC")
    List<Instructor> getThreeMostEarningInstructor();

    @Query("SELECT " +
            "  CASE " +
            "   WHEN " +
            "       COUNT(i)>0 " +
            "   THEN " +
            "       TRUE " +
            "   ELSE " +
            "       FALSE " +
            "   END " +
            "FROM Instructor i " +
            "WHERE i.phoneNumber = ?1")
    boolean selectExistsPhoneNumber(String phoneNumber);
}
