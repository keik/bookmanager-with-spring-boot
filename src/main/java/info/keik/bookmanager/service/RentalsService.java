package info.keik.bookmanager.service;

import info.keik.bookmanager.model.Rental;

import java.util.Date;
import java.util.List;

public interface RentalsService {

    /**
     * Find all rentals
     * 
     * @return List of all rentals
     */
    public List<Rental> findAllRentals();

    /**
     * Find a rental by ID
     * 
     * @return A found rental
     */
    public Rental findRentalById(Integer rentalId);

    /**
     * Rent a stock
     * 
     * @param stockId
     *            Stock ID to rent
     * @param dueDate
     *            Due date
     */
    public Rental rentStock(Integer stockId, Date dueDate);

    /**
     * Return a stock
     * 
     * @param id
     *            Rental ID to return
     */
    public Rental returnRental(Integer rentalId);

}
