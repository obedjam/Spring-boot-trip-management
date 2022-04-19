package com.bankbazaar.tripmanager.manager;

import com.bankbazaar.tripmanager.model.Users;
import com.bankbazaar.tripmanager.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterManager {

    @Autowired
    private UsersRepository userRepository;
    /**
     * Insert to Users table
     * @param data
     */
    public Users saveUsers(Users data)
    {
        return userRepository.save(data);
    }

}
