package com.bankbazaar.core.manager;

import com.bankbazaar.core.model.UserEntity;
import com.bankbazaar.core.repository.UserRepository;
import com.bankbazaar.core.security.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserManager implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    /**
     * Check if user exists by email
     * @param email
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new LoginUserDetails(user.get());
    }

    /**
     *  Insert to Users table
     * @param data
     */
    public UserEntity insertUsers(UserEntity data)
    {
        Optional<UserEntity> user = userRepository.findByEmail(data.getEmail());
        if(user.isEmpty())
        {
            data.setPassword(BCrypt.hashpw(data.getPassword(), BCrypt.gensalt()));
            return userRepository.save(data);
        }
        else
        {
            return null;
        }
    }
    /**
     *  Update to Users table
     * @param data
     */
    public UserEntity updateUsers(UserEntity data)
    {
        Optional<UserEntity> presentData = exists(data.getUserId());
        Optional<UserEntity> user = userRepository.findByEmail(data.getEmail());
        if (user.isEmpty()) {
            UserEntity newData = updateData(presentData.get(), data);
            return userRepository.save(newData);
        }
        return null;
    }
    /**
     * Get record by ID
     */
    public Optional<UserEntity> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    private Optional<UserEntity> exists(Long id)
    {
        return userRepository.findById(id);
    }

    private UserEntity updateData(UserEntity presentData, UserEntity user)
    {
        UserEntity newData = new UserEntity();
        newData.setUserId(presentData.getUserId());
        if(!user.getUserName().isBlank())
        {
            newData.setUserName(user.getUserName());
        }
        else{newData.setUserName(presentData.getUserName());}
        if(user.getDob()!=null)
        {
            newData.setDob(user.getDob());
        }
        else{newData.setDob(presentData.getDob());}
        if(!user.getEmail().isBlank())
        {
            newData.setEmail(user.getEmail());
        }
        else{newData.setEmail(presentData.getEmail());}
        if(!user.getPassword().isBlank())
        {
            newData.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        }
        else{newData.setPassword(presentData.getPassword());}
        if(!user.getPhone().isBlank())
        {
            newData.setPhone(user.getPhone());
        }
        else {newData.setPhone(presentData.getPhone());}

        return newData;
    }
}
