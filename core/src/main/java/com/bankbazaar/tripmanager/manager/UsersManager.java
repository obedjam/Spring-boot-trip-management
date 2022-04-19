package com.bankbazaar.tripmanager.manager;

import com.bankbazaar.tripmanager.model.Users;
import com.bankbazaar.tripmanager.repository.UsersRepository;
import com.bankbazaar.tripmanager.security.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        Users user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new LoginUserDetails(user);
    }

    /**
     * Insert to Users table
     * @param data
     */
    public Users saveUsers(Users data)
    {
            Optional<Users> presentData = exists(data.getUserId());
            if (presentData.isPresent()) {
                Users newData = updateData(presentData.get(), data);
                return userRepository.save(newData);
            }
            return null;
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
        if(exists(id).isPresent())
        {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private Optional<Users> exists(Long id)
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
