package com.news.noti.schedule;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;


@SpringBootTest
@ActiveProfiles("dev")
class ScheduleRunnerIT {

    @Autowired
    private ScheduleRunner scheduleRunner;

    @Test
    void run(){
        given(LocalDate.now()).willReturn(LocalDate.of(2023,6,16));
        scheduleRunner.run();
    }

}