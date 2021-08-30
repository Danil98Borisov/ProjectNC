package nc.project.controller;

import nc.project.models.Hotel;
import nc.project.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelRepository hotelRepository;

    @GetMapping("/all")
    public Iterable<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    @PutMapping("/add")
    Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @PutMapping("edit")
    Hotel editHotel(@RequestBody Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @DeleteMapping("/delete/{id}")
    void deleteHotel(@PathVariable Long id) {
        hotelRepository.deleteById(id);
    }

}