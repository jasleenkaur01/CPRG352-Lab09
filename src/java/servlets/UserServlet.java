
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
import javax.servlet.http.HttpSession;
import models.Role;
import models.User;
import services.*;


public class UserServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RoleService rs = new RoleService();
        UserService us = new UserService();
        HttpSession session = request.getSession();
        
        String action = request.getParameter("action");
        
        if(action != null && action.equals("edit")){
            String email = request.getParameter("email");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String password = request.getParameter("password");
            String active = request.getParameter("active");
            System.out.println(active);
            
            
            session.setAttribute("edemail", email);
             request.setAttribute("email", email);
            request.setAttribute("firstname", firstname);
            request.setAttribute("lastname", lastname);
            request.setAttribute("password", password);
            if(active.equals("true")){
                request.setAttribute("active", true);
            }
                    }
        else if(action != null && action.equals("delete") ){          
            String editEmail = request.getParameter("email");   
            try {
                us.delete(editEmail);
            } catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
         try {
            List users = us.getAll();
            List roles = rs.getAll();
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        return;
    }
        

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        if (action != null && action.equals("add")){
            try {
                boolean active = false;
                String email = request.getParameter("addemail");
                String firstname = request.getParameter("addfirstname");
                String lastname = request.getParameter("addlastname");
                String password = request.getParameter("password");
                String role = request.getParameter("roles");
                String active2 = request.getParameter("active");
                System.out.println(active2);
                if (active2 != null){
                active = true;
                }
                else {
                active = false;
                }
                
                int roleId = 0;
                UserService us = new UserService();
                RoleService rs = new RoleService();
                List<Role> roles = rs.getAll();
        
                for(Role rol: roles){
            if(rol.getRoleName().equals(role)){
                roleId = rol.getRoleId();
            }
                
        }
                us.insert(email, active, firstname, lastname, password, roleId);
            }
            catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
         if (action != null && action.equals("edit")){
            try {
                boolean active = false;
            String edemail = (String) session.getAttribute("edemail");
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String password = request.getParameter("password");
            String role = request.getParameter("role");
            String active2 = request.getParameter("active");
            System.out.println(active2);
            if (active2 != null){
                active = true;
            }
            else {
                active = false;
            }
            
            
         
        int roleID = 0;
        
        UserService us = new UserService();
        RoleService rs = new RoleService();
        
        List<Role> roles = rs.getAll();
        List <User> users = us.getAll();
        
        
        for(Role rol: roles){
            if(rol.getRoleName().equals(role)){
                roleID = rol.getRoleId();
            }
                
        }
        
        us.update(edemail, active, firstname, lastname, password, roleID);
            }
             catch (Exception ex) {
                Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        UserService us = new UserService();
        RoleService rs = new RoleService();
        
         try {
            List users = us.getAll();
            List roles = rs.getAll();
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        return;
    }

    

}
