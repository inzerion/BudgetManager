package org.zaverukha.budgetmanager.ejb;

import org.zaverukha.budgetmanager.jpa.Currency;
import org.zaverukha.budgetmanager.jpa.Record;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azaverukha
 * Date: 07.07.13
 * Time: 10:47
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class CurrencyManager {
    @PersistenceContext(unitName = "BudgetManager")
    private EntityManager em;
    public Currency create(Currency currency){
        em.persist(currency);
        return currency;
    }

    public List<Currency> findAll(){
        TypedQuery<Currency> records =  em.createNamedQuery(Currency.FIND_ALL, Currency.class);
        return records.getResultList();
    }

    public void remove(Currency currency){
        em.remove(em.merge(currency));
    }
}
