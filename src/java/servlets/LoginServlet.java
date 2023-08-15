package servlets;

import dataaccess.RoleDB;
import java.io.IOException;
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

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate(); // just by going to the login page the user is logged out :-) 

        String action = request.getParameter("action");
        System.out.println(action);
        if (action != null && action.equals("createUser")) {
            System.out.println(action);

            request.setAttribute("createAcc", action);

        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountService as = new AccountService();

        UserServices us = new UserServices();
        RoleDB role = new RoleDB();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String action = request.getParameter("action");
        String owner = request.getParameter("owner");
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        int roleno = 2;

        try {
            if ("create".equals(action)) //switch (action)
            {
//                case "create":
                us.insert(owner, fname, lname, password, role.getRole(roleno));
                request.setAttribute("message", "User created, please login");
//                    break;
//                case "update":
//                    is.update(itemId, name, price);
//                    break;

            
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            }
        } catch (Exception ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", "error");
        }
        
        if (action == null) {
            User user = as.login(email, password);
            if (user == null) {
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }
            HttpSession session = request.getSession();
            session.setAttribute("email", email);

            if (user.getRole().getRoleId() == 1) {
                response.sendRedirect("admin"); // Redirect for role 1
            } else {
                response.sendRedirect("Inventory"); // Redirect for other roles
            }

        }
    }
}
