package fr.afpa.library.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "borrowing")
public class Borrowing {

    private Long id;
    private LocalDate borrowDate;
    private LocalDate expectedReturnDate;
    private LocalDate returnDate;
    private Copy copy;
    private Subscriber subscriber;

    public Borrowing() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "borrowing_date")
    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    @Column(name = "expected_return_date")
    public LocalDate getExpectedReturnDate() {
        return expectedReturnDate;
    }

    public void setExpectedReturnDate(LocalDate expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    @Column(name = "return_date")
    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "copy_id")
    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id")
    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }
}
