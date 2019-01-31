package com.reservation;


import com.reservation.controller.ReservationController;
import com.reservation.pojo.Customer;
import com.reservation.pojo.Reservation;
import com.reservation.pojo.Restaurant;
import com.reservation.repository.ReservationRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ReservationController.class)
public class ReservationControllerTest {

    @MockBean
    private ReservationRepository reservationRepository;
    @Autowired
    private MockMvc mockMvc;

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Test
    public void getReservations() throws Exception{
        
       Reservation mockReservation = new Reservation();
        Reservation mockRoomReservation = new Reservation();
        SimpleDateFormat timeformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        mockRoomReservation.setReservationDate(timeformat.parse("2019-01-15 09:30"));
        mockRoomReservation.setNoOfGuest(15);
        
        Customer c=new Customer();
		c.setCustomerId("0b3b05be-e0cc-498e-8d0c-03df3a0937bf");
		Restaurant r=new Restaurant();
		r.setRestaurantId("0464e11d-50d6-4205-bc9b-9e937b748ab3");
        mockRoomReservation.setReserveBy(c);
        mockRoomReservation.setReservedAt(r);
        

        given(reservationRepository.findById("1f167f61-5242-4307-9f73-b378a7ea5831")).willReturn(mockReservation);
        //this.mockMvc.perform(get("/reserve/1f167f61-5242-4307-9f73-b378a7ea5831")).andExpect(status().isOk());
    }
}
