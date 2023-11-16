package com.hanghae.lecturesite.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@Entity
@Table(name = "lecture")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String intro;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private LectureCategoryEnum category;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime regDate;

    @OneToMany(mappedBy = "lecture")
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne
    private Tutor tutor;

    private Integer like;
}
