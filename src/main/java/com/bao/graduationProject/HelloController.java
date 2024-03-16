package com.bao.graduationProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${name}")
    private String name;

    @Value("${person.name}")
    private String name2;

    @Value("${address[0]}")
    private String address1;

    @Autowired
    private Environment environment;

    @Autowired
    private Person person;

    @RequestMapping("/hello")
    public Person hello(){
//        return this.name + " " + this.name2 + " " + this.address1;

//        return this.environment.getProperty("person.name");

        return this.person;
    }
}
