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
    private String name;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
