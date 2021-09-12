package dev.patika.datatransferobject;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    @ApiModelProperty(hidden = true)
    private Integer id;

    @ApiModelProperty(example = "Harun Murat Bulut")
    @NotBlank(message = "Name is mandatory")
    private String name;

    @ApiModelProperty(example = "Pendik/İstanbul")
    private String address;

    @ApiModelProperty(example = "1994-05-04")
    private LocalDate birthDate;

    @ApiModelProperty(example = "Male")
    @NotBlank(message = "Gender is mandatory")
    private String gender;

    @ApiModelProperty(example = "[1,2]")
    @NotBlank
    private Set<Integer> courseListId;
}
