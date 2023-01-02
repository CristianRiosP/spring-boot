package com.fundamentos.springboot.fundamentos.configuration;

import com.fundamentos.springboot.fundamentos.caseUse.getUser;
import com.fundamentos.springboot.fundamentos.caseUse.getUserImplement;
import com.fundamentos.springboot.fundamentos.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class caseUseConfiguration {

    @Bean
    getUser getUser(UserService userService){
        return new getUserImplement(userService);
    }
}
