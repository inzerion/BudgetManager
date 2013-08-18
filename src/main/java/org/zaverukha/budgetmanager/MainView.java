package org.zaverukha.budgetmanager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: azaverukha
 * Date: 07.07.13
 * Time: 16:01
 * To change this template use File | Settings | File Templates.
 */
@SessionScoped
@ManagedBean
public class MainView {
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



