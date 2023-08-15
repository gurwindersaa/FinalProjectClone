/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Item;
import models.User;

public class UserDB {

    public User get(String email) {

        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            User user = em.find(User.class, email);
            String role = user.getRole().getRoleName();
            return user;
        } finally {
            em.close();
        }
    }

    public List<User> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<User> users = em.createNamedQuery("User.findAll", User.class).getResultList();
            return users;
        } finally {
            em.close();
        }
    }
    
       public void insert(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
       }

         public void update(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
 
    public void delete(User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            user = em.merge(user); // Merge the user instance to attach it to the EntityManager
            List<Item> items = user.getItemList();
            for (Item item : items) {
                em.remove(item); // Remove each associated item
            }
            em.remove(user); // Remove the user
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
            throw ex;
        } finally {
            em.close();
        }
    }
}


