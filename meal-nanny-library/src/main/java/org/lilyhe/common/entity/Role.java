package org.lilyhe.common.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // no two rows can have same name; all rows must have a name
    @Column(length = 30, nullable = false, unique = true)
    private String name;

    @Column(length = 300, nullable = false)
    private String bio;

    // empty constructor required by Hibernate
    public Role() {

    }

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, String bio) {
        this.name = name;
        this.bio = bio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
