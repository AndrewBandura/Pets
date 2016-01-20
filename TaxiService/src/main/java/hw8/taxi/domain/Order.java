package hw8.taxi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Andrew on 18.10.2015.
 */
@Entity
@Table(name = "orders")
public class Order {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
//    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
    @Column(name = "order_id")
    private Long id;
    @Column(name = "oder_date")
    private Date date;

    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST})
    @JoinColumn(name = "order_client_id")
    private Client client;

    @Column(name="order_address_from")
    private String addressFrom;

    @Column(name = "order_address_to")
    private String addressTo;

    @Column(name = "order_amount")
    private double amount;

    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST})
    @JoinColumn(name = "order_user_id")
    @JsonIgnore
    private User user;

    public Order() {
    }

    public Order(Long id,Date date, Client client, String addressFrom, String addressTo, double amount) {
        this.id = id;
        this.date = date;
        this.client = client;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getAddressFrom() {
        return addressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.addressFrom = addressFrom;
    }

    public String getAddressTo() {
        return addressTo;
    }

    public void setAddressTo(String addressTo) {
        this.addressTo = addressTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
