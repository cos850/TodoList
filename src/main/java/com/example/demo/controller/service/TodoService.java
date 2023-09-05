package com.example.demo.controller.service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TodoService {

    private final TodoRepository repository;

    @Autowired
    TodoService(TodoRepository repository){
        this.repository = repository;
    }

    // 1. 검증, 2. 저장, 3. 저장한 엔티티가 포함된 새 리스트 반환
    public List<TodoEntity> create(final TodoEntity entity){
        validate(entity);
        repository.save(entity);
        return repository.findByUserId(entity.getUserId());
    }

    private void validate(final TodoEntity entity){
        if(entity == null){
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Enitity cannot be null.");
        }

        if(entity.getUserId() == null){
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }

    public List<TodoEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }
}
