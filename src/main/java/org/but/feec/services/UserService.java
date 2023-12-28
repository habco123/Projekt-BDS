package org.but.feec.services;


import org.but.feec.api.UserCreateView;
import org.but.feec.data.CustomerRepository;

import static org.but.feec.services.Argon2FactoryService.ARGON2;

public class UserService {

    private CustomerRepository customerRepository;

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
