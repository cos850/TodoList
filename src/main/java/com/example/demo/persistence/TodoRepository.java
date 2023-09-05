package com.example.demo.persistence;

import com.example.demo.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  TodoRepository가 interface임에도 작동하는 원리
 *  : AOP (Aspect Oriented Programming)
 *  스프링이 Methodinterceptor 라는 AOP를 사용해 JpaRepository의 메서드를 호출할 때마다
 *  메서드 콜을 가로채간 후, 메서드의 이름을 확인하고 이를 기반으로 쿼리를 작성함
 */
@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {

    /** 기본제공 외 필요한 쿼리 정의 */

    // 1. 메서드명 쿼리 형식
    // 스프링이 메서드명을 파싱해서 "SELECT * FROM TodoRepository WHERE userId = '{userId}'" 와 같은 쿼리 실행
    List<TodoEntity> findByUserId(String userId);

    // 2. @Query
    // 메서드명 쿼리 형식보다 복잡한 쿼리가 필요한 경우 사용
    //    @Query("select * from TodoEntity t where t.userId = ?1")    // 1: 매개변수 순서
    //    List<TodoEntity> findByUserId(String userId);
}
