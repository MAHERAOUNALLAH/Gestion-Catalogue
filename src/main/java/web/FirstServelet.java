package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.IServiceProduit;
import DAO.ServiceProduitImpl; 
import entities.Produit;

@WebServlet("/FirstServelet")
public class FirstServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IServiceProduit service;

	public FirstServelet() {
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
		response.setContentType("text/html;charset=UTF-8");
		List<Produit> liste = service.getAllProducts();
		PrintWriter out = response.getWriter();

		out.println("<html><body><table border='1'>");
		out.println("<tr><th>Nom</th><th>Prix</th></tr>");
		for (Produit p : liste) {
			out.println("<tr><td>" + p.getNom() + "</td><td>" + p.getPrix() + "</td></tr>");
		}
		out.println("</table></body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}