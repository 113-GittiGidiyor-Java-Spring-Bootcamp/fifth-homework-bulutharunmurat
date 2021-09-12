package dev.patika.service;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseService <T>{

    List<T> findAll();
    T findById(int id);
    void deleteById(int id);
}
