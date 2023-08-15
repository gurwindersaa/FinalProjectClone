/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import services.AccountService;

/**
 *
 * @author gurwi
 */
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
          HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        String email = (String) session.getAttribute("email");
        try{
           AccountService acc = new AccountService();
           String role =acc.Role(email);
           if(role.equals("system admin")){
                        chain.doFilter(request, response); 
           }
           if(role.equals("regular user")){
                           HttpServletResponse httpResponse = (HttpServletResponse) response;
                           httpResponse.sendRedirect("Inventory");
           }
       }
           catch(Exception e){
                   
                   }
    }

    @Override
    public void destroy() {
    }
    
}
