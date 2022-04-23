package com.ep396.Webapps2022.ejb;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.ep396.Webapps2022.entity.CurrencyEnum;
import com.ep396.Webapps2022.entity.SystemUserGroup;
import com.ep396.Webapps2022.entity.SystemUser;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author blankie
 */
@Stateless
public class UserService {

    @PersistenceContext
    EntityManager em;

    public UserService() {
    }

    public String registerUser(String username, String password, String confPassword, String name, String surname, Float currencyCount,
            CurrencyEnum currencyType) {
        try {
            SystemUser sys_user;
            SystemUserGroup sys_user_group;

            if (!password.equals(confPassword)) {
                return "Passwords do not match";
            }

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String passwd = password;
            md.update(passwd.getBytes("UTF-8"));
            byte[] digest = md.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            String paswdToStoreInDB = bigInt.toString(16);

            TypedQuery<SystemUser> query = em.createNamedQuery("getUserByUsername", SystemUser.class);
            query.setParameter("username", username);
            try {
                SystemUser result = query.getSingleResult();
                return "User already exists";

            } catch (NoResultException e) {
                sys_user = new SystemUser(username, paswdToStoreInDB, name, surname, currencyCount, currencyType);
                sys_user_group = new SystemUserGroup(username, "users");
                em.persist(sys_user);
                em.persist(sys_user_group);
                return "index";
            }
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return "Backend error: Unsupported encoding or no such algorithm error";
        }
    }
}
