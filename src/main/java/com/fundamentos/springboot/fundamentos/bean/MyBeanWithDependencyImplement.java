package com.fundamentos.springboot.fundamentos.bean;

public class MyBeanWithDependencyImplement implements MyBeanWhithDependency{
    private MyOperation myOperation;

    public MyBeanWithDependencyImplement(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public void printWithDependency() {
        int numero= 5;
        System.out.println(myOperation.sum(numero));
        System.out.println("Hola desde la implemtancion de un bean con dependencia");
    }
}
