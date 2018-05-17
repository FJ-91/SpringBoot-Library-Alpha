package fr.afpa.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "author")
@PrimaryKeyJoinColumn(name="person_id")
public class Author extends Person {

    private String pseudonym;
    private List<Book> books;

    public Author() {
        super();
    }

    @Column(name = "pseudonym")
    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL)
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return getLastname()+" "+getFirstname()+" ("+getDateOfBirth()+")";
    }

    @Transient
    public String getName(){
        return this.getFirstname()+" "+this.getLastname();
    }
}
