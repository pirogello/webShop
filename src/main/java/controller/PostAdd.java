package controller;

import model.Product;
import model.User;
import model.dao.ProductsDao;
import model.dao.ProductsDaoDbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@MultipartConfig
@WebServlet(urlPatterns = "/postAdd")
public class PostAdd extends HttpServlet {

    ProductsDao productsDao;

    @Override
    public void init() {
        try {
            productsDao = new ProductsDaoDbcImpl();
        } catch (ClassNotFoundException | SQLException e) {
            new IllegalStateException(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if(user != null && user.getRole().getTitle().equals("admin"))
            req.getRequestDispatcher("jsp/postAdd.jsp").forward(req,resp);
        else{
            resp.sendRedirect(req.getContextPath() + "/HomePage");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        if(user != null && user.getRole().getTitle().equals("admin")) {
            String title = getSimpleParam(req.getPart("title").getInputStream());
            int quantity = Integer.parseInt(getSimpleParam(req.getPart("quantity").getInputStream()));
            String description = getSimpleParam(req.getPart("description").getInputStream());
            int price = Integer.parseInt(getSimpleParam(req.getPart("price").getInputStream()));
            Part image = req.getPart("image");
            String imageName = image.getSubmittedFileName();
            String imgForJsp = "\"files/" + user.getFirstName() + "/" + imageName + "\"";
            String path = req.getServletContext().getRealPath("/files" + File.separator + user.getFirstName());
            uploadFile(image.getInputStream(), path, imageName);
            Product product = new Product(0, title,  quantity,  price,  imgForJsp,  description, user,false, 0);
            try {
                productsDao.save(product);
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
        resp.sendRedirect(req.getContextPath() + "/HomePage");
    }

    private String getSimpleParam(InputStream is){
        String param = null;
        try {
            byte[] byt = new byte[is.available()];
            is.read(byt);
            param = new String(byt, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return param;
    }

    private boolean uploadFile(InputStream is, String path,String imageName) {
        boolean test = false;
        try {
            File dir = new File(path);
            if(!dir.exists())
                dir.mkdir();
            byte[] byt = new byte[is.available()];
            is.read(byt);
            FileOutputStream fops = new FileOutputStream(path + File.separator + imageName);
            fops.write(byt);
            fops.flush();
            fops.close();
            test = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return test;
    }
}
