package com.bankbazaar.service.service;

import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.core.model.UserRole;
import com.bankbazaar.core.repository.TripActivityRepository;
import com.bankbazaar.core.repository.TripRepository;
import com.bankbazaar.core.repository.TripUserMapRepository;
import com.bankbazaar.core.repository.UserRepository;
import com.bankbazaar.dto.model.TripDto;
import com.bankbazaar.dto.model.TripUserMapDto;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
class TripUserMapServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    TripService tripService;

    @Autowired
    TripUserMapService service;

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
        UserDto user = userService.addUser(userDto);

        TripDto tripDto =new TripDto();
        tripDto.setTripName("name");
        tripDto.setTripDescription("trip");
        tripDto.setDestination("trip");
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("1998-05-23");
        tripDto.setStartDate(startDate);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("1998-05-23");
        tripDto.setEndDate(endDate);
        tripService.addTrip(tripDto, user.getUserId());
    }

    @Test
    void addMember() throws ParseException {
        UserDto userDto =new UserDto();
        userDto.setUserName("name");
        userDto.setPassword("password");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1998-05-23");
        userDto.setDob(date);
        userDto.setEmail("name2@mail.com");
        userDto.setPhone("1234567891");
        UserDto user = userService.addUser(userDto);

        List<TripUserMapEntity> tripUserMap = tripUserMapRepository.findAll();
        service.addMember(tripUserMap.get(0).getTripId(),user.getUserId());
        assertEquals(tripUserMapRepository.count(),2);

    }

    @Test
    void deleteMember() throws ParseException {
        UserDto userDto =new UserDto();
        userDto.setUserName("name");
        userDto.setPassword("password");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1998-05-23");
        userDto.setDob(date);
        userDto.setEmail("name2@mail.com");
        userDto.setPhone("1234567891");
        UserDto user = userService.addUser(userDto);

        List<TripUserMapEntity> tripUserMap = tripUserMapRepository.findAll();
        service.addMember(tripUserMap.get(0).getTripId(),user.getUserId());
        Boolean response = service.deleteMember(tripUserMap.get(0).getTripId(),user.getUserId());
        assertTrue(response);
    }

    @Test
    void updateMember() throws ParseException {
        UserDto userDto =new UserDto();
        userDto.setUserName("name");
        userDto.setPassword("password");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1998-05-23");
        userDto.setDob(date);
        userDto.setEmail("name2@mail.com");
        userDto.setPhone("1234567891");
        UserDto user = userService.addUser(userDto);

        List<TripUserMapEntity> tripUserMap = tripUserMapRepository.findAll();
        service.addMember(tripUserMap.get(0).getTripId(),user.getUserId());
        TripUserMapDto tripUserMapDto = service.updateMember(tripUserMap.get(0).getTripId(),user.getUserId(),"ADMIN");
        assertEquals(UserRole.ADMIN.toString(), tripUserMapDto.getUserRole());
    }
}