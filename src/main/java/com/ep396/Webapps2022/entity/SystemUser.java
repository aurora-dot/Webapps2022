/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ep396.Webapps2022.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
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
        @NamedQuery(name = "getUserByUsername", query = "SELECT u FROM SystemUser u WHERE u.username = :username"),
        @NamedQuery(name = "getUser", query = "SELECT u FROM SystemUser u")

})

@Entity
public class SystemUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotNull
    String username;

    @NotNull
    String password;

    @NotNull
    String name;

    @NotNull
    String surname;

    @NotNull
    Float currencyCount;

    @NotNull
    CurrencyEnum currencyType;

    public SystemUser() {
    }

    public SystemUser(String username, String password, String name, String surname, Float currencyCount,
            CurrencyEnum currencyType) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = username;
        this.currencyCount = currencyCount;
        this.currencyType = currencyType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Float getCurrencyCount() {
        return currencyCount;
    }

    public void setCurrencyCount(Float currencyCount) {
        this.currencyCount = currencyCount;
    }

    public CurrencyEnum getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyEnum currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.surname);
        hash = 71 * hash + Objects.hashCode(this.username);
        hash = 71 * hash + Objects.hashCode(this.password);
        hash = 71 * hash + Objects.hashCode(this.currencyCount);
        hash = 71 * hash + Objects.hashCode(this.currencyType);

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
        final SystemUser other = (SystemUser) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }

        if (!Objects.equals(this.username, other.username)) {
            return false;
        }

        if (!Objects.equals(this.currencyCount, other.currencyCount)) {
            return false;
        }

        if (!Objects.equals(this.currencyType, other.currencyType)) {
            return false;
        }

        return Objects.equals(this.password, other.password);
    }
}
