package info.keik.bookmanager.service;

import info.keik.bookmanager.model.Stock;

import java.util.List;

public interface StocksService {

    /**
     * Returns all stocks
     * 
     * @return List of all stocks
     */
    public List<Stock> findAllStocks();

    /**
     * Returns found stocks by query (temporary) TODO #9
     * 
     * @param name
     *            Part of name to find
     * @return Found stocks
     */
    public List<Stock> findStocksByQuery(String name);

    /**
     * Find a stock by ID
     * 
     * @param id
     *            Stock ID to find
     * @return A found stock
     */
    public Stock findStockById(Integer id);

    /**
     * Add a new stock
     * 
     * @param stock
     *            A stock to add
     */
    public Stock addStock(Stock stock);

    /**
     * Return a stock
     * 
     * @param stockId
     *            Stock ID to delete
     */
    public void deleteStock(Integer stockId);

    /**
     * Delete stocks
     * 
     * @param ids
     *            Stock IDs to delete
     */
    public void deleteStocks(List<Integer> ids);

}
