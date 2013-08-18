package org.zaverukha.budgetmanager;

import org.zaverukha.budgetmanager.ejb.RecordManager;
import org.zaverukha.budgetmanager.jpa.Record;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azaverukha
 * Date: 1/7/13
 * Time: 5:21 PM
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@SessionScoped
public class RecordsView {

    private enum MoneyTraficEnum { INCOME, EXPENSE };


    @EJB
    private RecordManager recordManager;
    Calendar fromCalendarBean = new Calendar();
    Calendar toCalendarBean = new Calendar();
    Calendar addRecordCalendar = new Calendar();

    double ammount;
    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public Calendar getAddRecordCalendar() {
        return addRecordCalendar;
    }

    public void setAddRecordCalendar(Calendar addRecordCalendar) {
        this.addRecordCalendar = addRecordCalendar;
    }


    public Calendar getToCalendarBean() {
        return toCalendarBean;
    }

    public void setToCalendarBean(Calendar toCalendarBean) {
        this.toCalendarBean = toCalendarBean;
    }

    public Calendar getFromCalendarBean() {
        return fromCalendarBean;
    }

    public void setFromCalendarBean(Calendar fromCalendarBean) {
        this.fromCalendarBean = fromCalendarBean;
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
        return recordManager.findRecords(fromCalendarBean.getDate(), toCalendarBean.getDate());
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
        expense.setDate(addRecordCalendar.getDate());
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
}
