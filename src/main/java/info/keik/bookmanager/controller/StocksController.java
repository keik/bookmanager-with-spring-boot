package info.keik.bookmanager.controller;

import info.keik.bookmanager.model.Stock;
import info.keik.bookmanager.service.StocksService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/reltals")
public class StocksController {

    @Autowired
    StocksService stocksService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<Stock> stocks = stocksService.findAllStocks();
        model.addAttribute("stocks", stocks);
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(Stock stock) {
        stocksService.addStock(stock);
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String destroy(@PathVariable("id") Integer stockId) {
        stocksService.deleteStock(stockId);
        return null;
    }

}
