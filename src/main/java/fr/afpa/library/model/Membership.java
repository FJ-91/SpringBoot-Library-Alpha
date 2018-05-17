package fr.afpa.library.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "membership")
public class Membership {

    private Long id;
    private String name;
    private float price;
    private int maxActiveBorrowings;
    private int maxTotalDelays;
    private List<Subscriber> subscribers;

    public Membership() {
    }

    public Membership(String name, float price, int maxActiveBorrowings, int maxTotalDelays) {
        this.name = name;
        this.price = price;
        this.maxActiveBorrowings = maxActiveBorrowings;
        this.maxTotalDelays = maxTotalDelays;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Column(name = "max_active_borrowings", nullable = false)
    public int getMaxActiveBorrowings() {
        return maxActiveBorrowings;
    }

    public void setMaxActiveBorrowings(int maxActiveBorrowings) {
        this.maxActiveBorrowings = maxActiveBorrowings;
    }

    @Column(name = "max_total_delays", nullable = false)
    public int getMaxTotalDelays() {
        return maxTotalDelays;
    }

    public void setMaxTotalDelays(int maxTotalDelays) {
        this.maxTotalDelays = maxTotalDelays;
    }

    @OneToMany(mappedBy = "membership")
    public List<Subscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<Subscriber> subscribers) {
        this.subscribers = subscribers;
    }

    @Override
    public String toString() {
        return "Membership{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", maxActiveBorrowings=" + maxActiveBorrowings +
                ", maxTotalDelays=" + maxTotalDelays +
                '}';
    }
}
