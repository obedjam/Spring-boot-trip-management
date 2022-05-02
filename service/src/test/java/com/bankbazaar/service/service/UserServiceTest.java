package com.bankbazaar.service.service;

import com.bankbazaar.core.repository.UserRepository;
import com.bankbazaar.dto.model.UserDto;
import com.bankbazaar.service.ServiceModuleTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public
class UserServiceTest extends ServiceModuleTest {

    @Autowired
    UserService service;

    @Autowired
    private UserRepository userRepository;

    @Test
    void userService() throws ParseException {
        assertEquals(userRepository.count(),0);
        UserDto userDto =new UserDto();
        userDto.setUserName("name");
        userDto.setPassword("password");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1998-05-23");
        userDto.setDob(date);
        userDto.setEmail("name@mail.com");
        userDto.setPhone("1234567891");
        UserDto newUser = service.addUser(userDto);

        assertEquals(1, userRepository.count());
        validate(userDto, newUser);

        UserDto user = service.getUserDetails(newUser.getEmail());
        validate(userDto, user);

        UserDto loggedInUser = service.loggedInUserDetails(newUser.getUserId());
        validate(userDto, loggedInUser);

        UserDto nullUser = service.getUserDetails("null@email");
        assertNull(nullUser);

        UserDto nullLoggedInUser = service.loggedInUserDetails(70L);
        assertNull(nullLoggedInUser);

        user.setUserName("username");
        UserDto updatedUser = service.updateUser(user);

        assertEquals(1, userRepository.count());
        validate(user, updatedUser);
    }

    void validate(UserDto originalUser, UserDto validateUser)
    {
        assertNotNull(validateUser);
        assertNotNull(validateUser.getUserId());
        assertEquals(originalUser.getUserName(), validateUser.getUserName());
        assertNotNull(validateUser.getPassword());
        assertNotEquals(originalUser.getPassword(),validateUser.getPassword());
        assertEquals(originalUser.getDob(), validateUser.getDob());
        assertEquals(originalUser.getEmail(), validateUser.getEmail());
        assertEquals(originalUser.getPhone(), validateUser.getPhone());
    }
}