package org.zaverukha.budgetmanager.beans;

import java.io.Serializable;
import java.util.Locale;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class LanguageSwitcher implements Serializable{

	private static final long serialVersionUID = -3027351891054130388L;
	@Inject
	transient Logger logger;
    public void changeLanguage(String localeStr) {
    	logger.info("Change locale to: " + localeStr);
        Locale locale = new Locale(localeStr);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }
}
