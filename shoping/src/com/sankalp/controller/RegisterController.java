package com.sankalp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part;

import com.sankalp.dao.Dao;
import com.sankalp.entites.Category;
import com.sankalp.entites.Product;
import com.sankalp.entites.User;

@WebServlet("/register")
@MultipartConfig
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String opt = request.getParameter("opt");
		if (opt != null) {

			int choice = Integer.parseInt(opt);
			switch (choice) {
			case 1:
				register(request, response);
				break;
			case 2:
				// get jobs from database
				login(request, response);
				break;
			case 3:
				logout(request, response);
				break;
			case 4:
				addCategory(request, response);
				break;
			case 5:
				addProduct(request, response);
				break;
			case 6:
				categoryWisePorduct(request, response);
				break;

			}
		}

	}

	private void categoryWisePorduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String cat = request.getParameter("category");
			System.out.println(cat);

			if (cat.trim().equals("all")) {
				List<Product> plist = Dao.geDao().getProductList();
				request.setAttribute("list", plist);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				int cid = Integer.parseInt(cat.trim());
				System.out.println(cid);
				List<Product> catByPro = Dao.geDao().getProductCategoryList(cid);
				request.setAttribute("list", catByPro);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}

		} catch (Exception e) {
			System.out.println(RegisterController.class.getCanonicalName() + "->" + e.toString());
		}

	}

	private void addProduct(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String pTitle = request.getParameter("pTitle");
			String pDesc = request.getParameter("pDesc");
			int pPrice = Integer.parseInt(request.getParameter("pPrice"));
			int pDiscount = Integer.parseInt(request.getParameter("pDiscount"));
			int pQuanity = Integer.parseInt(request.getParameter("pQuanity"));
			int catId = Integer.parseInt(request.getParameter("catId"));
			Part part = request.getPart("pPhoto");

			Product p = new Product();
			p.setpTitle(pTitle);
			p.setpDesc(pDesc);
			p.setpPrice(pPrice);
			p.setpDiscount(pDiscount);
			p.setpQuanity(pQuanity);
			p.setpPhoto(part.getSubmittedFileName());

			Category c1 = Dao.geDao().categorybyId(catId);
			p.setCategory(c1);
			Product p1 = Dao.geDao().addProduct(p);
			String path = request.getRealPath("image") + File.separator + "product" + File.separator
					+ part.getSubmittedFileName();
			System.out.println(path);

			FileOutputStream fos = new FileOutputStream(path);

			InputStream is = part.getInputStream();

			byte[] data = new byte[is.available()];
			is.read(data);
			fos.write(data);
			fos.close();

			if (p1 != null) {
				request.getSession().setAttribute("message", "Add Product Successfully!.");
				response.sendRedirect("admin.jsp");
				return;
			} else {
				request.getSession().setAttribute("message", "Add Product Failed");
				response.sendRedirect("admin.jsp");
			}

		} catch (Exception e) {
			System.out.println(RegisterController.class.getCanonicalName() + "->" + e.toString());
		}

	}

	private void addCategory(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String categoryTitle = request.getParameter("categoryTitle");
			String categoryDescription = request.getParameter("categoryDescription");
			Category category = new Category(categoryTitle, categoryDescription);
			Category c = Dao.geDao().addCategory(category);
			if (c != null) {
				request.getSession().setAttribute("message", "Add Category Successfully!." + c.getCategoryId());
				response.sendRedirect("admin.jsp");
				return;
			} else {
				request.getSession().setAttribute("message", "Add Category Failed");
				response.sendRedirect("admin.jsp");
			}

		} catch (Exception e) {
			System.out.println(RegisterController.class.getCanonicalName() + "->" + e.toString());
		}

	}

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			request.getSession().removeAttribute("current_user");
			response.sendRedirect("login.jsp");
		} catch (Exception e) {
			System.out.println(RegisterController.class.getCanonicalName() + "->" + e.toString());
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String userEmail = request.getParameter("userEmail");
			String userPassword = request.getParameter("userPassword");
			User user = new User();
			user.setUserEmail(userEmail);
			user.setUserPassword(userPassword);
			User u = Dao.geDao().login(user);
			if (u != null) {
				request.getSession().setAttribute("current_user", u);
				if (u.getUserType().equals("admin")) {
					response.sendRedirect("admin.jsp");
				} else if (u.getUserType().equals("normal")) {
					response.sendRedirect("normal.jsp");
				} else {
					request.getSession().setAttribute("message", "we have not identified user type");
					response.sendRedirect("login.jsp");
				}
			} else {
				request.getSession().setAttribute("message", "login Failed");
				response.sendRedirect("login.jsp");
			}

		} catch (Exception e) {
			System.out.println(RegisterController.class.getCanonicalName() + "->" + e.toString());
		}
	}

	private void register(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			String userName = request.getParameter("userName");
			String userEmail = request.getParameter("userEmail");
			String userPassword = request.getParameter("userPassword");
			String userPhone = request.getParameter("userPhone");
			String userAddress = request.getParameter("userAddress");
			User user = new User();
			user.setUserEmail(userEmail);
			User u = Dao.geDao().emailCheck(user);
			if (u != null) {
				request.getSession().setAttribute("message", "This Email Id Already Used!.");
				response.sendRedirect("register.jsp");
				return;
			} else {
				user = new User(userName, userEmail, userPassword, userPhone, "default.jsp", userAddress, "normal");
				u = Dao.geDao().insert(user);
				if (u != null) {
					request.getSession().setAttribute("message", "Successfully Register!.");
					response.sendRedirect("register.jsp");
					return;
				} else {
					request.getSession().setAttribute("message", "Registeration Failed");
					response.sendRedirect("register.jsp");
				}
			}

		} catch (Exception e) {
			System.out.println(RegisterController.class.getCanonicalName() + "->" + e.toString());
		}
	}

}
