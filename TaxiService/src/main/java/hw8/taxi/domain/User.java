package hw8.taxi.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrew on 17.09.2015.
 */
@Entity
@Table(name="operators")
public class User {
    @Id
    @Column(name="operator_id")
    @SequenceGenerator(name="idGenerator",sequenceName = "operator_seq",allocationSize = 1)
    @GeneratedValue(generator = "idGenerator", strategy= GenerationType.SEQUENCE)
    private Long id;
    @Column(name="operator_name",unique = true)
    private String name;
    @Column(name="operator_password")
    private String password;
    @Column(name="operator_date")
    private Date date;
    @Column(name="operator_is_blocked")
    private boolean isBlocked;
    @Column(name = "operator_personal_id")
    private String personalId;

    @ManyToOne
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.PERSIST})
    @JoinColumn(name = "user_role_id")
    private Role role;

    @OneToMany(mappedBy = "user")
    @Cascade({CascadeType.SAVE_UPDATE})
    private Set<Order> orders = new HashSet();


    public User() {
    }

    public User(String name, String password, Date date,Role role, String personalId) {
        this.date = date;
        this.role = role;
        this.password = password;
        this.name = name;
        this.personalId = personalId;
        this.isBlocked=false;

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private  Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public boolean isPasswordExpired(int termDays){
        Date expiryDate = addDays(this.getDate(),termDays);
        return expiryDate.compareTo(this.date)<0;
    }

    public boolean isPasswordValid(String password){
        return password.equals(this.getPassword()) ;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
