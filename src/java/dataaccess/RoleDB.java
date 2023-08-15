/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import models.Role;
import models.User;

/**
 *
 * @author gurwi
 */
public class RoleDB {

    public RoleDB() {
    }

    public List<Role> getRoles() throws SQLException {
   EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            List<Role> roles = em.createNamedQuery("Role.findAll", Role.class).getResultList();
            return roles;
        } finally {
            em.close();
        }
    }

    public Role getUserRole(String owner)throws SQLException{
           EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
        TypedQuery<Role> query = em.createNamedQuery("Role.findByUserEmail", Role.class);
        query.setParameter("email", owner);

        List<Role> roles = query.getResultList();
        if (!roles.isEmpty()) {
            // Assuming there is only one role associated with the given email
            return roles.get(0);
        } else {
            // Handle the case where no role is found for the given email
            return null;
        }
    } finally {
        em.close();
    } 
    }
    
    public Role getRole(int id) throws SQLException {
          EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Role role = em.find(Role.class, id);
            return role;
        } finally {
            em.close();
        }
    }
}
