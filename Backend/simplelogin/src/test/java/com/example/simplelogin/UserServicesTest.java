package com.example.simplelogin;

import com.example.simplelogin.Exception.AuthenticationException;
import com.example.simplelogin.Model.User.User;
import com.example.simplelogin.Model.User.UserServices;
import com.example.simplelogin.Repository.UserRepositoryInterface;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServicesTest {
    @Autowired
    private UserServices userServices;
    @Autowired
    private UserRepositoryInterface userRepository;


    @Test
    public void contextLoads() throws Exception{
        assertThat(userServices).isNotNull(); 
    }

    @Test 
    public void registerUserNullTest() {
        assertThrows(AuthenticationException.class, ()->{
            userServices.registerUser(null,null,null,null);
        });
    }

    @Test
    public void registerUserValidTest(){
        try{
            userServices.deleteUser("test1123");
        }
        catch(AuthenticationException e){
            
        }

        userServices.registerUser("test1", "test1123", "1234", "admin");
        User u = userRepository.findByUsername("test1123").get();
        
        assert "test1".equals(u.getName());
    }

    @Test
    public void tokenTest(){
        User u = userRepository.findByUsername("test1123").get();
        // Map<String, String> token = JWTService.generateJWTToken(u);
        // System.out.println(token.get("token"));
        // assert 1 == 1;
    }
}
