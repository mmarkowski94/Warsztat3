package pl.coderslab.users;

import pl.coderslab.utils.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/user/list")
public class UserList extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao userDao = new UserDao();
       // request.setAttribute("users", userDao.findAll());
        //getServletContext().getRequestDispatcher("list.jsp")
          //      .forward(request, response);

    }
}

