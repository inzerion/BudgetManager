package org.zaverukha.budgetmanager.beans;

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
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void create(ActionEvent event){
        Account account = new Account();
        account.setName(getName());
        ejbManager.create(account);
    }
}
