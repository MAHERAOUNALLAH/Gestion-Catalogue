<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ajouter un produit</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
	<br>
	<br>
	<div class="container mt-4" style="max-width: 500px;">
		<div class="card">
			<div class="card-header">
				<h3 class="text-success">Ajouter un produit</h3>
			</div>
			<div class="card-body">

				<c:if test="${not empty erreur}">
					<div class="alert alert-danger">${erreur}</div>
				</c:if>

				<form action="<c:url value='/add'/>" method="post">

					<div class="mb-3">
						<label class="form-label">Nom</label> <input type="text"
							name="nom" value="<c:out value='${param.nom}'/>"
							class="form-control" required>
					</div>

					<div class="mb-3">
						<label class="form-label">Prix</label> <input type="number"
							name="prix" value="${param.prix}" step="0.01" min="0"
							class="form-control" required>
					</div>

					<div class="mb-3">
						<label class="form-label">Quantité</label> <input type="number"
							name="quantite" value="${param.quantite}" min="0"
							class="form-control" required>
					</div>

					<button type="submit" class="btn btn-success">Enregistrer</button>
					<a href="<c:url value='/accueil'/>" class="btn btn-secondary">Annuler</a>
				</form>
			</div>
		</div>
	</div>

</body>
</html>