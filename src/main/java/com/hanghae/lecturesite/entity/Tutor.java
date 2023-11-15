package com.hanghae.lecturesite.entity;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "tutor")
@NoArgsConstructor
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Long career;

    @Column(nullable = false)
    private String company;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String intro;

    @OneToMany(mappedBy="tutor", orphanRemoval = true)
    private List<Lecture> lectureList = new ArrayList<>();

}
