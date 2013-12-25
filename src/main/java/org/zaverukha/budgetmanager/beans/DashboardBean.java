package org.zaverukha.budgetmanager.beans;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.zaverukha.budgetmanager.ejb.RecordManager;
import org.zaverukha.budgetmanager.jpa.Record;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by azaverukha on 16.12.13.
 */
@Named
@SessionScoped
public class DashboardBean implements Serializable{
    private static final long serialVersionUID = -4198624851730123886L;

    private Date fromDate;
    private Date toDate;

    public CartesianChartModel getModel() {

        return model;
    }

    CartesianChartModel model;
    @EJB
    RecordManager recordManager;
    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {

        this.toDate = toDate;
    }

    public void handleFromDateSelect(SelectEvent event){
        fromDate = (Date)event.getObject();
        createModel(model);
    }


    public void handleToDateSelect(SelectEvent event){
        toDate =  (Date)event.getObject();
        createModel(model);
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }



    @PostConstruct
    private void init(){
        fromDate = new Date();
        toDate = new Date();
        model = new CartesianChartModel();
        //createModel(model);

    }

    private void createModel(CartesianChartModel model ){
        ChartSeries expenses = new ChartSeries();
        List<Record> records =  recordManager.findExpensesByPeriod(fromDate, toDate);
        DateTime from  =  new DateTime(fromDate);
        DateTime to  =  new DateTime(fromDate);
         double ammount = 0.0   ;
        for(Record record: records){
            ammount += record.getAmmount();

        }
        expenses.set("24/12/2014", ammount * -1);

        model.clear();
        model.addSeries(expenses);
    }




}
