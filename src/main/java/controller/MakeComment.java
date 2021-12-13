package controller;

import model.User;
import model.dao.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

import model.Comment;

@WebServlet(urlPatterns = "/makeComment")
public class MakeComment extends HttpServlet {

    CommentsDao commentsDao;
    UsersDao usersDao;
    ProductsDao productsDao;

    @Override
    public void init() {
        try {
            commentsDao = new CommentsDaoDbcImpl();
            usersDao = new UsersDaoDdbcImpl();
            productsDao = new ProductsDaoDbcImpl();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Integer prodId = Integer.parseInt(req.getParameter("product_id"));
        if(user != null && prodId != null){
            req.setAttribute("product_id", prodId);
            req.getRequestDispatcher("jsp/makeComment.jsp").forward(req,resp);
        }
        else{
            resp.sendRedirect(req.getContextPath() + "/HomePage");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Integer prodId = Integer.parseInt(req.getParameter("product_id"));
        String text = req.getParameter("text");
        Comment comment = new Comment(null, usersDao.find(user.getId()), productsDao.find(prodId), text);
        try {
            commentsDao.save(comment);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        resp.sendRedirect(req.getContextPath() + "/HomePage");
    }
}
