package info.keik.bookmanager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;

    @Column(nullable = false)
    private String type;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Item item;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addedAt;

    @Column(nullable = false)
    private Boolean isOnLoan = true;

    @OneToMany(mappedBy = "stock")
    private List<Rental> rentals = new ArrayList<Rental>();

    public Stock() {
    }

    public Stock(Integer id, String type, Item item) {
        this.id = id;
        this.type = type;
        this.item = item;
    }

    @PrePersist
    protected void onCreate() {
        addedAt = new Date();
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

    public Date getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(Date addedAt) {
        this.addedAt = addedAt;
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

    public void addRental(Rental rental) {
        if (!getRentals().contains(rental)) {
            getRentals().add(rental);
        }
        if (rental.getStock() != this) {
            rental.setStock(this);
        }
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
