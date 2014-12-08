package info.keik.bookmanager.service;

import info.keik.bookmanager.domain.RentalsRepository;
import info.keik.bookmanager.domain.StocksRepository;
import info.keik.bookmanager.model.Rental;
import info.keik.bookmanager.model.Stock;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentalsServiceImpl implements RentalsService {

    @Autowired
    private RentalsRepository rentalsRepository;

    @Autowired
    private StocksRepository stocksRepository;

    @Override
    public List<Rental> findAllRentals() {
        return rentalsRepository.findAll();
    }

    @Override
    public Rental findRentalById(Integer rentalId) {
        return rentalsRepository.findOne(rentalId);
    }

    @Override
    @Transactional
    public Rental rentStock(Integer stockId) {
        Stock stock = stocksRepository.findOne(stockId);
        if (stock.getIsOnLoan()) {
            return null;
        } else {
            stock.setIsOnLoan(true);
            Rental rental = new Rental();
            rental.setValid(true);
            rental.setStock(stock);
            return rentalsRepository.save(rental);
        }
    }

    @Override
    @Transactional
    public Rental returnStock(Integer rentalId) {
        Rental rental = rentalsRepository.findOne(rentalId);
        rental.getStock().setIsOnLoan(false);
        rental.setValid(false);
        return rentalsRepository.save(rental);
    }

}
