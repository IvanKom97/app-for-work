package com.example.projectforwork.unit;

import com.example.projectforwork.config.CustomUserDetailsService;
import com.example.projectforwork.config.users_config.Searching;
import com.example.projectforwork.entity.AbstractUserEntity;
import com.example.projectforwork.exceptions.UserNotFoundException;
import com.example.projectforwork.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConfigCustomUserDetailsTest {
    @Mock
    private Searching searching;
    private CustomUserDetailsService userDetailsService;

    @BeforeEach
    public void setList() {
        List<Searching> list = new ArrayList<>();
        list.add(searching);
        userDetailsService = new CustomUserDetailsService(list);
    }

    @Test
    public void loadByUsernameTest() {
        AbstractUserEntity user = TestUtils.abstractUser();
        String username = "iv@mail.ru";
        when(searching.findUserByMail(username)).thenReturn(Optional.of(user));
        UserDetails userDetails =  userDetailsService.loadUserByUsername(username);
        assertEquals(userDetails.getUsername(), username);
        Mockito.verify(searching, times(1)).findUserByMail(username);
    }

    @Test
    public void throwExceptionLoadByUsernameTest() {
        String username = "username";
        when(searching.findUserByMail(username)).thenThrow(UserNotFoundException.class);
        Assertions.assertThrows(UserNotFoundException.class,
                () -> userDetailsService.loadUserByUsername(username));
    }
}
