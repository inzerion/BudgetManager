package org.zaverukha.budgetmanager;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.zaverukha.budgetmanager.ejb.AccountManager;
import org.zaverukha.budgetmanager.ejb.CurrencyManager;
import org.zaverukha.budgetmanager.jpa.Account;
import org.zaverukha.budgetmanager.jpa.Currency;

/**
 * Created with IntelliJ IDEA.
 * User: azaverukha
 * Date: 07.07.13
 * Time: 11:13
 * To change this template use File | Settings | File Templates.
 */
@Named
@SessionScoped
public class ConfigurationView implements Serializable{
	private static final long serialVersionUID = 3638800492322654168L;

	@EJB
    private CurrencyManager currencyManager;

    @EJB
    private AccountManager accountManager;
    List<Currency> selectedCurrencies;
    List<Account> selectedAccounts;

    public List<Account> getSelectedAccounts() {
        return selectedAccounts;
    }

    public void setSelectedAccounts(List<Account> selectedAccounts) {
        this.selectedAccounts = selectedAccounts;
    }

    public List<Currency> getCurrencies() {
        return currencyManager.findAll();
    }

    public List<Account> getAccounts() {
        return accountManager.findAll();
    }

    public List<Currency> getSelectedCurrencies() {
        return selectedCurrencies;
    }

    public void setSelectedCurrencies(List<Currency> selectedCurrencies) {
        this.selectedCurrencies = selectedCurrencies;
    }


    public void removeCurrency(ActionEvent event){
        for(Currency currency : selectedCurrencies)
         currencyManager.remove(currency);
    }

    public void removeAccount(ActionEvent event){
        for(Account account : selectedAccounts)
            accountManager.remove(account);
    }


}
