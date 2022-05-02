package com.bankbazaar.service.service;

import com.bankbazaar.core.model.ActivityStatus;
import com.bankbazaar.core.model.TripUserCompositeKey;
import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.core.model.UserRole;
import com.bankbazaar.core.repository.TripActivityRepository;
import com.bankbazaar.core.repository.TripUserMapRepository;
import com.bankbazaar.dto.model.TripActivityDto;
import com.bankbazaar.dto.model.TripDto;
import com.bankbazaar.dto.model.TripUserMapDto;
import com.bankbazaar.dto.model.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TripActivityServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    TripService tripService;

    @Autowired
    TripActivityService service;

    @Autowired
    TripUserMapService tripUserMapService;

    @Autowired
    private TripActivityRepository tripActivityRepository;



    @Test
    void tripActivityService() throws ParseException {
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

        assertEquals(tripActivityRepository.count(),0);

        TripActivityDto tripActivityDto = addActivity();

        TripActivityDto tripActivity = service.addTripActivity(trip.getTripId(),tripActivityDto,user.getUserId());
        assertEquals(tripActivityRepository.count(),1);
        validate(tripActivityDto, tripActivity);

        List<TripActivityDto> tripList = service.findTripActivityById(trip.getTripId(),user.getUserId());
        assertEquals(1,tripList.size());
        validate(tripActivity, tripList.get(0));

        TripActivityDto updatedTripActivity = service.updateTripActivity(tripActivity.getActivityId(),tripActivity.getTripId(),"REJECTED");
        assertEquals(ActivityStatus.REJECTED.toString(), updatedTripActivity.getActivityStatus());

        tripUserMapService.updateMember(trip.getTripId(),user.getUserId(),"USER");

        TripActivityDto activity = service.addTripActivity(trip.getTripId(),addActivity(),user.getUserId());
        service.updateTripActivity(activity.getActivityId(),tripActivity.getTripId(),"APPROVED");;
        List<TripActivityDto> newTripList = service.findTripActivityById(trip.getTripId(),user.getUserId());
        assertEquals(1,newTripList.size());
        assertEquals(tripActivityRepository.count(),2);
    }

    void validate(TripActivityDto originalActivity, TripActivityDto validateActivity)
    {
        assertNotNull(validateActivity);
        assertNotNull(validateActivity.getActivityId());
        assertNotNull(validateActivity.getTripId());
        assertNotNull(validateActivity.getActivityStatus());
        assertEquals(originalActivity.getActivityDescription(), validateActivity.getActivityDescription());
        assertEquals(originalActivity.getActivityTime(),validateActivity.getActivityTime());
        assertEquals(originalActivity.getAddedBy(), validateActivity.getAddedBy());
        assertEquals(originalActivity.getLocation(), validateActivity.getLocation());
    }

    TripActivityDto addActivity() throws ParseException {
        TripActivityDto tripActivityDto = new TripActivityDto();
        tripActivityDto.setActivityStatus("PENDING");
        tripActivityDto.setActivityDescription("trip activity");
        Date activityDate = new SimpleDateFormat("yyyy-MM-dd").parse("1998-05-23");
        tripActivityDto.setActivityTime(activityDate);
        tripActivityDto.setLocation("park");

        return  tripActivityDto;
    }
}