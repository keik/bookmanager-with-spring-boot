package info.keik.bookmanager.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tags")
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Book> books = new HashSet<Book>();

    public Tag() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        if (!getBooks().contains(book)) {
            getBooks().add(book);
        }
        if (!book.getTags().contains(this)) {
            book.getTags().add(this);
        }
    }

    public void removeBook(Book book) {
        if (getBooks().contains(book)) {
            getBooks().remove(book);
        }
        if (book.getTags().contains(this)) {
            book.getTags().remove(this);
        }
    }

}
