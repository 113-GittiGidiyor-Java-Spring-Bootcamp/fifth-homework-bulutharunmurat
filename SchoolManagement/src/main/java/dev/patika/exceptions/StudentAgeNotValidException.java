package dev.patika.exceptions;

public class StudentAgeNotValidException extends RuntimeException{
    public StudentAgeNotValidException(String message){
        super(message);
    }
}
