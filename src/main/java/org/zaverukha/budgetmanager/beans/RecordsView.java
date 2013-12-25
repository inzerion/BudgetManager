package org.zaverukha.budgetmanager.beans;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.Date;
import javax.inject.Named;

import org.zaverukha.budgetmanager.ejb.CurrencyManager;
import org.zaverukha.budgetmanager.ejb.RecordManager;
import org.zaverukha.budgetmanager.jpa.Currency;
import org.zaverukha.budgetmanager.jpa.Record;

/**
 * Created with IntelliJ IDEA.
 * User: azaverukha
 * Date: 1/7/13
 * Time: 5:21 PM
 * To change this template use File | Settings | File Templates.
 */
@Named
@SessionScoped
public class RecordsView implements Serializable{
	private static final long serialVersionUID = -4198624851730923886L;



    @EJB
    private RecordManager recordManager;
    @EJB
    private CurrencyManager currencyManager;
    private Date fromCalendarBean;
    private Date toCalendarBean;
    private Date addRecordCalendar;
    private double ammount;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    String currency;

    public List<Currency> getCurrencies(){
        return currencyManager.findAll();
    }

    @PostConstruct
    private void init(){
        fromCalendarBean  = previousDay();
        toCalendarBean = new Date();
        addRecordCalendar = new Date();
    }

    public Date getFromCalendarBean() {
        return fromCalendarBean;
    }

    public void setFromCalendarBean(Date fromCalendarBean) {
        this.fromCalendarBean = fromCalendarBean;
    }

    public Date getToCalendarBean() {
        return toCalendarBean;
    }

    public void setToCalendarBean(Date toCalendarBean) {
        this.toCalendarBean = toCalendarBean;
    }

    public Date getAddRecordCalendar() {
        return addRecordCalendar;
    }

    public void setAddRecordCalendar(Date addRecordCalendar) {
        this.addRecordCalendar = addRecordCalendar;
    }



    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public Record[] getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(Record[] selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    private Record[] selectedRecords;




    public RecordsView(){

    }


    public List<Record> getRecords() {
        return recordManager.findRecords(fromCalendarBean, toCalendarBean);
    }

    public void addIncome(ActionEvent event){
        addMoneyTrafic(Record.RecordType.INCOME);
    }

    public void addExpense(ActionEvent event){
        addMoneyTrafic(Record.RecordType.EXPENSE);
    }

    private void addMoneyTrafic(Record.RecordType type){
        Record record = new Record();
        ammount = correctAmmount(type, ammount);
        record.setAmmount(ammount);
        record.setDate(addRecordCalendar);
        record.setType(type);
        record.setCurrency(currencyManager.findBySign(currency));
        recordManager.createExpense(record);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected","Success"));
    }

    private static double correctAmmount(Record.RecordType type, double ammount) {
        if(type == Record.RecordType.EXPENSE && ammount > 0 ) ammount = -ammount;
        if(type == Record.RecordType.INCOME && ammount < 0 ) ammount = -ammount;
        return ammount;
    }

    public void removeRecord(ActionEvent event){
        for(int i = 0; i < selectedRecords.length; i++){
            recordManager.removeRecord(selectedRecords[i]);
        }

    }

    private static Date previousDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, -1);
        return calendar.getTime();

    }
}
