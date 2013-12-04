package org.zaverukha.budgetmanager;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.zaverukha.budgetmanager.ejb.AccountManager;
import org.zaverukha.budgetmanager.jpa.Account;

/**
 * Created with IntelliJ IDEA.
 * User: azaverukha
 * Date: 07.07.13
 * Time: 14:45
 * To change this template use File | Settings | File Templates.
 */
@Named
@SessionScoped
public class AddAccount implements Serializable {
	private static final long serialVersionUID = 5277135173717933964L;
	@EJB
    private AccountManager ejbManager;
    String firstName;
    String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void create(ActionEvent event){
        Account account = new Account();
        account.setFirstName(getFirstName());
        account.setLastName(getLastName());
        ejbManager.create(account);
    }
}
