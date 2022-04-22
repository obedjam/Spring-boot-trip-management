package com.bankbazaar.core.manager;

import com.bankbazaar.core.model.UserEntity;
import com.bankbazaar.core.repository.UsersRepository;
import com.bankbazaar.core.security.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UsersManager implements UserDetailsService {

    @Autowired
    private UsersRepository userRepository;
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
            /**
             * Here we update the object inside the optional presentData
             * the object consists of the current user data
             * @data has the updated details.
             * updateData method updates the presentData with the new details from data
             * then we save the updated data in presentData to DB
             */
            updateData(presentData.get(), data);
            return userRepository.save(presentData.get());
        }
        return null;
    }
    /**
     * Get record by ID
     */
    public Optional<UserEntity> getUsersById(String email) {
        return userRepository.findByEmail(email);
    }
    /**
     * Delete record by id
     * @param id
     */
    public Boolean deleteUsers(Long id) {
        if(exists(id).isPresent())
        {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Optional<UserEntity> exists(Long id)
    {
        return userRepository.findById(id);
    }

    public void updateData(UserEntity presentData, UserEntity user)
    {
        if(!user.getUserName().isBlank())
        {
            presentData.setUserName(user.getUserName());
        }
        if(user.getDob()!=null)
        {
            presentData.setDob(user.getDob());
        }
        if(!user.getEmail().isBlank())
        {
            presentData.setEmail(user.getEmail());
        }
        if(!user.getPassword().isBlank())
        {
            presentData.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        }
        if(!user.getPhone().isBlank())
        {
            presentData.setPhone(user.getPhone());
        }
    }
}
