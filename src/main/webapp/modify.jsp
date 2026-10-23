<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="entities.Produit"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modifier un produit</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
</head>
<body>
	<%
		Produit p = (Produit) request.getAttribute("produit");
	%>
	<div class="container mt-4" style="max-width: 500px;">
		<div class="card">
			<div class="card-header">
				<h3 class="text-warning">Modifier un produit</h3>
			</div>
			<div class="card-body">
				<form action="modify" method="post">

					<input type="hidden" name="id" value="<%=p.getId()%>">

					<div class="mb-3">
						<label class="form-label">Id</label>
						<input type="text" class="form-control" value="<%=p.getId()%>" disabled>
					</div>

					<div class="mb-3">
						<label class="form-label">Nom</label>
						<input type="text" name="nom" class="form-control" required
							value="<%=p.getNom()%>">
					</div>

					<div class="mb-3">
						<label class="form-label">Prix</label>
						<input type="number" name="prix" step="0.01" min="0"
							class="form-control" required value="<%=p.getPrix()%>">
					</div>

					<div class="mb-3">
						<label class="form-label">Quantité</label>
						<input type="number" name="quantite" min="0"
							class="form-control" required value="<%=p.getQuantite()%>">
					</div>

					<button type="submit" class="btn btn-warning">Enregistrer</button>
					<a href="accueil" class="btn btn-secondary">Annuler</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>