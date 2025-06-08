package kr.ac.hansung.cse.hellospringdatajpa.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name="roles")
@Getter
@Setter

public class Role
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable=false, unique=true)
    private String rolename;

    @ManyToMany(mappedBy="roles")
    private List<User> users;

    public Role() {

    }

    public Role(String rolename) {
        this.rolename = rolename;
    }

    public Integer getId() {
        return id;
    }

    public String getRolename() {
        return rolename;
    }

    public List<User> getUsers() {
        return users;
    }

}

