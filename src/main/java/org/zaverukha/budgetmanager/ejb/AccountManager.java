package org.zaverukha.budgetmanager.ejb;

import org.zaverukha.budgetmanager.jpa.Account;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azaverukha
 * Date: 07.07.13
 * Time: 14:46
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class AccountManager {
    @PersistenceContext(unitName = "BudgetManager")
    private EntityManager em;
    public Account create(Account currency){
        em.persist(currency);
        return currency;
    }

    public List<Account> findAll(){
        TypedQuery<Account> records =  em.createNamedQuery(Account.FIND_ALL, Account.class);
        return records.getResultList();
    }

    public void remove(Account account){
        em.remove(em.merge(account));
    }
}
