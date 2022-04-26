package com.bankbazaar.service.manager;

import com.bankbazaar.core.manager.TripUserMapManager;
import com.bankbazaar.core.model.TripEntity;
import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.core.model.UserEntity;
import com.bankbazaar.core.model.UserRole;
import com.bankbazaar.dto.model.TripUserMapDto;
import com.bankbazaar.service.mapper.TripUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TripUserMapService {

    @Autowired
    private TripUserMapper modelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private TripUserMapManager manager;

    @Autowired
    private TripService tripService;


    public ModelAndView getTripsService(Long tripId, Principal principal) {

        List<TripUserMapEntity> userList = manager.getTripsTripId(tripId);
        ModelAndView model = new ModelAndView("trip_members");
        model.addObject("userList", userList);
        Optional<TripUserMapEntity> userData = manager.exists(tripId,userService.userDetails(principal).get().getUserId());
        model.addObject("role",userData.get().getUserRole().toString());
        return model;
    }

    public ResponseEntity<String> addUsersService(Long tripId, Long userId) {

        if(manager.exists(tripId,userId).isEmpty()) {
            TripUserMapEntity tripUserMap = new TripUserMapEntity();
            Optional<UserEntity> userEntity = userService.getUserDetails(userId);
            Optional<TripEntity> tripEntity = tripService.getTripDetails(tripId);

            tripUserMap.setUserId(userEntity.get().getUserId());
            tripUserMap.setTripId(tripEntity.get().getTripId());
            tripUserMap.setUserRole(UserRole.USER);
            manager.saveTripUserMapping(tripUserMap);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public String deleteUsersService(Long tripId, Long userId) {

        manager.deleteTripUserMapping(tripId,userId);
        List<TripUserMapEntity> userList = manager.getTripsTripId(tripId);
        Collections.sort(userList);
        boolean hasAdmin=false;
        for (TripUserMapEntity user : userList) {
            if(user.getUserRole()==UserRole.ADMIN)
            {
                hasAdmin=true;
                break;
            }
        }
        if(!hasAdmin)
        {
            try {
                TripUserMapEntity firstUser = userList.get(0);
                firstUser.setUserRole(UserRole.ADMIN);
                manager.updateTripUserMapping(firstUser);
            }
            catch (Exception e){return "redirect:/trip-user-mapping?tripId="+tripId;}
        }
        return "redirect:/trip-user-mapping?tripId="+tripId;
    }

    public ResponseEntity<TripUserMapEntity> updateUsersService(Long tripId, Long userId,String role) {

        if(manager.exists(tripId,userId).isPresent()) {
            TripUserMapDto tripUserMap = new TripUserMapDto();
            tripUserMap.setTripId(tripId);
            tripUserMap.setUserId(userId);
            tripUserMap.setUserRole(role);
            TripUserMapEntity response = manager.saveTripUserMapping(modelMapper.dtoToDomain(tripUserMap));
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
