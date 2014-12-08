package info.keik.bookmanager.controller;

import info.keik.bookmanager.model.Rental;
import info.keik.bookmanager.service.RentalsService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rentals")
public class RentalsController {

    @Autowired
    RentalsService rentalsService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        List<Rental> rentals = rentalsService.findAllRentals();
        model.addAttribute("rentals", rentals);
        return "rentals-index.html";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable("id") Integer id, Model model) {
        Rental rental = rentalsService.findRentalById(id);
        model.addAttribute("rental", rental);
        return "book-show.html";
    }

    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String create(@RequestParam("stockId") Integer stockId) {
        rentalsService.rentStock(stockId);

        // TODO design
        return null;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, params = "method=put")
    public String update(@PathVariable("id") Integer rentalId) {
        rentalsService.returnStock(rentalId);
        return null;
    }

}
