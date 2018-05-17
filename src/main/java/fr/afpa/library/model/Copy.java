package fr.afpa.library.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "copy")
public class Copy {

    private Long id;
    private Boolean available;
    private Book book;
    private List<Borrowing> borrowings;

    public Copy() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @OneToMany(mappedBy = "copy")
    public List<Borrowing> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }

    @Override
    public String toString() {
        String authors = "";
        for(Author a : this.book.getAuthors()){
            authors += a.getName()+",";
        }
        authors = authors.substring(0, authors.length() - 1);
        return "Copy n°"+this.id+" : "+this.book.getTitle()+" from : "+authors;
    }
}
