/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import models.Category;
import models.Item;
import models.User;

/**
 *
 * @author gurwi
 */
public class CategoryDB {

    public Category get(String categoryName) {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            TypedQuery<Category> query = em.createQuery("SELECT c FROM Category c WHERE c.categoryName = :categoryName", Category.class);
            query.setParameter("categoryName", categoryName);
            Category category = query.getSingleResult();
            return category;
        } finally {
            em.close();
        }
    }
    
    public Category get(int categoryId) {
    EntityManager em = DBUtil.getEmFactory().createEntityManager();
    try {
        Category category = em.find(Category.class, categoryId);
        return category;
    } finally {
        em.close();
    }
}

    public List<Category> getAll() throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Category> categories = em.createNamedQuery("Category.findAll", Category.class).getResultList();
            return categories;
        } finally {
            em.close();
        }
    }

    public void insert(Category category) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(category);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
    
     public void update(Category category) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(category);
            trans.commit();
        } catch (Exception ex) {
            trans.rollback();
        } finally {
            em.close();
        }
    }
     

}
