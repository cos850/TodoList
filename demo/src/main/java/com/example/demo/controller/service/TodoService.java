package com.example.demo.controller.service;

import com.example.demo.model.TodoEntity;
import com.example.demo.persistence.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    // 1. 검증, 2. 조회, 3. 수정, 4. 저장, 5. 저장한 엔티티가 포함된 새 리스트 반환
    public List<TodoEntity> update(final TodoEntity entity){
        validate(entity);

        /**
         * [optional vs null]
         * https://stackoverflow.com/questions/9561295/whats-the-point-of-guavas-optional-class
         * 관련 서적: 이펙티브자바 3/E p331. 아이템55 옵셔널 반환은 신중히 하라
         */
        final Optional<TodoEntity> original = repository.findById(entity.getId());

        // ifPresent : 값이 있을 경우 전달한 함수를 수행함
        original.ifPresent(todo->{
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            repository.save(todo);
        });

        return retrieve(entity.getUserId());
    }

    // 1. 검증, 2. 삭제, 3. 삭제한 엔티티가 없는 새 리스트 반환
    public List<TodoEntity> delete(final TodoEntity entity){
        validate(entity);

        try{
            repository.delete(entity);
        }catch (Exception e){
            log.error("error deleting entity", e);
            throw new RuntimeException("error deleting entity " + entity.getId());  // 내부 로직 캡슐화
        }

        return retrieve(entity.getUserId());
    }
}
