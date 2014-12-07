package info.keik.bookmanager.model.item;

import info.keik.bookmanager.model.Item;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
public class Book extends Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    public Book() {
    }

    public Book(String name, String author, String publisher) {
        this.name = name;
        this.author = author;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

}
