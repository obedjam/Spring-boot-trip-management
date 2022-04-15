package com.bankbazaar.tripmanager.manager;

import com.bankbazaar.tripmanager.model.Users;
import com.bankbazaar.tripmanager.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UsersManager {

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
    /**
     * Get record by ID
     */
    public Optional<Users> getUsersById(Long id) {
        return userRepository.findById(id);
    }
    /**
     * Delete record by id
     * @param id
     */
    public String deleteUsers(Long id) {
        if(userRepository.findById(id).isEmpty())
        {
            return null;
        }
        userRepository.deleteById(id);
        return "Users "+id+" has been removed" ;
    }
    /**
     * Update record
     */
    public Users updateUsers(Users user) {
        if(userRepository.findById(user.getUserId()).isEmpty())
        {
            return null;
        }
        return userRepository.save(user);
    }
}
