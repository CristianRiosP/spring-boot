package com.fundamentos.springboot.fundamentos.service;

import com.fundamentos.springboot.fundamentos.entity.User;
import com.fundamentos.springboot.fundamentos.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final Log log = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Transactional // Rollback de transacciones en caso de error
    public void saveTransactional(List<User> users){
        users.stream()
                .peek(user -> log.info("user Insert"+ user))
                .forEach(userRepository::save);//metodo por referencia

                //.forEach(user -> userRepository.save(user));
    }
    public List<User> getListUsers(){
        return  userRepository.findAll();
    }
}
