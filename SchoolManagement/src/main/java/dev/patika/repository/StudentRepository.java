package dev.patika.repository;

import dev.patika.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    // select * from student where name='Murat';
    List<Student> findByName(String s);
    List<Student> findByNameContaining(String s);
    void deleteByName(String s);
//    List<Student> findByAgeGreaterThan(int age);


    @Query("select count(s) from Student s")
    int getNumberOfStudents();

    @Query("select s.gender, count(s.gender) from Student s GROUP BY s.gender")
    List<?> getGenderWithGrouping();

    @Query(nativeQuery = true, value = "select gender AS gender, count(gender) AS count from student s GROUP BY gender")
    List<StudentGenderStatistics> getGenderWithGroupingWithNativeQuery();
}
