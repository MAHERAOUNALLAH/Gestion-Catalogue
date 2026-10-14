package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Produit;

public class ServiceProduitImpl implements IServiceProduit {

    private Connection connection;

    public ServiceProduitImpl() {
        connection = SingletonConnexion.getInstance();
    }

    @Override
    public void addProduct(Produit p) {
        String sql = "INSERT INTO produit (nom, prix, quantite) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getNom());
            ps.setDouble(2, p.getPrix());
            ps.setInt(3, p.getQuantite());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int id) {
        String sql = "DELETE FROM produit WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(int id, Produit p) {
        String sql = "UPDATE produit SET nom=?, prix=?, quantite=? WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getNom());
            ps.setDouble(2, p.getPrix());
            ps.setInt(3, p.getQuantite());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Produit getProduct(int id) {
        Produit p = null;
        String sql = "SELECT * FROM produit WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Produit(rs.getInt("id"), rs.getString("nom"),
                        rs.getDouble("prix"), rs.getInt("quantite"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    @Override
    public List<Produit> getAllProducts() {
        List<Produit> liste = new ArrayList<>();
        String sql = "SELECT * FROM produit";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                liste.add(new Produit(rs.getInt("id"), rs.getString("nom"),
                        rs.getDouble("prix"), rs.getInt("quantite")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public List<Produit> getProductsByMc(String mc) {
        List<Produit> liste = new ArrayList<>();
        String sql = "SELECT * FROM produit WHERE nom LIKE ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "%" + mc + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                liste.add(new Produit(rs.getInt("id"), rs.getString("nom"),
                        rs.getDouble("prix"), rs.getInt("quantite")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return liste;
    }
}