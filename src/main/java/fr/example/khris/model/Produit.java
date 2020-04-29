package fr.example.khris.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe model Java Bean creant la table PRODUITS avec tous ses attr.
 * Classe serialisable pour possibilité d'utilisationd e ce java bean dans une architecture distribuée
 */
@Entity //INdique que cette classe est persistante
@Table(name = "PRODUIT") // precise le nom de la table par le mapping
public class Produit implements Serializable {
    
    @Id // Associe ce champ de la table en tant que clef primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto incrementation
    @Column(name = "ID") // associe un champ de la colonne à cet attr
    private int id;
    @Column(name = "DESC") // associe un champ de colonne à cet attr
    private String description;
    
    // Ces 2 attrs etant laissés tels quels, ils seront representés dans la la table sous forme de colonne tels qu'ils sont
    // notés ici
    private double prix;
    private int quantité;
    
    
    /*
    Constructeur sans param obligatoire, sans quoi, la serialisation ne pourra pas se faire
     */
    public Produit() {
    }
    
    
    //======================================= GETTERS & SETTERS ===============================================================
    public int getId() {
        return id;
    }
    
    public void setId(int pId) {
        id = pId;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String pDescription) {
        description = pDescription;
    }
    
    public double getPrix() {
        return prix;
    }
    
    public void setPrix(double pPrix) {
        prix = pPrix;
    }
    
    public int getQuantité() {
        return quantité;
    }
    
    public void setQuantité(int pQuantité) {
        quantité = pQuantité;
    }
}
