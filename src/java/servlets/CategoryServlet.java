/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Category;
import services.CategoryServices;

/**
 *
 * @author gurwi
 */
public class CategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryServices cs = new CategoryServices();

        String action = request.getParameter("action");
        String categoryName = request.getParameter("categoryName");
         System.out.println(categoryName);
        if (action != null && action.equals("edit")) {
            Category category = cs.get(categoryName);
            request.setAttribute("category", category);
         System.out.println(category);

        }
        try {
            List<Category> categories = cs.getAll();
            request.setAttribute("categories", categories);
            System.out.println(categories);
        } catch (Exception ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Category.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryServices cs = new CategoryServices();

        String action = request.getParameter("action");
        String categoryName = request.getParameter("categoryName");
         int categoryId = 0;
        if(action.equals("update")){
            categoryId = Integer.parseInt(request.getParameter("categoryId"));
        }
               
        try {
            switch (action) {
                case "create":
                    cs.insert(categoryName);

                    break;
                case "update":
                    cs.update(categoryId , categoryName);
                    break;
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            List<Category> categories = cs.getAll();
            request.setAttribute("categories", categories);
            System.out.println(categories);
        } catch (Exception ex) {
            Logger.getLogger(CategoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Category.jsp").forward(request, response);
    }

}
