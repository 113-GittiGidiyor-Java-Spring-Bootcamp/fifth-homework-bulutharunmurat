package dev.patika.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Builder
public class Log extends AbstractBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Long localDate;
    private String message;
    private String errorType;

    public Log(Long localDate, String message, String errorType) {
        this.localDate = localDate;
        this.message = message;
        this.errorType = errorType;
    }
}
