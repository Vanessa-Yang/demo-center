package org.example.biz.model;

import lombok.Data;

/**
 * @Description
 * @Author VanessaYang
 * @Date: 2023/9/7 0007 9:44
 **/
@Data
public class Person {

    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
