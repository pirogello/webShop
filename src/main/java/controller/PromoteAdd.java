package controller;

import model.Product;
import model.dao.ProductsDao;
import model.dao.ProductsDaoDbcImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/promote")
public class PromoteAdd extends HttpServlet {

    private ProductsDao productsDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            productsDao = new ProductsDaoDbcImpl();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int prodId =  Integer.parseInt(req.getParameter("product_id"));
        req.setAttribute("product_id", prodId);
        req.getRequestDispatcher("/jsp/promoteAdd.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean highlighting = Boolean.parseBoolean(req.getParameter("highlighting"));
        boolean priorityInList =  Boolean.parseBoolean(req.getParameter("priorityInList"));
        int prodId = Integer.parseInt(req.getParameter("product_id"));

        Product product = productsDao.find(prodId);

        if(product != null){
            if(!product.isHighlighting())
                product.setHighlighting(highlighting);
            if(priorityInList)
                product.setPriorityInList(product.getPriorityInList() + 1);
        }
        productsDao.update(product);
        resp.sendRedirect(req.getContextPath() + "/HomePage");

    }
}
