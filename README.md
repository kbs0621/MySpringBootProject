### [Study 2-4] Spring Boot와 JPA(Java Persistence API) 활용

* Student과 StudentDetail 1:1 (OneToOne) Entity 연관관계
    * FetchType.LAZY vs FetchType.EAGER
    * @JoinColumn, mappedBy
    * 연관관계의 주인(owner와 종속(non-owner))
        * Owner(BookDetail), Non-Owner(Book)
* DTO
* Controller
* Service
* Repository

### [Study 2-5] SpringBoot 와 JPA(Java Persistence API) 활용

* Student 와 Department 1:N (OneToMany) 엔티티 연관관계

* N+1 문제 해결
  * 성능개선
  * N+1 문제 해결
  * HibernateModule 사용하여 BatchSize 설정하기

### [Study 2-7] SpringBoot와 JWT
* Basic Authentication(인증) 과 Authorization(권한)
* JWT(Json Web Token) 토큰 인증