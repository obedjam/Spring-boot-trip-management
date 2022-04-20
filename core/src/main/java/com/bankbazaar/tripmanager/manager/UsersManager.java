package com.bankbazaar.tripmanager.manager;

import com.bankbazaar.tripmanager.model.Users;
import com.bankbazaar.tripmanager.repository.UsersRepository;
import com.bankbazaar.tripmanager.security.LoginUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        Optional<Users> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new LoginUserDetails(user.get());
    }

    /**
     * Insert to Users table
     * @param data
     */
    public Users saveUsers(Users data)
    {
        Optional<Users> presentData = exists(data.getUserId());
        Optional<Users> user = userRepository.findByEmail(data.getEmail());
        if (user.isEmpty()) {
            updateData(presentData.get(), data);
            return userRepository.save(presentData.get());
        }
        return null;
    }
    /**
     * Get record by ID
     */
    public Optional<Users> getUsersById(String email) {
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

    private Optional<Users> exists(Long id)
    {
        return userRepository.findById(id);
    }

    public void updateData(Users presentData,Users user)
    {
        presentData.setLastModifiedTime(LocalDate.now());
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
