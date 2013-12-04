package org.zaverukha.budgetmanager;

import java.io.Serializable;
import java.util.Map;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 * Created with IntelliJ IDEA.
 * User: azaverukha
 * Date: 07.07.13
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
@Named
@SessionScoped
public class MainView implements Serializable{
	private static final long serialVersionUID = -999280835985447033L;

	public int getActiveIndex() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params =
                fc.getExternalContext().getRequestParameterMap();
        String index = params.get("activeIndex");
        activeIndex = index == null ? 0 :  Integer.parseInt(index);;
        return activeIndex;
    }

      int activeIndex;

}



