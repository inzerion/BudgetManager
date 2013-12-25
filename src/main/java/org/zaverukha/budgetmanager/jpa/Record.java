package org.zaverukha.budgetmanager.jpa;

import javax.persistence.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: azaverukha
 * Date: 1/7/13
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = Record.FIND_ALL_RECORDS, query = "SELECT e FROM Record e WHERE e.recordDate between :fromDate and :toDate"),
        @NamedQuery(name = Record.FIND_ALL_TYPED_RECORDS, query = "SELECT e FROM Record e WHERE e.recordDate between :fromDate and :toDate and e.type = :type")
})

public class Record {
    public enum RecordType { INCOME, EXPENSE };
    public static final String FIND_ALL_RECORDS = "findAllRecords";
    public static final String FIND_ALL_TYPED_RECORDS = "findAllTypedRecords";

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordDate = new Date();
    private double ammount;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="currency_id")
    private Currency currency;

    public RecordType getType() {
        return type;
    }

    public void setType(RecordType type) {
        this.type = type;
    }

    @Enumerated(EnumType.ORDINAL)
    RecordType type;

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }





    public String getFormatedDate() {
        return DateFormat.getDateInstance(DateFormat.FULL, Locale.forLanguageTag("ru-RU")).format(recordDate);
    }

    public Date getDate() {
        return this.recordDate;
    }

    public void setDate(Date date) {
        this.recordDate = date;
    }





}
