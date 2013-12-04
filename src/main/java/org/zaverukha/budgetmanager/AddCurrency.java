package org.zaverukha.budgetmanager;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.inject.Named;

import org.zaverukha.budgetmanager.ejb.CurrencyManager;
import org.zaverukha.budgetmanager.jpa.Currency;

/**
 * Created with IntelliJ IDEA.
 * User: azaverukha
 * Date: 07.07.13
 * Time: 10:42
 * To change this template use File | Settings | File Templates.
 */
@Named
@SessionScoped
public class AddCurrency implements Serializable{
	private static final long serialVersionUID = 1893592098038631204L;
	@EJB
    private CurrencyManager ejbManager;
    String name;
    String sign;
    String code;
    public AddCurrency(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void create(ActionEvent event){
        Currency currency = new Currency();
        currency.setName(getName());
        currency.setCode(getCode());
        currency.setSign(getSign());
        ejbManager.create(currency);
    }


}
