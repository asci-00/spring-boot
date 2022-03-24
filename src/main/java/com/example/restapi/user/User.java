package com.example.restapi.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private Integer id;
    @Size(min=2, message="Name을 2글자 이상 입력해주십시오.") // details message에 삽입됨
    private String name;
    @Past
    private Date joinDate;
}
