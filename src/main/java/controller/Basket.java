package controller;

import model.Order;
import model.Product;
import model.User;
import model.dao.OrderDao;
import model.dao.OrderDaoDbcImp;
import model.dao.ProductsDao;
import model.dao.ProductsDaoDbcImpl;

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

@WebServlet(urlPatterns = "/basket")
public class Basket extends HttpServlet {

    ProductsDao productsDao;
    OrderDao orderDao;

    @Override
    public void init() {
        try {
            productsDao = new ProductsDaoDbcImpl();
            orderDao = new OrderDaoDbcImp();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null && user.getRole().getTitle().equals("user")) {
            List<Order> orders = orderDao.findAllByUserId(user.getId());
            ArrayList<Product> products = new ArrayList<>();

            for (int i = 0; i < orders.size(); i++) {
                Product product = productsDao.find(orders.get(i).getProductId());
                product.setQuantity(orders.get(i).getQuantityProducts());
                products.add(product);
            }
            session.setAttribute("productsAtBasket", products);
            req.getRequestDispatcher("/jsp/basket.jsp").forward(req, resp);
        }else{
            resp.sendRedirect(req.getContextPath() + "/HomePage");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
            if (user != null && user.getRole().getTitle().equals("user")) {
                try {
                    int userId = Integer.parseInt(req.getParameter("user_id"));
                    int productId = Integer.parseInt(req.getParameter("product_id"));
                    int quantityProducts = Integer.parseInt(req.getParameter("quantity_products"));

                    //обновить или добавить данные по заказу
                    Order order = new Order(0, userId, productId, quantityProducts);
                    Order ordFromDB = orderDao.findByUserIdAndProductId(productId, userId);
                    if(ordFromDB != null){
                        order.setQuantityProducts(ordFromDB.getQuantityProducts() + quantityProducts);
                        orderDao.update(order);
                    }
                    else
                        orderDao.save(order);

                    //обновить данные по продукту
                    Product product = productsDao.find(productId);
                    int count = product.getQuantity() - quantityProducts;
                    product.setQuantity(count);
                    productsDao.update(product);

                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        resp.sendRedirect(req.getContextPath() + "/HomePage");
    }
}
