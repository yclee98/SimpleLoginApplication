package com.example.simplelogin;

import com.example.simplelogin.exception.AuthenticationException;
import com.example.simplelogin.model.User.User;
import com.example.simplelogin.repository.UserRepositoryInterface;
import com.example.simplelogin.service.imple.UserService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServicesTest {

}
