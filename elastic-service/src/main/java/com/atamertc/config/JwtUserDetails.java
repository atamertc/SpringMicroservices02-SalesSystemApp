package com.atamertc.config;

import com.atamertc.repository.entity.User;
import com.atamertc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtUserDetails implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByAuthid(Long authid) {
        Optional<User> user = userService.findByAuthid(authid);
        if (user.isEmpty()) {
            System.out.println("User bos geldi.");
            return null;
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("Admin"));
        authorities.add(new SimpleGrantedAuthority("VIP"));
        authorities.add(new SimpleGrantedAuthority("User"));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.get().getUsername())
                .password(user.get().getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .authorities(authorities)
                .build();

    }

}
