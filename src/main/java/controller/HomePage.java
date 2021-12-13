package controller;

import model.Product;
import model.dao.ProductsDao;
import model.dao.ProductsDaoDbcImpl;
import model.dao.UsersDao;
import model.dao.UsersDaoDdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


@WebServlet(urlPatterns = "/HomePage")
public class HomePage extends HttpServlet {

	UsersDao usersDao;
	ProductsDao productsDao;

	@Override
	public void init() {
		try {
			usersDao = new UsersDaoDdbcImpl();
			productsDao = new ProductsDaoDbcImpl();
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		if(req.getParameter("exit") != null) {
			HttpSession session = req.getSession();
			session.removeAttribute("user");
		}
		ArrayList<Product> products = (ArrayList<Product>) productsDao.findAll();
		products.sort((o1, o2) -> {
			if(o1.getPriorityInList() == o2.getPriorityInList())
				return 0;
			if(o1.getPriorityInList() < o2.getPriorityInList())
				return 1;
			return -1;
		});
		req.setAttribute("products", products);

		req.getRequestDispatcher("/jsp/home.jsp").forward(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*
		HttpSession session = req.getSession();
		if(session.getAttribute("user") == null) {
			User user = usersDao.findAllByFirstName(req.getParameter("login"));
			if (user == null) {
				req.setAttribute("noUser", "не верное имя пользователя или пароль");
				req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
			} else {
				session.setAttribute("user", user);
				req.getRequestDispatcher("/jsp/home.jsp").forward(req,resp);
			}
		}
		else
			req.getRequestDispatcher("/jsp/home.jsp").forward(req,resp);*/
	}

}
