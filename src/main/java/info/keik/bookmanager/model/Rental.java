package info.keik.bookmanager.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Rental implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date rentaledAt;

    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnedAt;

    @Column
    private Boolean valid;

    @ManyToOne
    private Stock stock;

    // @OneToOne
    // private User user;

    public Rental() {
    }

    @PrePersist
    protected void onCreate() {
        rentaledAt = new Date();
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getRentaledAt() {
        return rentaledAt;
    }

    public void setRentaledAt(Date rentaledAt) {
        this.rentaledAt = rentaledAt;
    }

    public Date getReturnedAt() {
        return returnedAt;
    }

    public void setReturnedAt(Date returnedAt) {
        this.returnedAt = returnedAt;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    // public User getUser() {
    // return user;
    // }
    //
    // public void setUser(User user) {
    // this.user = user;
    // }

}
