package com.bankbazaar.service.service;


import com.bankbazaar.core.repository.TripRepository;
import com.bankbazaar.core.repository.UserRepository;
import com.bankbazaar.dto.model.TripDto;
import com.bankbazaar.dto.model.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
class TripServiceTest extends TripUserMapServiceTest{

    @Autowired
    TripService service;
    @Autowired
    UserService userService;
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private UserRepository userRepository;
    @Test
    void tripService() throws ParseException {

        UserDto userDto =new UserDto();
        userDto.setUserName("name");
        userDto.setPassword("password");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse("1998-05-23");
        userDto.setDob(date);
        userDto.setEmail("name@mail.com");
        userDto.setPhone("1234567891");
        UserDto user = userService.addUser(userDto);
        assertEquals(0, tripRepository.count());

        TripDto tripDto =new TripDto();
        tripDto.setTripName("name");
        tripDto.setTripDescription("trip");
        tripDto.setDestination("trip");
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse("1998-05-23");
        tripDto.setStartDate(startDate);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse("1998-05-23");
        tripDto.setEndDate(endDate);
        TripDto trip = service.addTrip(tripDto, user.getUserId());

        assertEquals(1, tripRepository.count());
        validate(tripDto, trip);

        TripDto getTrip = service.getTripDetails(trip.getTripId());
        validate(trip, getTrip);

        TripDto nullTrip = service.getTripDetails(70L);
        assertNull(nullTrip);

        ModelAndView model = service.viewTrip(user.getUserId());
        assertEquals("trip", model.getViewName());
    }


    void validate(TripDto originalTrip, TripDto validateTrip)
    {
        assertNotNull(validateTrip);
        assertNotNull(validateTrip.getTripId());
        assertEquals(originalTrip.getTripName(), validateTrip.getTripName());
        assertEquals(originalTrip.getTripDescription(), validateTrip.getTripDescription());
        assertEquals(originalTrip.getDestination(),validateTrip.getDestination());
        assertEquals(originalTrip.getStartDate(),validateTrip.getStartDate());
        assertEquals(originalTrip.getEndDate(),validateTrip.getEndDate());
    }
}