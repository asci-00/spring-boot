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