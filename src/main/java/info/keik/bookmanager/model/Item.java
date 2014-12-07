package info.keik.bookmanager.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    protected String name;

    @OneToMany(mappedBy = "item")
    @OrderBy(value = "created")
    private List<Comment> comments = new ArrayList<Comment>();

    @ManyToMany(targetEntity = Tag.class, cascade = CascadeType.ALL)
    @JoinTable(name = "item_to_tag", joinColumns = { @JoinColumn(name = "item_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "tag_id", referencedColumnName = "id") })
    @JsonIgnore
    @OrderBy("name ASC")
    private Set<Tag> tags = new HashSet<Tag>();

    @OneToOne(mappedBy = "item", cascade = CascadeType.PERSIST)
    private Stock stock;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        if (!getComments().contains(comment)) {
            getComments().add(comment);
        }
        if (comment.getItem() != this) {
            comment.setItem(this);
        }
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        if (!getTags().contains(tag)) {
            getTags().add(tag);
        }
        if (!tag.getItems().contains(this)) {
            tag.getItems().add(this);
        }
    }

    public void removeTag(Tag tag) {
        if (getTags().contains(tag)) {
            getTags().remove(tag);
        }
        if (tag.getItems().contains(this)) {
            tag.getItems().remove(this);
        }
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

}
