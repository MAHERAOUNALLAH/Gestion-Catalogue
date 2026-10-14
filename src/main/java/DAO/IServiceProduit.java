package DAO;

import java.util.List;
import entities.Produit;

public interface IServiceProduit {
    void addProduct(Produit p);
    void deleteProduct(int id);
    void updateProduct(int id, Produit p);
    Produit getProduct(int id);
    List<Produit> getAllProducts();
    List<Produit> getProductsByMc(String mc);
}