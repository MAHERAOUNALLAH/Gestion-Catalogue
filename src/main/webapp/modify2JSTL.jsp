<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier un produit</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-4" style="max-width: 500px;">
		<div class="card">
			<div class="card-header">
				<h3 class="text-warning">Modifier un produit</h3>
			</div>
			<div class="card-body">

				<c:if test="${empty produit}">
					<div class="alert alert-danger">Produit introuvable.</div>
					<a href="<c:url value='/accueil'/>" class="btn btn-secondary">Retour</a>
				</c:if>

				<c:if test="${not empty produit}">
					<form action="<c:url value='/modify'/>" method="post">

						<input type="hidden" name="id" value="${produit.id}">

						<div class="mb-3">
							<label class="form-label">Id</label> <input type="text"
								class="form-control" value="${produit.id}" disabled>
						</div>

						<div class="mb-3">
							<label class="form-label">Nom</label> <input type="text"
								name="nom" class="form-control" required
								value="<c:out value='${produit.nom}'/>">
						</div>

						<div class="mb-3">
							<label class="form-label">Prix</label> <input type="number"
								name="prix" step="0.01" min="0" class="form-control" required
								value="${produit.prix}">
						</div>

						<div class="mb-3">
							<label class="form-label">Quantité</label> <input type="number"
								name="quantite" min="0" class="form-control" required
								value="${produit.quantite}">
						</div>

						<button type="submit" class="btn btn-warning">Enregistrer</button>
						<a href="<c:url value='/accueil'/>" class="btn btn-secondary">Annuler</a>
					</form>
				</c:if>

			</div>
		</div>
	</div>
</body>
</html>