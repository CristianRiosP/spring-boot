package com.fundamentos.springboot.fundamentos.component;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwo implements ComponentDependency{

    @Override
    public void saludar() {
        System.out.println("Hola gente desde el two");
    }
}
