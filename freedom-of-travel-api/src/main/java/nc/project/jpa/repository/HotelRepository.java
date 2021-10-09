package nc.project.jpa.repository;

import nc.project.jpa.entity.Hotel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    @Query(value = "select DISTINCT ON(name) h.id,h.city,h.description, h.name, h.rating from apartment apart, hotel h, apartment_type ap_type " +
            "where h.id = apart.hotel_id " +
            "and ap_type.id = apart.apartment_type_id and (:rating is null or h.rating > :rating)" +
            "and (:city = '' or h.city = :city) " +
            "and (:type = '' or ap_type.name = :type) " +
            "and (:price is null or apart.price < :price) " +
            "and not exists  " +
            " (select 1 from reservation resrv " +
            "   where resrv.start_date >= :startDate " +
            "   and resrv.end_date <= :endDate " +
            "   and resrv.apartment_id = apart.id)" +
            "ORDER BY h.name, h.id,h.rating", nativeQuery = true)
    List<Hotel> findAvailableHotels(@Param("startDate") LocalDate startDate,
                                            @Param("endDate") LocalDate endDate,
                                            @Param("city") String city,
                                            @Param("rating") Integer rating,
                                            @Param("type") String type,
                                            @Param("price") Float price,
                                            Pageable pageble);
}