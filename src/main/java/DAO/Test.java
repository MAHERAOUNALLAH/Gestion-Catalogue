package DAO;

import java.sql.Connection;
import java.util.List;

import entities.Produit;

public class Test {

    public static void main(String[] args) {

        // 1. Tester la connexion
        Connection c = SingletonConnexion.getInstance();
        if (c != null) {
            System.out.println("Connexion réussie !");
        } else {
            System.out.println("Échec de la connexion.");
            return;
        }

        IServiceProduit service = new ServiceProduitImpl();

        // 2. Ajouter un produit
        Produit p1 = new Produit("Clavier mécanique", 45.99, 30);
        service.addProduct(p1);
        System.out.println("Produit ajouté !");

        // 3. Afficher tous les produits
        System.out.println("\n--- Liste des produits ---");
        List<Produit> produits = service.getAllProducts();
        for (Produit p : produits) {
            System.out.println(p.getId() + " | " + p.getNom() + " | "
                    + p.getPrix() + " | " + p.getQuantite());
        }

        // 4. Récupérer un produit par son id (adapte l'id selon ta BDD)
        Produit p = service.getProduct(1);
        if (p != null) {
            System.out.println("\nProduit trouvé : " + p.getNom());
        } else {
            System.out.println("\nAucun produit trouvé avec cet id.");
        }

        // 5. Modifier un produit
        if (p != null) {
            p.setPrix(39.99);
            p.setQuantite(25);
            service.updateProduct(p.getId(), p);
            System.out.println("Produit mis à jour !");
        }

        // 6. Rechercher des produits par nom
        System.out.println("\n--- Recherche par nom contenant 'Clavier' ---");
        List<Produit> resultats = service.getProductsByMc("Clavier");
        for (Produit pr : resultats) {
            System.out.println(pr.getId() + " | " + pr.getNom());
        }

        // 7. Supprimer un produit (décommente pour tester)
        // service.deleteProduct(1);
        // System.out.println("Produit supprimé !");
    }
}