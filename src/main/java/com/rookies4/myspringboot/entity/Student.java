package com.rookies4.myspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Student
@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {

    //PK(Sequential Value : Primary Key)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    //이름
    @Column(nullable = false)
    private String name;

    //학번
    @Column(nullable = false, unique = true)
    private String studentNumber;

    //
    private StudentDetail studentDetail;
}