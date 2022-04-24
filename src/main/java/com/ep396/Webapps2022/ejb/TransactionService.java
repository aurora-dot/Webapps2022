/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ep396.Webapps2022.ejb;

import com.ep396.Webapps2022.entity.CurrencyEnum;
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
    public synchronized Transaction makeTransaction(SystemUser to, SystemUser from, Float currencyCountTo, Float currencyCountFrom, CurrencyEnum currencyTypeTo, CurrencyEnum currencyTypeFrom, Boolean finalised) {
        return new Transaction(to.getUsername(), from.getUsername(), currencyCountTo, currencyCountFrom, currencyTypeTo, currencyTypeFrom, finalised);
    }

    @RolesAllowed({"users"})
    public synchronized Boolean acceptRequest(Long id) {
        try {
            Transaction pendingTransaction = em.find(Transaction.class, id);
            SystemUser fromUser = this.getUserByUsername(pendingTransaction.getFromUsername());
            SystemUser toUser = this.getUserByUsername(pendingTransaction.getToUsername());

            float currencyCount = pendingTransaction.getCurrencyCountFrom();
            if (fromUser.getCurrencyCount() < currencyCount) return false;

            toUser.setCurrencyCount(toUser.getCurrencyCount() + pendingTransaction.getCurrencyCountTo());
            fromUser.setCurrencyCount(fromUser.getCurrencyCount() - pendingTransaction.getCurrencyCountFrom());

            em.find(Transaction.class, pendingTransaction.getId()).setCompleted(true);
            em.persist(fromUser);
            em.persist(toUser);            
            em.flush();

            return true;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    @RolesAllowed({"users"})
    public synchronized boolean denyRequest(Long id) {
        try {
            em.remove(em.find(Transaction.class, id));
            em.flush();

            return true;

        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    @RolesAllowed({"users"})
    public synchronized boolean givePayment(String from, String to, float currencyCount) {
        try {
            SystemUser fromUser = this.getUserByUsername(from);
            SystemUser toUser = this.getUserByUsername(to);

            if (fromUser == null || toUser == null) return false;
            if (fromUser.getCurrencyCount() < currencyCount) return false;

            float fromCurrency = fromUser.getCurrencyType().convertCurrency(fromUser.getCurrencyType(), toUser.getCurrencyType());

            float currencyCountTo = currencyCount * fromCurrency;
            float currencyCountFrom = currencyCount;

            toUser.setCurrencyCount(toUser.getCurrencyCount() + currencyCountTo);
            fromUser.setCurrencyCount(fromUser.getCurrencyCount() - currencyCountFrom);

            em.persist(makeTransaction(toUser, fromUser, currencyCountTo, currencyCountFrom, toUser.getCurrencyType(), fromUser.getCurrencyType(), true));
            em.persist(fromUser);
            em.persist(toUser);            
            em.flush();

            return true;

        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }

    @RolesAllowed({"users"})
    public synchronized boolean requestPayment(String from, String to, float currencyCount) {
        try {
            SystemUser fromUser = this.getUserByUsername(from);
            SystemUser toUser = this.getUserByUsername(to);

            if (fromUser == null || toUser == null) return false;

            float fromCurrency = fromUser.getCurrencyType().convertCurrency(fromUser.getCurrencyType(), toUser.getCurrencyType());
            float currencyCountTo = currencyCount * fromCurrency;
            float currencyCountFrom = currencyCount;

            em.persist(makeTransaction(toUser, fromUser, currencyCountTo, currencyCountFrom, toUser.getCurrencyType(), fromUser.getCurrencyType(), false));
            em.flush();

            return true;

        } catch (Exception e) {
            System.err.println(e);
        }

        return false;
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
