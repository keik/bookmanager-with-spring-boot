package info.keik.bookmanager.model.item;

import info.keik.bookmanager.model.Item;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Computer extends Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String productNo;

    @Column(nullable = false)
    private String vendor;

    public Computer() {
    }

    public Computer(String name, String productNo, String vendor) {
        this.name = name;
        this.productNo = productNo;
        this.vendor = vendor;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

}
