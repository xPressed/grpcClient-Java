package ru.xpressed.grpcclientjava.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.xpressed.grpcclientjava.entity.User;
import ru.xpressed.grpcclientjava.server.UserRequest;

@Service
public class SecurityUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] data = UserRequest.get(username);
        return new User(data[0], data[1], data[1]);
    }
}
