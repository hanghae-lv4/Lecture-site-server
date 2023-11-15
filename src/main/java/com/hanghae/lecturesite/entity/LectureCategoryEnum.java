package com.hanghae.lecturesite.entity;

import lombok.Getter;

@Getter
public enum LectureCategoryEnum {
    SPRING(Category.SPRING),
    REACT(Category.REACT),
    NODE(Category.NODE);

    private final String category;

    LectureCategoryEnum(String category) {
        this.category = category;
    }

    public static class Category {
        public static final String SPRING = "SPRING";
        public static final String REACT = "REACT";
        public static final String NODE = "NODE";
    }
}