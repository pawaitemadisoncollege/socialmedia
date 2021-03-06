package edu.matc.controller;

import edu.matc.entity.User;
import edu.matc.entity.UserRole;
import edu.matc.persistence.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This is the ShowEmployeeSearchServlet. It will set the page title and forward
 * to the employeeSearch.jsp page.
 *
 *@author lemerson
 */
@WebServlet(
        name = "performSignup",
        urlPatterns = {"/performSignup"}
) public class PerformSignup extends HttpServlet {
    /**
    *  Handles HTTP GET requests.
    *
    *@param  request               the HttpRequest
    *@param  response              the HttpResponse
    *@exception  ServletException  if there is a general servlet exception
    *@exception  IOException       if there is a general I/O exception
    */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userName = request.getParameter("user_name");
        String emailAddress = request.getParameter("email");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String password = request.getParameter("password");

        //Stock stock = new Stock();
        User user = new User(userName,password,emailAddress,firstName,lastName);

        //StockDailyRecord stockDailyRecords = new StockDailyRecord();
        UserRole role = new UserRole(userName,"user");

        //stockDailyRecords.setStock(stock);
        role.setUser(user);

        //stock.getStockDailyRecords().add(stockDailyRecords);
        user.getUserRoles().add(role);

        //session.save(stock);
        UserDao dao = new UserDao();
        String userNameReturn = dao.addUser(user);

        HttpSession session = request.getSession();
        session.setAttribute("newUser",true);
        session.setAttribute("newUserName",userName);
        String url = "showMyAccount";
        response.sendRedirect(url);
    }

}






