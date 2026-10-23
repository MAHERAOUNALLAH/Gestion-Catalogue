package web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.IServiceProduit;
import DAO.ServiceProduitImpl;
import entities.Produit;

@WebServlet(urlPatterns = { "/", "/accueil", "/search", "/delete", "/modify", "/add" })
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Noms des nouvelles vues JSTL (un seul endroit à modifier si besoin)
	private static final String VUE_LISTE = "/vue2.jsp";
	private static final String VUE_AJOUT = "/ajout2JSTL.jsp";
	private static final String VUE_MODIF = "/modify2JSTL.jsp";

	private IServiceProduit service;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		service = new ServiceProduitImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path = request.getServletPath();

		switch (path) {
		case "/":
		case "/accueil":
			request.setAttribute("prods", service.getAllProducts());
			request.getRequestDispatcher(VUE_LISTE).forward(request, response);
			break;

		case "/search":
			String mc = request.getParameter("mc");
			if (mc == null) {
				mc = "";
			}
			request.setAttribute("prods", service.getProductsByMc(mc));
			request.setAttribute("mc", mc);
			request.getRequestDispatcher(VUE_LISTE).forward(request, response);
			break;

		case "/delete":
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				service.deleteProduct(id);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/accueil");
			break;

		case "/modify":
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				request.setAttribute("produit", service.getProduct(id));
			} catch (NumberFormatException e) {
				request.setAttribute("produit", null);
			}
			request.getRequestDispatcher(VUE_MODIF).forward(request, response);
			break;

		case "/add":
			request.getRequestDispatcher(VUE_AJOUT).forward(request, response);
			break;

		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path = request.getServletPath();

		switch (path) {
		case "/add":
			try {
				String nom = request.getParameter("nom");
				double prix = Double.parseDouble(request.getParameter("prix"));
				int quantite = Integer.parseInt(request.getParameter("quantite"));

				if (nom == null || nom.trim().isEmpty()) {
					throw new IllegalArgumentException("Le nom est obligatoire.");
				}

				service.addProduct(new Produit(nom.trim(), prix, quantite));
				response.sendRedirect(request.getContextPath() + "/accueil");

			} catch (NumberFormatException e) {
				request.setAttribute("erreur", "Prix ou quantité invalide.");
				request.getRequestDispatcher(VUE_AJOUT).forward(request, response);
			} catch (IllegalArgumentException e) {
				request.setAttribute("erreur", e.getMessage());
				request.getRequestDispatcher(VUE_AJOUT).forward(request, response);
			}
			break;

		case "/modify":
			try {
				int id = Integer.parseInt(request.getParameter("id"));
				String nom = request.getParameter("nom");
				double prix = Double.parseDouble(request.getParameter("prix"));
				int quantite = Integer.parseInt(request.getParameter("quantite"));

				if (nom == null || nom.trim().isEmpty()) {
					throw new IllegalArgumentException("Le nom est obligatoire.");
				}

				service.updateProduct(id, new Produit(nom.trim(), prix, quantite));
				response.sendRedirect(request.getContextPath() + "/accueil");

			} catch (IllegalArgumentException e) {
				// NumberFormatException hérite d'IllegalArgumentException
				request.setAttribute("erreur", e instanceof NumberFormatException
						? "Valeurs invalides." : e.getMessage());
				try {
					int id = Integer.parseInt(request.getParameter("id"));
					request.setAttribute("produit", service.getProduct(id));
				} catch (NumberFormatException ex) {
					request.setAttribute("produit", null);
				}
				request.getRequestDispatcher(VUE_MODIF).forward(request, response);
			}
			break;

		default:
			response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
	}
}