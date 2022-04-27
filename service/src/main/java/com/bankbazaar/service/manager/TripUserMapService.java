package com.bankbazaar.service.manager;

import com.bankbazaar.core.manager.TripUserMapManager;
import com.bankbazaar.core.model.TripUserMapEntity;
import com.bankbazaar.core.model.UserRole;
import com.bankbazaar.dto.model.TripUserMapDto;
import com.bankbazaar.service.mapper.TripUserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    public TripUserMapDto addUsersService(Long tripId, Long userId) {

            TripUserMapEntity tripUserMap = new TripUserMapEntity();
            tripUserMap.setTripId(tripId);
            tripUserMap.setUserId(userId);
            tripUserMap.setUserRole(UserRole.USER);
            TripUserMapEntity response = manager.saveTripUserMapping(tripUserMap);
            return modelMapper.domainToDto(response);

    }

    public void deleteUsersService(Long tripId, Long userId) {

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
            catch (Exception e){return;}
        }
    }

    public TripUserMapDto updateUsersService(Long tripId, Long userId,String role) {

        if(manager.exists(tripId,userId).isPresent()) {
            TripUserMapDto tripUserMap = new TripUserMapDto();
            tripUserMap.setTripId(tripId);
            tripUserMap.setUserId(userId);
            tripUserMap.setUserRole(role);
            TripUserMapEntity response = manager.saveTripUserMapping(modelMapper.dtoToDomain(tripUserMap));
            return modelMapper.domainToDto(response);
        }
        else
        {
            return null;
        }
    }
}
