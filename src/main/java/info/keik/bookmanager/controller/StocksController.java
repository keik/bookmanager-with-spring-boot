package info.keik.bookmanager.controller;

import info.keik.bookmanager.model.Stock;
import info.keik.bookmanager.model.item.Book;
import info.keik.bookmanager.service.BooksService;
import info.keik.bookmanager.service.StocksService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/stocks")
public class StocksController {

    @Autowired
    StocksService stocksService;

    @Autowired
    BooksService booksService;

    @InitBinder
    public void disallowedFieldBinder(WebDataBinder binder) {
        // binder.setDisallowedFields("id");
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model,
            @RequestParam(value = "q", required = false) String q) {
        if (q != null) {
            model.addAttribute("stocks", stocksService.findStocksByQuery(q));
            model.addAttribute("q", q);
        } else {
            model.addAttribute("stocks", stocksService.findAllStocks());
        }
        return "stocks-index.html";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(Model model, @PathVariable("id") Integer stockId) {
        model.addAttribute(stocksService.findStockById(stockId));
        return "stock-show.html";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String new_() {
        return "stock-new.html";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String create(Stock stock, Book book) {
        // TODO cannot persist book caused by null fields, but Java object have
        // values properly...
        book.setId(null);
        stock.setItem(book);
        stocksService.addStock(stock);
        return "redirect:/stocks";
    }

    @RequestMapping(value = "/{ids}", method = RequestMethod.DELETE)
    public @ResponseBody void destroy(@PathVariable("ids") String strIds) {
        List<Integer> stockIds = new ArrayList<Integer>();
        for (String strId : strIds.split(",")) {
            stockIds.add(Integer.valueOf(strId));
        }
        stocksService.deleteStocks(stockIds);
    }

}
