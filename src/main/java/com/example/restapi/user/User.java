package com.example.restapi.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value={"password"})
@JsonFilter("UserInfo") // SimpleFilterProvider().addFilter에서 사용됨
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    @Size(min=2, message="Name을 2글자 이상 입력해주십시오.") // details message에 삽입됨
    private String name;
    @Past
    private Date joinDate;

//    @JsonIgnore
    private String password;
//    @JsonIgnore
    private String ssn;
}
