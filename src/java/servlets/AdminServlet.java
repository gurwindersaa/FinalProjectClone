/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import services.CategoryServices;
import dataaccess.ItemDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.Item;
import models.User;
import services.ItemServices;
import services.UserServices;

/**
 *
 * @author gurwi
 */
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserServices us = new UserServices();
        HttpSession session = request.getSession();
        ItemServices is = new ItemServices();
        CategoryServices cs = new CategoryServices();
        String action = request.getParameter("action");
        String userEmail = request.getParameter("userEmail");
        if (userEmail != null) {
            session.setAttribute("userEmail", userEmail);
        }
        String email = (String) session.getAttribute("userEmail");
        String itemIdParam = request.getParameter("itemId");
        int itemId = 0; // Set a default value

        if (itemIdParam != null && !itemIdParam.isEmpty()) {
            itemId = Integer.parseInt(itemIdParam);
        }

        if (action != null && action.equals("edit")) {
            try {
                ItemDB itemdb = new ItemDB();
                Item item = itemdb.get(itemId);
                request.setAttribute("selectedItem", item);

            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (action != null && action.equals("delete")) {
            try {
                is.delete(itemId);

            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {

            List<Item> items = is.getAll(email);
            request.setAttribute("items", items);
            User user = us.get(email);
            System.out.println(user);
            List<Category> categories = cs.getAll();
            request.setAttribute("categories", categories);
            request.setAttribute("user", user);

        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        try {
            List<User> users = us.getAll();
            request.setAttribute("users", users);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserServices us = new UserServices();
        ItemServices is = new ItemServices();
        CategoryServices cs = new CategoryServices();

        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userEmail");
        System.out.println("dopost" + email);

        String action = (String) request.getParameter("action");

        String itemIdParam = request.getParameter("itemId");
        int itemId = 0; // Set a default value
        if (itemIdParam != null && !itemIdParam.isEmpty()) {
            itemId = Integer.parseInt(itemIdParam);
        }
        String name = request.getParameter("itemName");
        String category = request.getParameter("category");
        String priceParam = request.getParameter("price");
        double price = 0; // Set a default value
        if (priceParam != null && !priceParam.isEmpty()) {
            price = Double.parseDouble(priceParam);
        }
        System.out.println(name);
        try {
            switch (action) {
                case "create":
                    is.insert(category, name, price, email);
                    break;
                case "update":
                    is.update(itemId, name, price);
                    break;

            }
            request.setAttribute("message", action);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        try {
            List<User> users = us.getAll();
            request.setAttribute("users", users);
            List<Item> items = is.getAll(email);
            request.setAttribute("items", items);
            List<Category> categories = cs.getAll();
            request.setAttribute("categories", categories);
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);

    }
}
