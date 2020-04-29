package fr.example.khris.dao;

import fr.example.khris.model.Produit;

import java.util.List;

/**
 * Interface de gestion du CRUD des Produits
 */
public interface IProduitDao {
    
    void save(Produit pProduit);
    
    List<Produit> findAll();
    
    List<Produit> findByDesignation(String mot_clef);
    
    Produit findById(int id);
    
    void update(Produit pProduit);
    
    void deleteById(int id);
}
