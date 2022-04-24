/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ep396.Webapps2022.jsf;

import com.ep396.Webapps2022.ejb.TransactionService;
import com.ep396.Webapps2022.entity.CurrencyEnum;
import com.ep396.Webapps2022.entity.SystemUser;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author blankie
 */
@Named
@RequestScoped
public class TransactionBean {
    @EJB
    TransactionService transactionService;

    private String otherUsername;
    private Float currencyCount;

    public TransactionService getTransactionService() {
        return transactionService;
    }

    public void setTransactionService(TransactionService transactionService) {
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

    public Float getCurrentUserCurrencyCount(String username) {
        try {
            return transactionService.getUserByUsername(username).getCurrencyCount();
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public String getCurrentUserCurrencyCountAsString(String username) {
        try {
            SystemUser user = transactionService.getUserByUsername(username);
            return CurrencyEnum.getCurrencySymbol(user.getCurrencyType()) + user.getCurrencyCount();
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;  
    }


}
