package com.bao.graduationProject;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component  //表示Person类被spring识别
@ConfigurationProperties(prefix = "person")    //上方红条无所谓
public class Person {

    private String name;
    private int other_properties;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOther_properties() {
        return other_properties;
    }

    public void setOther_properties(int age) {
        this.other_properties = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + other_properties +
                '}';
    }
}
