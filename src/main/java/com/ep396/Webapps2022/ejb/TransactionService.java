/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ep396.Webapps2022.ejb;

import com.ep396.Webapps2022.entity.SystemUser;
import com.ep396.Webapps2022.entity.Transaction;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import static javax.ejb.TransactionAttributeType.REQUIRED;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author blankie
 */

@Stateless
@TransactionAttribute(REQUIRED)
public class TransactionService {
    @PersistenceContext
    EntityManager em;

    public TransactionService() {
    }

    @RolesAllowed({"users"})
    public synchronized SystemUser getUserByUsername(String username) {
        TypedQuery<SystemUser> query = em.createNamedQuery("getUserByUsername", SystemUser.class);
        query.setParameter("username", username);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @RolesAllowed({"users"})
    public synchronized List<Transaction> getUserAllTransactions(String username) {
        TypedQuery<Transaction> query = em.createNamedQuery("getUserAllTransactions", Transaction.class);
        query.setParameter("username", username);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @RolesAllowed({"users"})
    public synchronized List<Transaction> getUserRequestsSending(String username) {
        TypedQuery<Transaction> query = em.createNamedQuery("getUserRequestsSending", Transaction.class);
        query.setParameter("username", username);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @RolesAllowed({"users"})
    public synchronized List<Transaction> getUserRequestsRecieving(String username) {
        TypedQuery<Transaction> query = em.createNamedQuery("getUserRequestsRecieving", Transaction.class);
        query.setParameter("username", username);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @RolesAllowed({"users"})
    public synchronized List<Transaction> getUserCompletedSendings(String username) {
        TypedQuery<Transaction> query = em.createNamedQuery("getUserCompletedSendings", Transaction.class);
        query.setParameter("username", username);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @RolesAllowed({"users"})
    public synchronized List<Transaction> getUserCompletedRecievings(String username) {
        TypedQuery<Transaction> query = em.createNamedQuery("getUserCompletedRecievings", Transaction.class);
        query.setParameter("username", username);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @RolesAllowed({"admins"})
    public synchronized List<SystemUser> getAllUsers() {
        TypedQuery<SystemUser> query = em.createNamedQuery("getAllUsers", SystemUser.class);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @RolesAllowed({"admins"})
    public synchronized List<Transaction> getAllTransactions() {
        TypedQuery<Transaction> query = em.createNamedQuery("getAllTransactions", Transaction.class);
        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
