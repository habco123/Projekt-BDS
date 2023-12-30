package org.but.feec.services;


import org.but.feec.api.CustomerBasicView;
import org.but.feec.api.UserCreateView;
import org.but.feec.data.CustomerRepository;

import java.util.List;

import static org.but.feec.services.Argon2FactoryService.ARGON2;

public class UserService {

    private CustomerRepository customerRepository;

    private static String username;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        UserService.username = username;
    }

    public UserService(CustomerRepository customerRepository){this.customerRepository = customerRepository; }

    public void createUser(UserCreateView userCreateView){

        char[] originalPassword = userCreateView.getPassword();
        char[] hashedPassword = hashPassword(originalPassword);
        userCreateView.setPassword(hashedPassword);

        customerRepository.createPerson(userCreateView);
    }

    public char[] hashPassword(char[] password) {
        return ARGON2.hash(10, 65536, 1, password).toCharArray();
    }
}
