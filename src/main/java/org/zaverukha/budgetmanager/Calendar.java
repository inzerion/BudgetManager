package org.zaverukha.budgetmanager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: azaverukha
 * Date: 04.06.13
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class Calendar {
    Date date = new Date();
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
