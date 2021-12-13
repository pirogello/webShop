package controller;

import model.Comment;
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

@WebServlet(urlPatterns = "/editComment")
public class EditComment extends HttpServlet {

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
        Integer commId = Integer.parseInt(req.getParameter("comment_id"));
        Comment comment = commentsDao.find(commId);
        if(comment.getUser().getId() == user.getId()){
            req.setAttribute("user_id", user.getId());
            req.setAttribute("comment", comment);
            req.getRequestDispatcher("/jsp/editComment.jsp").forward(req,resp);
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/HomePage");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Integer userId = Integer.parseInt(req.getParameter("user_id"));
        Integer commId = Integer.parseInt(req.getParameter("comment_id"));
        String text = req.getParameter("text");
        if(user.getId() == userId){
            Comment comment = new Comment(commId, null, null, text);
            commentsDao.update(comment);
        }

        resp.sendRedirect(req.getContextPath() + "/HomePage");

    }
}
