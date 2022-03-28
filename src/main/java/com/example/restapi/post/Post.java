package com.example.restapi.post;

import com.example.restapi.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue
    private Integer id;

    private String description;


    // User : Post => 1 : N
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore // 순환참조로 인해 데이터 과부화 방지
    private User user;
}
