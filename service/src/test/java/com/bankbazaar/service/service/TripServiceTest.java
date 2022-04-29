package com.bankbazaar.service.service;

import com.bankbazaar.core.repository.TripActivityRepository;
import com.bankbazaar.core.repository.TripRepository;
import com.bankbazaar.core.repository.TripUserMapRepository;
import com.bankbazaar.core.repository.UserRepository;
import com.bankbazaar.dto.model.TripDto;
import com.bankbazaar.dto.model.UserDto;
import lombok.extern.slf4j.Slf4j;
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
class TripServiceTest {

    @Autowired
    TripService service;
    @Autowired
    UserService userService;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private TripUserMapRepository tripUserMapRepository;
    @Autowired
    private TripActivityRepository tripActivityRepository;

    @Test
    void addTrip() throws ParseException {
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
        UserDto user = userService.addUser(userDto);


        TripDto tripDto =new TripDto();
        tripDto.setTripName("name");
        tripDto.setTripDescription("trip");
        tripDto.setDestination("trip");
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("1998-05-23");
        tripDto.setStartDate(startDate);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("1998-05-23");
        tripDto.setEndDate(endDate);
        service.addTrip(tripDto, user.getUserId());

        assertEquals(tripRepository.count(),1);

    }
}