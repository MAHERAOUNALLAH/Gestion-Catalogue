<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, entities.Produit"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des produits</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
	<%
		List<Produit> liste = (List<Produit>) request.getAttribute("prods");
		String mc = (String) request.getAttribute("mc");
		if (mc == null) {
			mc = "";
		}
	%>
	<div class="container mt-4">
		<div class="card">
			<div class="card-header">
				<h2 class="text-danger">Liste des Produits</h2>
			</div>
			<div class="card-body">

				<form action="search" method="get" class="row g-2 mb-3">
					<div class="col-auto">
						<input type="text" name="mc" class="form-control"
							placeholder="Rechercher un produit..." value="<%=mc%>">
					</div>
					<div class="col-auto">
						<button type="submit" class="btn btn-primary">Chercher</button>
					</div>
				</form>

				<table class="table">
					<tr>
						<th>Id</th>
						<th>Nom</th>
						<th>Prix</th>
						<th>Quantité</th>
						<th>Actions</th>
					</tr>
					<%
						if (liste != null) {
							for (Produit p : liste) {
					%>
					<tr>
						<td><%=p.getId()%></td>
						<td><%=p.getNom()%></td>
						<td><%=p.getPrix()%></td>
						<td><%=p.getQuantite()%></td>
						<td><a class="btn btn-danger"
							href="delete?id=<%=p.getId()%>"
							onclick="return confirm('Supprimer ce produit ?');">Supprimer</a></td>
					</tr>
					<%
							}
						}
					%>
				</table>

			</div>
		</div>
	</div>
</body>
</html>