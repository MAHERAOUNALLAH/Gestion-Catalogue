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
import entities.Produit; // doit être identique au package de Produit.java

@WebServlet(urlPatterns = { "/", "/accueil", "/search", "/delete", "/modify", "/add" })
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
		} else if (path.equals("/modify")) {
			int id =Integer.parseInt(request.getParameter("id"));
			Produit p=service.getProduct(id);
			
			
			
			
			
			
			request.setAttribute("produit", p);
			request.getRequestDispatcher("/modify.jsp").forward(request, response);
			
			
			
			

		} else if (path.equals("/add")) {
			
			
			request.getRequestDispatcher("/ajout.jsp").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path = request.getServletPath();
		
			if (path.equals("/add")) {
					
					String nom = request.getParameter("nom");
					double prix = Double.parseDouble(request.getParameter("prix"));
					int quantite = Integer.parseInt(request.getParameter("quantite"));
					Produit p=new Produit(nom,prix,quantite);
					service.addProduct(p);
					response.sendRedirect(request.getContextPath() + "/accueil");
		
		
			}else if (path.equals("/modify")){
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					String nom = request.getParameter("nom");
					double prix = Double.parseDouble(request.getParameter("prix"));
					int quantite = Integer.parseInt(request.getParameter("quantite"));	
					Produit p=new Produit(nom,prix,quantite);
					service.updateProduct(id, p);
				
				
			
					response.sendRedirect(request.getContextPath() + "/accueil");
				
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				
				
			}
			
			
			
			
			
			


		
	}

}