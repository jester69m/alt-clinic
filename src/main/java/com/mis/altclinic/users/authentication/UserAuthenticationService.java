package com.mis.altclinic.users.authentication;

import com.mis.altclinic.users.User;
import org.springframework.security.core.AuthenticationException;

public interface UserAuthenticationService {

    User authenticate(String username, String password) throws AuthenticationException;
}
