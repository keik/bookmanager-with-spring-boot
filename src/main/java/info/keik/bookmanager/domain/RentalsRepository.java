package info.keik.bookmanager.domain;

import info.keik.bookmanager.model.Rental;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalsRepository extends JpaRepository<Rental, Integer> {

}
