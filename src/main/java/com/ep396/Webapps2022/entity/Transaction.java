/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ep396.Webapps2022.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

/**
 *
 * @author blankie
 */

@NamedQueries({
    @NamedQuery(name = "getAllTransactions", query = "SELECT t FROM Transaction t"),
    @NamedQuery(name = "getUserAllTransactions", query = "SELECT t FROM Transaction t WHERE t.fromUsername = :username OR t.toUsername = :username"),

    @NamedQuery(name = "getUserRequestsSending", query = "SELECT t FROM Transaction t WHERE t.fromUsername = :username AND t.completed = false"),
    @NamedQuery(name = "getUserRequestsRecieving", query = "SELECT t FROM Transaction t WHERE t.toUsername = :username AND t.completed = false"),
        
    @NamedQuery(name = "getUserCompletedSendings", query = "SELECT t FROM Transaction t WHERE t.fromUsername = :username AND t.completed = true"),
    @NamedQuery(name = "getUserCompletedRecievings", query = "SELECT t FROM Transaction t WHERE t.toUsername = :username AND t.completed = true"),
})

@Entity
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    String toUsername;

    @NotNull
    String fromUsername;

    @NotNull
    Float currencyCountTo;

    @NotNull
    Float currencyCountFrom;

    @NotNull
    CurrencyEnum currencyTypeTo;

    @NotNull
    CurrencyEnum currencyTypeFrom;

    @NotNull
    boolean completed;


    public Transaction() {
    }

    public Transaction(String toUsername, String fromUsername, Float currencyCountTo, Float currencyCountFrom, CurrencyEnum currencyTypeTo, CurrencyEnum currencyTypeFrom, boolean completed) {
        this.toUsername = toUsername;
        this.fromUsername = fromUsername;
        this.currencyCountTo = currencyCountTo;
        this.currencyCountFrom = currencyCountFrom;
        this.currencyTypeTo = currencyTypeTo;
        this.currencyTypeFrom = currencyTypeFrom;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public Float getCurrencyCountTo() {
        return currencyCountTo;
    }

    public void setCurrencyCountTo(Float currencyCountTo) {
        this.currencyCountTo = currencyCountTo;
    }

    public Float getCurrencyCountFrom() {
        return currencyCountFrom;
    }

    public void setCurrencyCountFrom(Float currencyCountFrom) {
        this.currencyCountFrom = currencyCountFrom;
    }

    public CurrencyEnum getCurrencyTypeTo() {
        return currencyTypeTo;
    }

    public void setCurrencyTypeTo(CurrencyEnum currencyTypeTo) {
        this.currencyTypeTo = currencyTypeTo;
    }

    public CurrencyEnum getCurrencyTypeFrom() {
        return currencyTypeFrom;
    }

    public void setCurrencyTypeFrom(CurrencyEnum currencyTypeFrom) {
        this.currencyTypeFrom = currencyTypeFrom;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }



    @Override
    public int hashCode() {
        int hash = 3;
        hash = 64 * hash + Objects.hashCode(this.id);
        hash = 64 * hash + Objects.hashCode(this.toUsername);
        hash = 64 * hash + Objects.hashCode(this.fromUsername);
        hash = 64 * hash + Objects.hashCode(this.currencyCountTo);
        hash = 64 * hash + Objects.hashCode(this.currencyCountFrom);
        hash = 64 * hash + Objects.hashCode(this.currencyTypeTo);
        hash = 64 * hash + Objects.hashCode(this.currencyTypeFrom);
        hash = 64 * hash + Objects.hashCode(this.completed);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transaction other = (Transaction) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.toUsername, other.toUsername)) {
            return false;
        }
        if (!Objects.equals(this.fromUsername, other.fromUsername)) {
            return false;
        }

        if (!Objects.equals(this.currencyCountTo, other.currencyCountTo)) {
            return false;
        }

        if (!Objects.equals(this.currencyCountFrom, other.currencyCountFrom)) {
            return false;
        }

        if (!Objects.equals(this.currencyTypeTo, other.currencyTypeTo)) {
            return false;
        }

        if (!Objects.equals(this.currencyTypeFrom, other.currencyTypeFrom)) {
            return false;
        }

        return Objects.equals(this.completed, other.completed);
    }
}
