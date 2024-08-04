package com.java.biao.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = 6497405088845065607L;

    private String name;
    private Integer age;
    private Integer score;
    private Integer id;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", id=" + id +
                '}';
    }
}
