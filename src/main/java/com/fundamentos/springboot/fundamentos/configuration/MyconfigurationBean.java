package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.bean.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyconfigurationBean {
    @Bean
    public MyBEan beanOpration(){
        return new MyBeanImplement2();
    }
    @Bean
    public MyOperation beanOprationSum(){
        return new MyOperationImplement();
    }
    @Bean
    public MyBeanWhithDependency beanOprationSumDependency(MyOperation myOperation){
        return new MyBeanWithDependencyImplement(myOperation);
    }
}
