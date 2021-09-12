package dev.patika.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class InstructorSalaryUpdateLogger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int InstructorId;
    private double percentage;
    private double beforeSalary;
    private double afterSalary;
    private LocalDate transactionDate;
    private String clientIpAddress;
    private String clientUrl;
    private String sessionActivityId;

}
