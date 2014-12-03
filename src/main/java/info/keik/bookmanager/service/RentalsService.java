package info.keik.bookmanager.service;

import info.keik.bookmanager.model.Rental;

import java.util.List;

public interface RentalsService {

    /**
     * Returns all rentals
     * 
     * @return List of all rentals
     */
    public List<Rental> findAllRentals();

    /**
     * Rent a stock
     * 
     * @param stockId
     *            Stock ID to rent
     */
    public Rental rentStock(Integer stockId);

    /**
     * Return a stock
     * 
     * @param id
     *            Book ID to delete
     */
    public void returnStock(Integer stockId);

}
