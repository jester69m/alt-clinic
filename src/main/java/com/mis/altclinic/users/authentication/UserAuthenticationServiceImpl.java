package com.mis.altclinic.users.authentication;

import com.mis.altclinic.users.User;
import com.mis.altclinic.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User authenticate(String username, String password) throws AuthenticationException {

        Optional<User> find = userRepository.findByEmail(username);
        if(find.isEmpty()){
            throw new AuthenticationException("User not found") {
            };
        }
        String storedPassword = find.get().getPassword();
        if(!bCryptPasswordEncoder.matches(password, storedPassword)){
            throw new AuthenticationException("Incorrect password") {
            };
        }
        return User.builder()
                .email(find.get().getEmail())
                .password(storedPassword)
                .role(find.get().getRole())
                .build();
    }
}
