package com.bankbazaar.service.service;

import com.bankbazaar.core.repository.TripActivityRepository;
import com.bankbazaar.core.repository.TripRepository;
import com.bankbazaar.core.repository.TripUserMapRepository;
import com.bankbazaar.core.repository.UserRepository;
import com.bankbazaar.dto.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
class UserServiceTest {

    @Autowired
    UserService service;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private TripUserMapRepository tripUserMapRepository;
    @Autowired
    private TripActivityRepository tripActivityRepository;


    @BeforeEach
    void setUp() throws ParseException {
        log.info("Executing cleanup");
        tripUserMapRepository.deleteAll();
        tripActivityRepository.deleteAll();
        userRepository.deleteAll();
        tripRepository.deleteAll();

        UserDto userDto =new UserDto();
        userDto.setUserName("name");
        userDto.setPassword("password");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1998-05-23");
        userDto.setDob(date);
        userDto.setEmail("name@mail.com");
        userDto.setPhone("1234567891");
        service.addUser(userDto);
    }

    @Test
    void addUser(){
        assertEquals(userRepository.count(),1);
    }

    @Test
    void getUserDetails() throws ParseException {

        UserDto response = service.getUserDetails("name@mail.com");
        assertNotNull(response);
    }

    @Test
    void updateUser() throws ParseException {

        UserDto user =  service.getUserDetails("name@mail.com");
        user.setUserName("username");
        UserDto response = service.updateUser(user);

        assertEquals(userRepository.count(),1);
        assertEquals(response.getUserName(), user.getUserName());
    }
}