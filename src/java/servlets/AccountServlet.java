/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dataaccess.RoleDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;
import services.UserServices;

/**
 *
 * @author gurwi
 */
public class AccountServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("email");
        UserServices us = new UserServices();

        try {
            User user = us.get(email);
            request.setAttribute("user", user);
            System.out.println(user);
        } catch (Exception ex) {
            Logger.getLogger(InventoryServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/UserAccount.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountService as = new AccountService();
        UserServices us = new UserServices();
        RoleDB role = new RoleDB();

        String email = (String) session.getAttribute("email");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String password = request.getParameter("password");
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "update":
                    System.out.println(action);
                    us.update(email, true, fname, lname, password);
                    request.setAttribute("message", "Changes has been made successfilly");
                    break;
                case "deactivate":
                    as.deactivate(email);
                     session.invalidate();
                     request.setAttribute("message", "Account has been deactivated");
                    break;
                   
            }
        } catch (Exception ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        getServletContext().getRequestDispatcher("/WEB-INF/UserAccount.jsp").forward(request, response);

    }
}
