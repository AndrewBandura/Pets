package hw8.taxi.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Andrew on 17.09.2015.
 */
@Entity
@Table(name="roles")
public class Role {
    @Id
    @Column(name="role_id")
    @SequenceGenerator(name="idGenerator",sequenceName = "role_seq",allocationSize = 1)
    @GeneratedValue(generator = "idGenerator", strategy= GenerationType.SEQUENCE)
    private Long id;
    @Column(name="role_name",unique = true)
    private String name;

    @OneToMany(mappedBy = "role")
    @Cascade({CascadeType.SAVE_UPDATE})
    private Set<User> users = new HashSet();

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
