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
@NamedQuery(name = "findAllRecords", query = "SELECT e FROM Record e WHERE e.recordDate between :fromDate and :toDate")
public class Record {
    public static String FIND_ALL_RECORDS = "findAllRecords";

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordDate = new Date();
    private double ammount;

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }



    public String getDate() {
        return DateFormat.getDateInstance(DateFormat.FULL, Locale.forLanguageTag("ru-RU")).format(recordDate);
    }

    public void setDate(Date date) {
        this.recordDate = date;
    }





}
