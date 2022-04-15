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
    public Boolean deleteUsers(Long id) {
        if(checkData(id).isPresent())
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

        Optional<Users> presentData = checkData(user.getUserId());
        if(presentData.isPresent())
        {
            Users newData = updateData(presentData.get(),user);
            return userRepository.save(newData);
        }
        return null;

    }

    private Optional<Users> checkData(Long id)
    {
        return userRepository.findById(id);
    }

    public Users updateData(Users presentData,Users user)
    {
        if(user.getUserName()!=null)
        {
            presentData.setUserName(user.getUserName());
        }
        if(user.getDob()!=null)
        {
            presentData.setDob(user.getDob());
        }
        if(user.getEmail()!=null)
        {
            presentData.setEmail(user.getEmail());
        }
        if(user.getPassword()!=null)
        {
            presentData.setPassword(user.getPassword());
        }
        if(user.getPhone()!=null)
        {
            presentData.setPhone(user.getPhone());
        }
        return presentData;
    }
}
