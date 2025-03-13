package com.example.projectforwork.config;

import com.example.projectforwork.config.users_config.Searching;
import com.example.projectforwork.entity.AbstractUserEntity;
import com.example.projectforwork.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final List<Searching> users;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AbstractUserEntity abstractUser = users.stream()
                .map(user -> user.findUserByMail(username))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst()
                .orElseThrow(UserNotFoundException::new);

        return new CustomUserDetails(abstractUser);
    }
}
