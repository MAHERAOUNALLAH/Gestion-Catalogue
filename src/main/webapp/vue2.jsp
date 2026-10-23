<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des produits</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-4">
		<div class="card">
			<div class="card-header">
				<h2 class="text-danger">Liste des Produits</h2>
			</div>
			<div>
				<a href="add" class="btn btn-secondary">Ajouter Produit</a>
			</div>
			<div class="card-body">

				<form action="search" method="get" class="row g-2 mb-3">
					<div class="col-auto">
						<input type="text" name="mc" class="form-control"
							placeholder="Rechercher un produit..." value="${mc}">
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
						<th colspan="2">Actions</th>
					</tr>
					<c:forEach var="p" items="${prods}">
						<tr>
							<td>${p.id}</td>
							<td>${p.nom}</td>
							<td>${p.prix}</td>
							<td>${p.quantite}</td>
							<td><a class="btn btn-danger" href="delete?id=${p.id}"
								onclick="return confirm('Supprimer ce produit ?');">Supprimer</a></td>
							<td><a class="btn btn-warning" href="modify?id=${p.id}"
								onclick="return confirm('Voulez-vous modifier ce produit ?');">Modifier</a></td>
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>
	</div>
</body>
</html>