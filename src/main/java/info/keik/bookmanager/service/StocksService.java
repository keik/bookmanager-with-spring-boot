package info.keik.bookmanager.service;

import info.keik.bookmanager.model.Stock;

import java.util.List;

public interface StocksService {

    /**
     * Returns all stocks
     * 
     * @return List of all stccks
     */
    public List<Stock> findAllStocks();

    /**
     * Add a new stock
     * 
     * @param stock
     *            A stock to add
     */
    public Stock addStock(Stock stock);

    /**
     * Return a book
     * 
     * @param stockId
     *            Stock ID to delete
     */
    public void deleteStock(Integer stockId);

}
