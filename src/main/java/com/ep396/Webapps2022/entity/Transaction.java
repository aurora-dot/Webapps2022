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
import javax.validation.constraints.NotNull;

/**
 *
 * @author blankie
 */

@Entity
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    Long toUserID;

    @NotNull
    Long fromUserID;

    @NotNull
    Float currencyCountToTransfer;

    @NotNull
    boolean completed;

    public Transaction(Long id, Long toUserID, Long fromUserID, Float currencyCountToTransfer, boolean completed) {
        this.id = id;
        this.toUserID = toUserID;
        this.fromUserID = fromUserID;
        this.currencyCountToTransfer = currencyCountToTransfer;
        this.completed = completed;
    }

    public Transaction() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getToUserID() {
        return toUserID;
    }

    public void setToUserID(Long toUserID) {
        this.toUserID = toUserID;
    }

    public Long getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(Long fromUserID) {
        this.fromUserID = fromUserID;
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
        hash = 64 * hash + Objects.hashCode(this.toUserID);
        hash = 64 * hash + Objects.hashCode(this.fromUserID);
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
        if (!Objects.equals(this.toUserID, other.toUserID)) {
            return false;
        }
        if (!Objects.equals(this.fromUserID, other.fromUserID)) {
            return false;
        }

        if (!Objects.equals(this.currencyCountToTransfer, other.currencyCountToTransfer)) {
            return false;
        }

        return Objects.equals(this.completed, other.completed);
    }
}
