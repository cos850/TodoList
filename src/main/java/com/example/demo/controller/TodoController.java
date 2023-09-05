package com.example.demo.controller;

import com.example.demo.controller.service.TodoService;
import com.example.demo.dto.ResponseDTO;
import com.example.demo.dto.TodoDTO;
import com.example.demo.model.TodoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("todo")
public class TodoController {

    private final TodoService service;

    @Autowired
    TodoController(TodoService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO dto){
        try{
            String temporaryUserId = "temporary-user";  // TODO: 로그인 구현 후 변경
            TodoEntity entity = TodoDTO.toEntity(dto);
            entity.setId(null);
            entity.setUserId(temporaryUserId);

            List<TodoDTO> dtos = service.create(entity).stream().map(TodoDTO::new).collect(Collectors.toList());

            return ResponseEntity.ok().body(ResponseDTO.<TodoDTO>builder().data(dtos).build());
        }catch (Exception e){
            // 오류시 error 필드 설정 후 반환
            return ResponseEntity.badRequest().body(ResponseDTO.<TodoDTO>builder().error(e.getMessage()).build());
        }
    }

    @GetMapping
    public ResponseEntity<?> retrieveTodoList(){
        String temporaryUserId = "temporary-user";

        List<TodoEntity> entities = service.retrieve(temporaryUserId);

        List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

        ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

        return ResponseEntity.ok().body(response);
    }
}
