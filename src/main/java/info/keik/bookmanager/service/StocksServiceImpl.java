package info.keik.bookmanager.service;

import info.keik.bookmanager.domain.StocksRepository;
import info.keik.bookmanager.model.Stock;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StocksServiceImpl implements StocksService {

    @Autowired
    private StocksRepository stocksRepository;

    @Override
    @Transactional
    public List<Stock> findAllStocks() {
        return stocksRepository.findAll();
    }

    @Override
    @Transactional
    public Stock findStockById(Integer stockId) {
        return stocksRepository.findOne(stockId);
    }

    @Override
    @Transactional
    public List<Stock> findStocksByQuery(String name) {
        return stocksRepository.findByNameContainsInItem(name);
    }

    @Override
    @Transactional
    public Stock addStock(Stock stock) {
        return stocksRepository.save(stock);
    }

    @Override
    @Transactional
    public Stock rentStock(Integer stockId) {
        Stock stock = stocksRepository.findOne(stockId);
        if (stock.getIsOnLoan()) {
            return null;
        } else {
            stock.setIsOnLoan(true);
            return stock;
        }
    }

    @Override
    @Transactional
    public Stock returnStock(Integer stockId) {
        Stock stock = stocksRepository.findOne(stockId);
        if (!stock.getIsOnLoan()) {
            return null;
        } else {
            stock.setIsOnLoan(false);
            return stock;
        }
    }

    @Override
    @Transactional
    public void deleteStock(Integer stockId) {
        stocksRepository.delete(stockId);
    }

    @Override
    @Transactional
    public void deleteStocks(List<Integer> ids) {
        stocksRepository.deleteByIdIn(ids);
    }

}
