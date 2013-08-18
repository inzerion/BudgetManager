package org.zaverukha.budgetmanager.ejb;

import org.zaverukha.budgetmanager.jpa.Record;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: azaverukha
 * Date: 13.02.13
 * Time: 14:24
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class RecordManager {
    @PersistenceContext(unitName = "BudgetManager")
    private EntityManager em;
    public Record createExpense(Record expense){
       em.persist(expense);
       return expense;
    }

    public List<Record> findRecords(Date from, Date to){
        TypedQuery<Record> records =  em.createNamedQuery(Record.FIND_ALL_RECORDS, Record.class)
                .setParameter("fromDate", from, TemporalType.TIMESTAMP)
                .setParameter("toDate", to, TemporalType.TIMESTAMP );
        return records.getResultList();
    }

    public void removeRecord(Record record){
        em.remove(em.merge(record));
    }
}
