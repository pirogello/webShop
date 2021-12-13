package controller;

import model.User;
import model.dao.UsersDao;
import model.dao.UsersDaoDdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {

    UsersDao usersDao;

    @Override
    public void init() {
        try {
            usersDao = new UsersDaoDdbcImpl();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user") == null) {
            User user = usersDao.findAllByFirstName(req.getParameter("login"));
            if (user == null || !user.getPassword().equals(req.getParameter("password"))) {
                req.setAttribute("noUser", "не верное имя пользователя или пароль");
                req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
            } else {
                session.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/HomePage");
            }
        }
        else
            resp.sendRedirect(req.getContextPath() + "/HomePage");
    }
}
