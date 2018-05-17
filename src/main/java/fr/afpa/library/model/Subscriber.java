package fr.afpa.library.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "subscriber")
@PrimaryKeyJoinColumn(name="person_id")
public class Subscriber extends User {

    private Boolean enabled;
    private String street;
    private String postalCode;
    private String city;
    private LocalDate creationDate;
    private LocalDateTime lastLogin;
    private String phoneNumber;
    private int totalDelays;
    private LocalDate membershipPurchaseDate;
    private Membership membership;
    private List<Borrowing> borrowings;

    public Subscriber() {
        super();
    }

    @Column(nullable = false)
    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Column(name = "postal_code")
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "creation_date", nullable = false)
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "last_login")
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name = "total_delays", nullable = false)
    public int getTotalDelays() {
        return totalDelays;
    }

    public void setTotalDelays(int totalDelays) {
        this.totalDelays = totalDelays;
    }

    @Column(name = "membership_purchase_date")
    public LocalDate getMembershipPurchaseDate() {
        return membershipPurchaseDate;
    }

    public void setMembershipPurchaseDate(LocalDate membershipPurchaseDate) {
        this.membershipPurchaseDate = membershipPurchaseDate;
    }

    @ManyToOne
    @JoinColumn(name = "membership_id")
    public Membership getMembership() {
        return membership;
    }

    public void setMembership(Membership membership) {
        this.membership = membership;
    }

    @OneToMany(mappedBy = "subscriber")
    public List<Borrowing> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(List<Borrowing> borrowings) {
        this.borrowings = borrowings;
    }

/*    @Override
    public String toString() {
        return this.getFirstname()+" "+this.getLastname()+" ("+this.getDateOfBirth()+") ["+this.membership.getName()+"]";
    }*/

    @Transient
    public String getName(){
        return this.getFirstname()+" "+this.getLastname();
    }
}
