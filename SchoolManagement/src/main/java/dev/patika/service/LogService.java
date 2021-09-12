package dev.patika.service;

import dev.patika.entity.Log;
import dev.patika.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService implements BaseService<Log> {

    private final LogRepository repository;
    @Override
    public List<Log> findAll() {
        return null;
    }

    
    @Transactional
    public Log save(Log log) {
        return repository.save(log);
    }


    @Override
    public Log findById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void deleteById(int id) {

    }

}
