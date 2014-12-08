package info.keik.bookmanager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private Integer refNo;

    @Column(nullable = false)
    private String type;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Item item;

    @Column(nullable = false)
    private Boolean isOnLoan = true;

    @OneToMany(mappedBy = "stock")
    private List<Rental> rentals = new ArrayList<Rental>();

    public Stock() {
    }

    public Stock(Integer refNo, String type, Item item) {
        this.refNo = refNo;
        this.type = type;
        this.item = item;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRefNo() {
        return refNo;
    }

    public void setRefNo(Integer refNo) {
        this.refNo = refNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Boolean getIsOnLoan() {
        return isOnLoan;
    }

    public void setIsOnLoan(Boolean isOnLoan) {
        this.isOnLoan = isOnLoan;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void addComment(Rental rental) {
        if (!getRentals().contains(rental)) {
            getRentals().add(rental);
        }
        if (rental.getStock() != this) {
            rental.setStock(this);
        }
    }
}
