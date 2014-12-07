package info.keik.bookmanager.service;

import info.keik.bookmanager.domain.RentalsRepository;
import info.keik.bookmanager.model.Rental;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalsServiceImpl implements RentalsService {

    @Autowired
    private RentalsRepository rentalsRepository;

    @Override
    public List<Rental> findAllRentals() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Rental rentStock(Integer stockId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void returnStock(Integer stockId) {
        // TODO Auto-generated method stub

    }

}
