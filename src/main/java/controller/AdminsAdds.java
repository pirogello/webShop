package controller;

import model.User;
import model.dao.ProductsDao;
import model.dao.ProductsDaoDbcImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/myAdds")
public class AdminsAdds extends HttpServlet {

    private ProductsDao productsDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            productsDao = new ProductsDaoDbcImpl();
        } catch (SQLException | ClassNotFoundException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/adminsAdds.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null && user.getRole().getTitle().equals("admin")) {
            session.setAttribute("productForEdit", productsDao.find(Integer.parseInt(req.getParameter("product_id"))));
            resp.sendRedirect(req.getContextPath() + "/editAdd");
        }
        else
            resp.sendRedirect(req.getContextPath() + "/HomePage");
    }
}
