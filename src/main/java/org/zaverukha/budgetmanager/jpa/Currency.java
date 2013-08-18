package org.zaverukha.budgetmanager.jpa;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: azaverukha
 * Date: 07.07.13
 * Time: 10:35
 * To change this template use File | Settings | File Templates.
 */
@Entity
@NamedQuery(name = "findAllCurrencies", query = "SELECT e FROM Currency e")
public class Currency {
    public static String FIND_ALL = "findAllCurrencies";

    @Id
    @GeneratedValue
    private Long id;
    @Column(length = 15)
    String name;
    @Column(length = 3)
    String code;
    @Column(length = 1)
    String sign;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }


    public Long getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
