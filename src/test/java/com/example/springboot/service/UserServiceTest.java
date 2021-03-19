package com.example.springboot.service;

import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    BCryptPasswordEncoder mockEncoder;
    @Mock
    UserMapper mockMapper;
    @InjectMocks
    UserService userService;

    @Test
    public void testSave(){
        //调用userService
        //验证userService将请求转发给了userMapper

        when(mockEncoder.encode("myPassword")).thenReturn("myEncodedPassword");
        userService.save("myUser","myPassword");
        verify(mockMapper).save("myUser","myEncodedPassword");
    }
    @Test
    public void testGetUserByUsername(){
        userService.getUserByUsername("myUser");
        verify(mockMapper).findUserByUsername("myUser");

    }
    @Test
    public void throwExceptionWhenUserNotFound(){
        assertThrows(
                UsernameNotFoundException.class,
                () -> userService.loadUserByUsername("myUser"));

    }
    @Test
    public void returnUserDetailsWhenUserFound(){
        when(mockMapper.findUserByUsername("myUser"))
                .thenReturn(new User(123,"myUser","myEncodedPassword"));
        UserDetails userDetails = userService.loadUserByUsername("myUser");

        Assertions.assertEquals("myUser",userDetails.getUsername());
        Assertions.assertEquals("myEncodedPassword",userDetails.getPassword());
    }

}