package dev.patika.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data //-> @RequiredArgsConstructor, @Getter, @Setter, @EqualsAndHashCode, @ToString
@Table(name = "Course")
@AllArgsConstructor
@NoArgsConstructor
@Entity //Spring DATA JPA
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Course extends AbstractBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String code;
    private Float creditScore;

    public Course(String name, String code, Float creditScore) {
        this.name = name;
        this.code = code;
        this.creditScore = creditScore;
    }

    public void addStudent(Student student){
        studentList.add(student);
    }

    @JsonBackReference
    @ManyToMany(mappedBy = "courseList", fetch = FetchType.LAZY)
    private Set<Student> studentList;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Instructor instructor;

}
