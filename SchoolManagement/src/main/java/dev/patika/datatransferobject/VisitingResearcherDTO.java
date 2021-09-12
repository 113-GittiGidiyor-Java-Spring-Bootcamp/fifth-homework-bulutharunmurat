package dev.patika.datatransferobject;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VisitingResearcherDTO extends InstructorDTO{

    @ApiModelProperty(example = "5500.00")
    @NotNull
    private Float hourlySalary;
}
