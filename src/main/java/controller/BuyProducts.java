package controller;

import model.Order;
import model.Product;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/purchase")
public class BuyProducts extends HttpServlet {

    ProductsDao productsDao;
    OrderDao orderDao;
    CommentsDao commentsDao;

    @Override
    public void init() {
        try {
            productsDao = new ProductsDaoDbcImpl();
            orderDao = new OrderDaoDbcImp();
            commentsDao = new CommentsDaoDbcImpl();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null && user.getRole().getTitle().equals("user")) {
            List<Product> products = productsDao.findAll();
            orderDao.deleteByUserId(user.getId());

            session.removeAttribute("productsAtBasket");
            for (Product pr:products) {
                if(pr.getQuantity() == 0) {
                    commentsDao.deleteByProductId(pr.getId());
                    productsDao.delete(pr.getId());
                }
            }

        }
        resp.sendRedirect(req.getContextPath() + "/HomePage");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("delete") != null){
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            if(user != null && user.getRole().getTitle().equals("user")) {
                int productId = Integer.parseInt(req.getParameter("product_id"));
                //обновить данные продукта
                Order order = orderDao.findByUserIdAndProductId(productId, user.getId());
                Product product = productsDao.find(productId);
                product.setQuantity(product.getQuantity() + order.getQuantityProducts());
                productsDao.update(product);
                //далить этот продукт из сессии
                ArrayList<Product> productsAtBasket = (ArrayList<Product>) session.getAttribute("productsAtBasket");
                for (int i = 0; i < productsAtBasket.size(); i++) {
                    if(productsAtBasket.get(i).getTitle().equals(product.getTitle()))
                        productsAtBasket.remove(i);
                }
                session.setAttribute("productsAtBasket", productsAtBasket);
                //удалить из корзины
                orderDao.delete(order.getId());
                resp.sendRedirect(req.getContextPath() +"/basket");
            }

        }
    }
}
