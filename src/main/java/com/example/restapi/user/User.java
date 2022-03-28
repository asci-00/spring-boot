package com.example.restapi.user;

import com.example.restapi.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
// @JsonIgnoreProperties(value={"password"})
// @JsonFilter("UserInfo") // SimpleFilterProvider().addFilter에서 사용됨
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

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(int id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
