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

import org.zaverukha.budgetmanager.ejb.RecordManager;
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

	private enum MoneyTraficEnum { INCOME, EXPENSE };


    @EJB
    private RecordManager recordManager;

    private  Date fromCalendarBean = prevDate();
    private Date toCalendarBean = new Date();;
    private Date addRecordCalendar = new Date();;
    private double ammount;

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
        addMoneyTrafic(MoneyTraficEnum.INCOME);
    }

    public void addExpense(ActionEvent event){
        addMoneyTrafic(MoneyTraficEnum.EXPENSE);
    }

    private void addMoneyTrafic(MoneyTraficEnum type){
        Record expense = new Record();
        ammount = correctAmmount(type, ammount);
        expense.setAmmount(ammount);
        expense.setDate(addRecordCalendar);
        recordManager.createExpense(expense);

        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected","Success"));
    }

    private static double correctAmmount(MoneyTraficEnum type, double ammount) {
        if(type == MoneyTraficEnum.EXPENSE && ammount > 0 ) ammount = -ammount;
        if(type == MoneyTraficEnum.INCOME && ammount < 0 ) ammount = -ammount;
        return ammount;
    }

    public void removeRecord(ActionEvent event){
        for(int i = 0; i < selectedRecords.length; i++){
            recordManager.removeRecord(selectedRecords[i]);
        }

    }

    private static Date prevDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_WEEK, -1);
        return calendar.getTime();

    }
}
