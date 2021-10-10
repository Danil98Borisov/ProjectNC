package nc.project.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nc.project.jpa.entity.Reservation;
import nc.project.jpa.repository.ReservationRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "http://localhost:4200")
public class ReservationController {


    private final ReservationRepository reservationRepository;

    @GetMapping("/all")
    public Iterable<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @DeleteMapping("/delete/{id}")
    void deleteReservation(@PathVariable Long id) {
        reservationRepository.deleteById(id);
    }

    @GetMapping("/booking/{email}")
    public List<Reservation> findReservationByUser(@PathVariable("email") String email) {
        return reservationRepository.findReservationByUser(email);
    }
    @PostMapping("/edit")
    Reservation editReservation(@RequestBody Reservation reservation) {
        return reservationRepository.save(reservation);
    }

}