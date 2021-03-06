# Spring boot

![MAVEN][maven-url]
![JAVA][java-url]
![SPRINGBOOT][spring-url]

> Spring-boot 공부 내용 정리를 위한 repository 

[maven-url]: https://shields.io/badge/maven-v4.0.0-blue?style=for-the-badge
[java-url]: https://shields.io/badge/java-v11-BLUE?style=for-the-badge
[spring-url]: https://shields.io/badge/spring%20boot-v2.6.4-orange?style=for-the-badge

## ⚾ 목표

- [ ] Spring boot 문법 숙지
- [ ] REST 규칙을 지키는 API 작성 (서버 자원에 대한 상태 기술)

| HTTP Method | REST API                     | Description      |
|-------------|------------------------------|------------------|
| GET         | /users                       | 모든 유저 검색         |
| GET         | /users/{id}                  | 특정 유저 검색         |
| GET         | /users/{id}/posts            | 특정 유저의 모든 게시글 검색 |
| GET         | /users/{id}/posts/{post_id}  | 특정 유저의 특정 개시글 검색 |
| POST        | /users                       | 새로운 유저 생성        |
| POST        | /users/{id}/posts            | 특정 유저의 새 게시글 생성  |
| DELETE      | /users/{id}                  | 특정 유저 삭제         |
| DELETE      | /users/{id}/postsp/{post_id} | 특정 유저의 특정 게시글 삭제 |

![img_1.png](img_1.png)

### `Level0`
- rest style로 soap web service 구현
- URI에 부적절한 resource 노출 
```markdown
GET     http://server/getPosts
POST    http://server/deletePosts
GET     http://server/doSomthing
```

### `Level1`
- 적절한 URI로 resource 노출
- 부적절한 HTTP Method 사용 & 부적절한 상태코드 사용
```markdown
GET     http://server/posts
POST    http://server/posts/1   # delete post id = 1
POST    http://server/posts/1   # update post id = 1
```

### `Level2`
- Level1 + 적절한 HTTP Method 사용
```markdown
GET     http://server/posts/1
DELETE  http://server/posts/1
POST    http://server/posts/1
```

### `Level3`
- Level2 + HATEOAS 등을 통해 호출 가능한 action 명시

```mardown

❓ RESTful web service best practices

- 고객 (API 사용자) 입장의 인터페이스
- HTTP 기능을 최대한 활용
    - 적절한 HTTP Method
    - 적절한 Response Status
- URI에 민감정보 노출하지 않기
- 복수형태의 Resource URI 사용
    /user     =>  /users
    /user/1   =>  /users/1
- Resource 는 명사형태로 사용
- URI는 일관된 접근방식으로 정의한다

```



### Intellij dependency path
```bash
Drive:\Users\User\.m2\repository\org\springframework
```

### Maven Project Structure
```markdown
└─src
    ├─main # java sources
    │  ├─java # class files
    │  │  └─com
    │  │      └─example
    │  │          └─restapi
    │  └─resources # resource & environment files
    │      ├─static
    │      └─templates
    └─test # unit test code
        └─java
            └─com
                └─example
                    └─restapi
...
pom.xml # maven project settings
...
```

### `application.properties`

> spring 프로젝트 외부 설정 파일 (properties -> yml)

- key - value 형태의 값을 정의하면 애플리케이션에서 참조하여 사용함 ( properties )
- yaml 기반 구성 파일을 사용할 수 있음 ( 계층적 구성 데이터에 용이 ) - ( yml )
- 프로젝트에서 값을 참조할 때, `@value` annotation을 사용하여 값을 받아올 수 있음

## annotation

### `@Controller`
- @Component을 구체화한 어노테이션
- Spring Framework에서 이를 통해 클래스 경로 스캐닝을 하여 Controller로 사용함을 인지
- 필요한 비즈니스 로직을 호출하여 전달할 모델과 이동할 뷰 정보를 에 반환함

### `@RestController`
- Spring MVC Controller에 `@ResponseBody`가 추가된 것
- View를 갖지 않으며, REST Data를 반환하는 것이 주 목적
- (JSON/XML 형태로 객체 데이터를 반환하는것이 주 용도)

### `@RequestMapping`

```java
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping(method = RequestMethod.GET, path = "/pathname")
    public String SomeController() {
        return "some return string";
    }
}
```

- api요청 url과 handler를 매핑하는 어노테이션
- GET, POST, DELETE, PUT 등의 MethodMapping으로 대체 가능


---

## Framework & Library

### `DispathcerServlet`

> 클라이언트의 모든 요청을 한 곳으로 받아서 처리
> 
> 요청에 맞는 Handler로 요청을 전달하는 역할
> 
> Handler의 처리 결과를 Http Response 형태로 만들어서 반환

![img.png](img.png)

### `HATEOAS` *Hypermedia As the Engine Of Application State*
> 현재 리소스와 연관된 (호출 가능한) 자원 상태 정보를 제공
> 
> pom.xml 패키지 설치 필요

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-hateoas</artifactId>
</dependency>
```

### `response`
```json
{
  "id": 1,
  "name": "user1",
  "joinDate" : "2020-01-12",
  "_links": {
    "mapping_name": {
      "href": "http://localhost:8088/users"
    }
  }
}

```

### `Swagger`

> REST Web Service 설계, 빌드, 문서화 등의 업무를 지원하는 프레임워크

```xml
<dependency>
    <groupId>io.spingfox</groupId>
    <artifactId>springfox=swagger2</artifactId>
    <version>2.9.2</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.9.2</version>
</dependency>
```

```java
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2);
    }
}
```

### `JPA`
> Java Persistence API
> 
> Java ORM 기술에 대한 API 표준 명세 (RDB 사용 방식을 정의)
> 
> EntityManager를 통한 CRUD 처리
> 
> `Hibernate` JPA의 구현체 (생산성, 유지보수, 비종속성 유리)
> 
> `Spring Data JPA` JPA를 추상화한 Repository 인터페이스 제공

### [참조]
### `단축키`
- `Ctrl + Alt + V` 인스턴스를 임시 변수를 생성하여 대입 