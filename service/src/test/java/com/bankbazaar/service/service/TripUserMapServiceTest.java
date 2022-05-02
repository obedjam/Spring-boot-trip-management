package com.bankbazaar.service.service;

import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.core.repository.TripUserMapRepository;
import com.bankbazaar.dto.model.TripDto;
import com.bankbazaar.dto.model.TripUserMapDto;
import com.bankbazaar.dto.model.UserDto;
import com.bankbazaar.service.ServiceModuleTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class TripUserMapServiceTest extends ServiceModuleTest {

    @Autowired
    UserService userService;

    @Autowired
    TripService tripService;

    @Autowired
    TripUserMapService service;

    @Autowired
    private TripUserMapRepository tripUserMapRepository;

    @Test
    void tripUserMapService() throws ParseException {
        assertEquals(0, tripUserMapRepository.count());
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
        TripDto trip = tripService.addTrip(tripDto, user.getUserId());
        assertEquals(1,tripUserMapRepository.count());


        UserDto newUser = addUser("user1@mail.com");
        TripUserMapDto newTripUserMap = service.addMember(trip.getTripId(),newUser.getUserId());
        assertEquals(2, tripUserMapRepository.count());
        validate(newUser.getUserId(), trip.getTripId(), newTripUserMap);

        ModelAndView model = service.getMembers(trip.getTripId(),newUser.getUserId());
        assertEquals("trip_members", model.getViewName());

        TripUserMapDto updatedTripUserMap = service.updateMember(trip.getTripId(),newUser.getUserId(),"ADMIN");
        assertEquals("ADMIN", updatedTripUserMap.getUserRole());

        TripUserMapDto updateNullTripUserMap = service.updateMember(70L,71L,"ADMIN");
        assertNull(updateNullTripUserMap);

        boolean deleteResponse = service.deleteMember(trip.getTripId(),newUser.getUserId());
        assertTrue(deleteResponse);

        UserDto firstUser = addUser("user2@mail.com");
        service.addMember(trip.getTripId(),firstUser.getUserId());
        service.deleteMember(trip.getTripId(),user.getUserId());
        List<TripUserMapEntity> firstUserMap = tripUserMapRepository.findAllByUserId(firstUser.getUserId());
        assertEquals("ADMIN",firstUserMap.get(0).getUserRole().toString());

        service.deleteMember(trip.getTripId(),firstUser.getUserId());
        assertEquals(0, tripUserMapRepository.count());


    }

    void validate(Long userId, Long tripId, TripUserMapDto validateMap)
    {
        assertNotNull(validateMap);
        assertNotNull(validateMap.getTripId());
        assertNotNull(validateMap.getUserId());
        assertNotNull(validateMap.getUserRole());
        assertEquals(userId,validateMap.getUserId());
        assertEquals(tripId, validateMap.getTripId());

    }

    UserDto addUser(String email) throws ParseException {
        UserDto newUserDto =new UserDto();
        newUserDto.setUserName("name");
        newUserDto.setPassword("password");
        Date newDate = new SimpleDateFormat("yyyy-MM-dd").parse("1998-05-23");
        newUserDto.setDob(newDate);
        newUserDto.setEmail(email);
        newUserDto.setPhone("1234567891");
        return userService.addUser(newUserDto);
    }
}