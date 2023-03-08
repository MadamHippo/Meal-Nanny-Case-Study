package org.lilyhe.admin.model;

import jakarta.persistence.*;

/**
 * @author Lily H.
 */
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


    // to allow for multiple roles, 1 for shop admin 2 for shop assistant
    public Role(Integer id){
        this.id = id;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }


    // best practice to override default equals, and in case I need to create more roles in the future
    // checks if two objects of the same type (Class) are equal
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Role other = (Role) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.name;
    }


}
