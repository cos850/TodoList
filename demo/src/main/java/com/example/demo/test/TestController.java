package com.example.demo.test;

import com.example.demo.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController     // @Controller + @ResponseBody (@ResponseBody: 해당 클래스의 메서드가 반환하는 것은 웹 서비스의 ResponseBody임을 명시)
@RequestMapping("test")
public class TestController {

    /**  요청 수신 */
    // 1. PathVariable (uri 경로 값을 변수로)
    @GetMapping("/req/{id}")
    public String testPathVariable(@PathVariable(required = false) String id){
        return "Hello World " + id;
    }

    // 2. RequestParam (uri 뒤 queryString 값을 변수로)
    @GetMapping("/req/reqparam")
    public String testRequestParam(@RequestParam(required = false) String id){
        return "Hello World " + id;
    }

    // 3. RequestBody (object와 같이 복잡한 자료형일 경우 사용)
    // 스프링이 json 데이터 수신받으면 object로 변환함 = 직렬화 (serialization)
    @GetMapping("/req/reqbody")
    public String testRequestBody(@RequestBody TestRequestBodyDto dto){
        return "Hello World " + dto.getId() + "!! \n" + dto.getMessage();
    }


    /** 응답 송신 */
    // ResponseBody (클래스의 @RestController 참조)

    // 4. ResponseDTO 반환
    // 스프링이 object 데이터를 json으로 변환해 송신함 = 역직렬화 (deserialization)
    @GetMapping("/res/responsebody")
    public ResponseDTO<String> testResponseDto(){
        List<String> data = new ArrayList<>();
        data.add("Hello World !! I'm ResponseDTO~~~~");
        return ResponseDTO.<String>builder().data(data).build();
    }

    // 5. ResponseEntity 반환
    // Http Response Body + status + header 조작 가능
    @GetMapping("/res/responseentity")
    public ResponseEntity<?> testResponseEntity(){
        List<String> data = new ArrayList<>();
        data.add("Hello World !! I'm ResponseDTO~~~~");
        ResponseDTO dto = ResponseDTO.<String>builder().data(data).build();

        return ResponseEntity.badRequest().body(dto);   // 400
    }

}
