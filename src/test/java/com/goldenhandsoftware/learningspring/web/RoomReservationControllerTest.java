package com.goldenhandsoftware.learningspring.web;

import com.goldenhandsoftware.learningspring.business.domain.RoomReservation;
import com.goldenhandsoftware.learningspring.business.service.ReservationService;
import com.goldenhandsoftware.learningspring.business.web.DateUtils;
import com.goldenhandsoftware.learningspring.business.web.RoomReservationWebController;
import com.goldenhandsoftware.learningspring.data.entity.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(RoomReservationWebController.class)
public class RoomReservationControllerTest {
    @MockBean
    private ReservationService reservationService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getReservations() throws Exception {
        String dateString = "2020-01-01";
        Date date = DateUtils.createDateFromDateString(dateString);
        List<RoomReservation> roomReservations =  new ArrayList<RoomReservation>();
        RoomReservation roomReservation = new RoomReservation();
        roomReservation.setLastName("Torvalds");
        roomReservation.setFirstName("Linus");
        roomReservation.setDate(date);
        roomReservation.setGuestId(1);
        roomReservation.setRoomId(100);
        roomReservation.setRoomName("LINUX UBUNTU");
        roomReservation.setRoomNumber("LB1");
        roomReservations.add(roomReservation);
        given(reservationService.getRoomReservationsFromDate(date)).willReturn(roomReservations);

        this.mockMvc.perform(get("/reservations?date=2020-01-01"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Torvalds, Linus")));

    }
}
