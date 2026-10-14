package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.IServiceProduit;
import DAO.ServiceProduitImpl;
import entities.Produit;   // doit être identique au package de Produit.java

@WebServlet(urlPatterns = { "/", "/accueil", "/search", "/delete" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IServiceProduit service;

	public Controller() {
		super();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		service = new ServiceProduitImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getServletPath();
		System.out.println(path);

		if (path.equals("/") || path.equals("/accueil")) {
			List<Produit> liste = service.getAllProducts();
			request.setAttribute("prods", liste);
			request.getRequestDispatcher("/vue.jsp").forward(request, response);

		} else if (path.equals("/search")) {
			String mc = request.getParameter("mc");
			if (mc == null) {
				mc = "";
			}
			request.setAttribute("prods", service.getProductsByMc(mc));
			request.setAttribute("mc", mc);
			request.getRequestDispatcher("/vue.jsp").forward(request, response);

		} else if (path.equals("/delete")) {
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				service.deleteProduct(id);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/accueil");

		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}