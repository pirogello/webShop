package controller;

import model.Comment;
import model.dao.CommentsDao;
import model.dao.CommentsDaoDbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/comments")
public class Comments extends HttpServlet {

    CommentsDao commentsDao;
    @Override
    public void init() {
        try {
            commentsDao = new CommentsDaoDbcImpl();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            int prodId = Integer.parseInt(req.getParameter("product_id"));
            List<Comment> comments = commentsDao.findByProductId(prodId);
            if(comments.size() != 0) {
                req.setAttribute("comms", comments);
                req.getRequestDispatcher("/jsp/comments.jsp").forward(req, resp);
            }
            else {
                resp.sendRedirect(req.getContextPath() + "/HomePage");
            }
        }
        catch (NumberFormatException e){
            resp.sendRedirect(req.getContextPath() + "/HomePage");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
