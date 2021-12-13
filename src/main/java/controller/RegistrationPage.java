package controller;

import model.Role;
import model.User;
import model.dao.RolesDao;
import model.dao.RolesDaoDbcImpl;
import model.dao.UsersDao;
import model.dao.UsersDaoDdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/register")
public class RegistrationPage extends HttpServlet {

    UsersDao usersDao;
    RolesDao rolesDao;
    @Override
    public void init() {
        try {
            rolesDao = new RolesDaoDbcImpl();
            usersDao = new UsersDaoDdbcImpl();
        } catch (ClassNotFoundException | SQLException e) {
            new IllegalStateException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("/jsp/registration.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqFirstName = req.getParameter("firstName");
        String reqSecondName = req.getParameter("secondName");
        String role = req.getParameter("role");
        String reqPassword = req.getParameter("password");
        String reqCheckPassword = req.getParameter("checkPassword");

        if (reqFirstName.length() < 3 || reqSecondName.length() < 3 || reqPassword.length() < 3) {
            req.setAttribute("wrongLength", "the length of the text in the field must be more than 3 characters");
            req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
        } else if (usersDao.findAllByFirstName(reqFirstName) != null) {
            req.setAttribute("userExist", "such a username already exists");
            req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
        } else if (!reqPassword.equals(reqCheckPassword)) {
            req.setAttribute("passMatch", "passwords don't match");
            req.getRequestDispatcher("/jsp/registration.jsp").forward(req, resp);
        } else {
            try {
                List<Role> rolesFromDB = rolesDao.findByRole(role);
                usersDao.save(new User(reqFirstName, reqSecondName, reqPassword, rolesFromDB.get(0)));
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/HomePage");
    }
}
