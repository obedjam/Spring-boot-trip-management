package com.bankbazaar.tripmanager.manager;

import com.bankbazaar.tripmanager.model.TripUserCompositeKey;
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
    public Boolean deleteUsers(Long id) {
        if(CheckData(id).isPresent())
        {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    /**
     * Update record
     */
    public Users updateUsers(Users user) {

        Users presentData = CheckData(user.getUserId()).orElse(null);
        if(presentData!=null)
        {
            presentData.updateData(user);
            return userRepository.save(presentData);
        }
        return null;

    }

    private Optional<Users> CheckData(Long id)
    {
        return userRepository.findById(id);
    }
}
