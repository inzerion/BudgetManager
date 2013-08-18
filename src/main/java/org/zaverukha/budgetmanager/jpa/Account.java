package org.zaverukha.budgetmanager.jpa;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: azaverukha
 * Date: 07.07.13
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
@Entity
@NamedQuery(name = "findAllAccounts", query = "SELECT e FROM Account e")
public class Account {
    public static String FIND_ALL = "findAllAccounts";


    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 15)
    private String firstName;
    @Column(length = 15)
    private String lastName;

    public Long getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
