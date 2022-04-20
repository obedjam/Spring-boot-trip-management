package com.bankbazaar.tripmanager.manager;

import com.bankbazaar.tripmanager.model.Users;
import com.bankbazaar.tripmanager.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

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
        Optional<Users> user = userRepository.findByEmail(data.getEmail());
        if(user.isEmpty())
        {
            data.setCreatedTime(LocalDate.now());
            data.setLastModifiedTime(LocalDate.now());
            data.setPassword(BCrypt.hashpw(data.getPassword(), BCrypt.gensalt()));
            return userRepository.save(data);
        }
        else
        {
            return null;
        }
    }


}
