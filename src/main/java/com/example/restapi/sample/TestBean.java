package com.example.restapi.sample;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // lombok : setter, getter, constructor 등의 기능들을 자동으로 생성해줌
@AllArgsConstructor // lombok: constructor가 모든 attribute를 인자로 받도록 자동으로 생성
@NoArgsConstructor // lombok: 아무 인자도 받지 않는 생성자
public class TestBean {
    private String message;
}
