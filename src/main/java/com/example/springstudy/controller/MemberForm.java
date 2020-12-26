package com.example.springstudy.controller;

public class MemberForm {
    private String name; // html의 name을 통해서 spring이 setter를 통해서 이름을 넣어준다!

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
