package com.goldenhandsoftware.learningspring;

import com.goldenhandsoftware.learningspring.data.entity.Guest;
import com.goldenhandsoftware.learningspring.data.entity.Reservation;
import com.goldenhandsoftware.learningspring.data.entity.Room;
import com.goldenhandsoftware.learningspring.data.repository.GuestRepository;
import com.goldenhandsoftware.learningspring.data.repository.ReservationRepository;
import com.goldenhandsoftware.learningspring.data.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class LearningSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearningSpringApplication.class, args);
	}

	// Commented out this because of mockito BDD tests
	/*@RestController
	@RequestMapping("/rooms")
	public class RoomController {
		@Autowired
		private RoomRepository roomRepository;

		@GetMapping
		public Iterable<Room> getRooms() {
			return this.roomRepository.findAll();
		}

	}*/
}
