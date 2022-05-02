package com.bankbazaar.service;

import com.bankbazaar.core.repository.TripActivityRepository;
import com.bankbazaar.core.repository.TripRepository;
import com.bankbazaar.core.repository.TripUserMapRepository;
import com.bankbazaar.core.repository.UserRepository;
import com.bankbazaar.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"test"})
public class ServiceModuleTest {

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
    public void setUp(){
        log.info("Executing cleanup");
        tripUserMapRepository.deleteAll();
        tripActivityRepository.deleteAll();
        userRepository.deleteAll();
        tripRepository.deleteAll();

    }
    @Test
    void main() {
    }
}