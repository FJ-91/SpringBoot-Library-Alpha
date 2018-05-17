package fr.afpa.library.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book")
public class Book {

    private Long id;
    private String isbn;
    private String title;
    private String subtitle;
    private Boolean available;
    private Genre genre;
    private int totalCopies;
    private int copiesAvailable;
    private String bookGenre;
    private List<Author> authors;
    private List<Copy> copies;

    public Book() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Boolean getAvailable() {
        return available;
    }
    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "author_writes_book",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")}
    )
    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    public List<Copy> getCopies() {
        return copies;
    }

    public void setCopies(List<Copy> copies) {
        this.copies = copies;
    }

    @Transient
    public int getTotalCopies() {
        return this.getCopies().size();
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    @Transient
    public int getCopiesAvailable() {
        int i = 0;
        for(Copy c : this.getCopies())if(c.getAvailable()==true) i++;
        return i;
    }

    public void setCopiesAvailable(int copiesAvailable) {
        this.copiesAvailable = copiesAvailable;
    }

    @Transient
    public String getBookGenre() {
        return genre.getName();
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    @Override
    public String toString() {
        String str = this.title+" from : "+this.authors;

        return str;
    }
}
