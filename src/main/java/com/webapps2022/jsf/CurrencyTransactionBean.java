/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.jsf;

import com.webapps2022.ejb.CurrencyTransactionService;
import com.webapps2022.entity.CurrencyEnum;
import com.webapps2022.entity.SystemUser;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author blankie
 */
@Named
@RequestScoped
public class CurrencyTransactionBean {
    @EJB
    CurrencyTransactionService transactionService;

    private String otherUsername;
    private Float currencyCount;

    public CurrencyTransactionService getTransactionService() {
        return transactionService;
    }

    public void setTransactionService(CurrencyTransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public String getOtherUsername() {
        return otherUsername;
    }

    public void setOtherUsername(String otherUsername) {
        this.otherUsername = otherUsername;
    }

    public Float getCurrencyCount() {
        return currencyCount;
    }

    public void setCurrencyCount(Float currencyCount) {
        this.currencyCount = currencyCount;
    }

    public String getCurrentUserCurrencyCount(String username) {
        try {
            SystemUser user = transactionService.getUserByUsername(username);
            return CurrencyEnum.getCurrencySymbol(user.getCurrencyType()) + user.getCurrencyCount();
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;  
    }

    public String getCurrentUserCurrencySymbol(String username) {
        try {
            SystemUser user = transactionService.getUserByUsername(username);
            return CurrencyEnum.getCurrencySymbol(user.getCurrencyType());
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;  
    }

}
