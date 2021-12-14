package controller;

import model.Product;
import model.User;
import model.dao.ProductsDao;
import model.dao.ProductsDaoDbcImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;


@MultipartConfig
@WebServlet(urlPatterns = "/editAdd")
public class EditAdd extends HttpServlet {


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
        req.getRequestDispatcher("jsp/editAdd.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        Product product = (Product) session.getAttribute("productForEdit");
        if(user != null && user.getRole().getTitle().equals("admin")) {
            String title = getSimpleParam(req.getPart("title").getInputStream());
            int quantity = Integer.parseInt(getSimpleParam(req.getPart("quantity").getInputStream()));
            String description = getSimpleParam(req.getPart("description").getInputStream());
            int price = Integer.parseInt(getSimpleParam(req.getPart("price").getInputStream()));
            Part image = req.getPart("image");
            String imageName = image.getSubmittedFileName();
            String imgForJsp = "\"files/" + user.getFirstName() + "/" + imageName + "\"";
            String path = req.getServletContext().getRealPath("/files" + File.separator + user.getFirstName());
            Product newProduct = new Product();
            newProduct.setId(product.getId());
            newProduct.setTitle(title);
            newProduct.setPrice(price);
            newProduct.setQuantity(quantity);
            newProduct.setDescription(description);
            newProduct.setUser(user);
            newProduct.setHighlighting(product.isHighlighting());
            newProduct.setPriorityInList(product.getPriorityInList());
            //System.out.println(1+imageName+1);
            if(imageName != "") {
                uploadFile(image.getInputStream(), path, imageName);
                newProduct.setImageHref(imgForJsp);
            } else {
                newProduct.setImageHref(product.getImageHref());
            }
            productsDao.update(newProduct);
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
