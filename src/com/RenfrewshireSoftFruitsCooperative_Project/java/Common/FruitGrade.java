package com.RenfrewshireSoftFruitsCooperative_Project.java.Common;

public enum FruitGrade {

    GRADE_A("GRADE A"),
    GRADE_B("GRADE B"),
    GRADE_C("GRADE C"),
    REJECTED("REJECTED");

    private String grade;

    FruitGrade(String grade) {
        this.grade = grade;
    }

    public String toString() {
        return this.grade;
    }
}
