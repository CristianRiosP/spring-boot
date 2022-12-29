package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanWhithPropertiesImplement implements MyBeanWhithProperties{

    private  String name;
    private String apellido;

    public MyBeanWhithPropertiesImplement(String name, String apellido) {
        this.name = name;
        this.apellido = apellido;
    }

    @Override
    public String function() {
        return name + "-"+ apellido;
    }
}
