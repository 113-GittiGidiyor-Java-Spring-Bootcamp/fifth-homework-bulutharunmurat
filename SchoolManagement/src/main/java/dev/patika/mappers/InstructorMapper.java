package dev.patika.mappers;

import dev.patika.datatransferobject.InstructorDTO;
import dev.patika.entity.Instructor;
import org.mapstruct.Mapper;

@Mapper
public interface InstructorMapper {


    public abstract Instructor mapFromInstructorDTOtoInstructor(InstructorDTO instructorDTO);
    public abstract InstructorDTO mapFromInstructortoInstructorDTO(Instructor instructor);

}
