package org.zaverukha.budgetmanager;

import java.io.Serializable;
import java.util.Date;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: azaverukha
 * Date: 04.06.13
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
@Named
@SessionScoped
public class Calendar implements Serializable {
	private static final long serialVersionUID = 4448284220680701837L;
	Date date = new Date();
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
