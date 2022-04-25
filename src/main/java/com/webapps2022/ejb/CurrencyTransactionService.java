/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.webapps2022.ejb;

import com.webapps2022.entity.CurrencyEnum;
import com.webapps2022.entity.SystemUser;
import com.webapps2022.entity.CurrencyTransaction;
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
public class CurrencyTransactionService {
    @PersistenceContext
    EntityManager em;

    public CurrencyTransactionService() {
    }

    @RolesAllowed({ "users" })
    private synchronized CurrencyTransaction createTransaction(SystemUser toSystemUser, SystemUser fromSystemUser,
            Float currencyCountTo, Float currencyCountFrom, CurrencyEnum currencyTypeTo, CurrencyEnum currencyTypeFrom,
            Boolean finalised) {
        return new CurrencyTransaction(toSystemUser, fromSystemUser, currencyCountTo,
                currencyCountFrom, currencyTypeTo, currencyTypeFrom, finalised);
    }

    @RolesAllowed({ "users" })
    public synchronized boolean acceptRequest(CurrencyTransaction pendingTransaction) {
        SystemUser fromUser = pendingTransaction.getFromSystemUser();
        SystemUser toUser = pendingTransaction.getToSystemUser();

        float currencyCount = pendingTransaction.getCurrencyCountFrom();
        if (fromUser.getCurrencyCount() < currencyCount)
            return false;

        toUser.setCurrencyCount(toUser.getCurrencyCount() + pendingTransaction.getCurrencyCountTo());
        fromUser.setCurrencyCount(fromUser.getCurrencyCount() - pendingTransaction.getCurrencyCountFrom());
        pendingTransaction.setCompleted(true);

        em.merge(pendingTransaction);
        em.merge(fromUser);
        em.merge(toUser);
        em.flush();

        return true;

    }

    @RolesAllowed({ "users" })
    public synchronized boolean denyRequest(CurrencyTransaction pendingTransaction) {
        em.remove(em.find(CurrencyTransaction.class, pendingTransaction.getId()));
        em.flush();

        return true;
    }

    @RolesAllowed({ "users" })
    public synchronized boolean sendPayment(String toUsername, String fromUsername, float currencyCount) {
        SystemUser toUser = getUserByUsername(toUsername);
        SystemUser fromUser = getUserByUsername(fromUsername);

        if (toUser == null || fromUser == null)
            return false;
        if (fromUser.getCurrencyCount() < currencyCount)
            return false;

        float fromCurrency = CurrencyEnum.convertCurrency(fromUser.getCurrencyType(), toUser.getCurrencyType());

        float currencyCountTo = currencyCount * fromCurrency;
        float currencyCountFrom = currencyCount;

        toUser.setCurrencyCount(toUser.getCurrencyCount() + currencyCountTo);
        fromUser.setCurrencyCount(fromUser.getCurrencyCount() - currencyCountFrom);

        em.persist(fromUser);
        em.persist(toUser);
        em.persist(createTransaction(toUser, fromUser, currencyCountTo, currencyCountFrom, toUser.getCurrencyType(),
                fromUser.getCurrencyType(), true));
        em.flush();

        return true;
    }

    @RolesAllowed({ "users" })
    public synchronized boolean requestPayment(String toUsername, String fromUsername, float currencyCount) {
        SystemUser toUser = getUserByUsername(toUsername);
        SystemUser fromUser = getUserByUsername(fromUsername);

        if (toUser == null || fromUser == null)
            return false;

        float fromCurrency = CurrencyEnum.convertCurrency(fromUser.getCurrencyType(), toUser.getCurrencyType());
        float currencyCountTo = currencyCount * fromCurrency;
        float currencyCountFrom = currencyCount;

        em.persist(createTransaction(toUser, fromUser, currencyCountTo, currencyCountFrom, toUser.getCurrencyType(),
                fromUser.getCurrencyType(), false));
        em.flush();

        return true;

    }

    @RolesAllowed({ "users" })
    public synchronized SystemUser getUserByUsername(String username) {
        TypedQuery<SystemUser> query = em.createNamedQuery("getUserByUsername", SystemUser.class);
        query.setParameter("username", username);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @RolesAllowed({ "users" })
    public synchronized List<CurrencyTransaction> getUserAllTransactions(String username) {
        TypedQuery<CurrencyTransaction> query = em.createNamedQuery("getUserAllTransactions", CurrencyTransaction.class);
        query.setParameter("systemUser", getUserByUsername(username));

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @RolesAllowed({ "users" })
    public synchronized List<CurrencyTransaction> getUserRequestsSending(String username) {
        TypedQuery<CurrencyTransaction> query = em.createNamedQuery("getUserRequestsSending", CurrencyTransaction.class);
        query.setParameter("systemUser", getUserByUsername(username));

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @RolesAllowed({ "users" })
    public synchronized List<CurrencyTransaction> getUserRequestsRecieving(String username) {
        TypedQuery<CurrencyTransaction> query = em.createNamedQuery("getUserRequestsRecieving", CurrencyTransaction.class);
        query.setParameter("systemUser", getUserByUsername(username));

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @RolesAllowed({ "users" })
    public synchronized List<CurrencyTransaction> getUserCompletedSendings(String username) {
        TypedQuery<CurrencyTransaction> query = em.createNamedQuery("getUserCompletedSendings", CurrencyTransaction.class);
        query.setParameter("systemUser", getUserByUsername(username));

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @RolesAllowed({ "users" })
    public synchronized List<CurrencyTransaction> getUserCompletedRecievings(String username) {
        TypedQuery<CurrencyTransaction> query = em.createNamedQuery("getUserCompletedRecievings", CurrencyTransaction.class);
        query.setParameter("systemUser", getUserByUsername(username));

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @RolesAllowed({ "users" })
    public synchronized List<CurrencyTransaction> getUserCompletedTransactions(String username) {
        TypedQuery<CurrencyTransaction> query = em.createNamedQuery("getUserCompletedTransactions", CurrencyTransaction.class);
        query.setParameter("systemUser", getUserByUsername(username));

        try {
            List<CurrencyTransaction> stuff = query.getResultList();
            System.out.println("Got stuff");
            System.out.println(stuff);
            return stuff;
        } catch (NoResultException e) {
            return null;
        }
    }

    @RolesAllowed({ "admins" })
    public synchronized List<SystemUser> getAllUsers() {
        TypedQuery<SystemUser> query = em.createNamedQuery("getAllUsers", SystemUser.class);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @RolesAllowed({ "admins" })
    public synchronized List<CurrencyTransaction> getAllTransactions() {
        TypedQuery<CurrencyTransaction> query = em.createNamedQuery("getAllTransactions", CurrencyTransaction.class);

        try {
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }
}
