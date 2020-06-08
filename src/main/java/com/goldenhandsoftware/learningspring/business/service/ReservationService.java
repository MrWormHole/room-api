package com.goldenhandsoftware.learningspring.business.service;

import com.goldenhandsoftware.learningspring.business.domain.RoomReservation;
import com.goldenhandsoftware.learningspring.data.entity.Guest;
import com.goldenhandsoftware.learningspring.data.entity.Reservation;
import com.goldenhandsoftware.learningspring.data.entity.Room;
import com.goldenhandsoftware.learningspring.data.repository.GuestRepository;
import com.goldenhandsoftware.learningspring.data.repository.ReservationRepository;
import com.goldenhandsoftware.learningspring.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReservationService {
    private final RoomRepository roomRepository;
    private final GuestRepository guestRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(RoomRepository roomRepository, GuestRepository guestRepository, ReservationRepository reservationRepository) {
        this.roomRepository = roomRepository;
        this.guestRepository = guestRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<RoomReservation> getRoomReservationsForDate(Date date) {
        // Get rooms ready for room reservations
        Iterable<Room> rooms = this.roomRepository.findAll();
        Map<Long, RoomReservation> roomReservationMap = new HashMap<Long, RoomReservation>();
        rooms.forEach(room -> {
            RoomReservation roomReservation = new RoomReservation();
            roomReservation.setRoomId(room.getRoomId());
            roomReservation.setRoomName(room.getRoomName());
            roomReservation.setRoomNumber(room.getRoomNumber());
            roomReservationMap.put(roomReservation.getRoomId(), roomReservation);
        });

        // Get reservations ready for guests
        Iterable<Reservation> reservations = this.reservationRepository.findReservationByReservationDate(new java.sql.Date(date.getTime()));
        reservations.forEach(reservation -> {
            RoomReservation roomReservation = roomReservationMap.get(reservation.getRoomId());
            roomReservation.setDate(date);
            Guest guest = this.guestRepository.findById(reservation.getGuestId()).get();
            roomReservation.setFirstName(guest.getFirstName());
            roomReservation.setLastName(guest.getLastName());
            roomReservation.setGuestId(guest.getGuestId());
        });

        // Finally return list of room reservations
        List<RoomReservation> roomReservations = new ArrayList<RoomReservation>();
        for(Long id: roomReservationMap.keySet()) {
            roomReservations.add(roomReservationMap.get(id));
        }
        return roomReservations;
    }
}
