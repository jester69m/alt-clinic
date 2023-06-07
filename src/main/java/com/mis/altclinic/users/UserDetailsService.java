package com.mis.altclinic.users;

import com.mis.altclinic.consumers.ConsumerRepository;
import com.mis.altclinic.doctors.DoctorRepository;
import com.mis.altclinic.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;
    private final ConsumerRepository consumerRepository;
    private final DoctorRepository doctorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByEmail(email);
        if(user.getUsername() != null){
            return user;
        }
        user = consumerRepository.findByEmail(email);
        if(user.getUsername() != null){
            return user;
        }
        return doctorRepository.findByEmail(email);
    }
}
