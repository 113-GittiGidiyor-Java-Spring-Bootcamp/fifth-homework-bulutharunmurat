package dev.patika.exceptions;

public class InstructorIsAlreadyExistException extends RuntimeException{
    public InstructorIsAlreadyExistException(String message){
        super(message);
    }

}
