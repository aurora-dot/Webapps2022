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

    @NamedQuery(name = "getAllIncompleteTransactions", query = "SELECT t FROM Transaction t WHERE t.completed = false"),
    @NamedQuery(name = "getAllCompletedTransactions", query = "SELECT t FROM Transaction t WHERE t.completed = true"),
        
    @NamedQuery(name = "getUserRequestsSending", query = "SELECT t FROM Transaction t WHERE t.fromUsername = :username AND t.completed = false"),
    @NamedQuery(name = "getUserRequestsRecieving", query = "SELECT t FROM Transaction t WHERE t.toUsername = :username AND t.completed = true"),
        
    @NamedQuery(name = "getUserCompletedSendings", query = "SELECT t FROM Transaction t WHERE t.fromUsername = :username AND t.completed = false"),
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
    Float currencyCountToTransfer;

    @NotNull
    boolean completed;

    public Transaction() {
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

    public Float getCurrencyCountToTransfer() {
        return currencyCountToTransfer;
    }

    public void setCurrencyCountToTransfer(Float currencyCountToTransfer) {
        this.currencyCountToTransfer = currencyCountToTransfer;
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
        hash = 64 * hash + Objects.hashCode(this.currencyCountToTransfer);
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

        if (!Objects.equals(this.currencyCountToTransfer, other.currencyCountToTransfer)) {
            return false;
        }

        return Objects.equals(this.completed, other.completed);
    }
}
