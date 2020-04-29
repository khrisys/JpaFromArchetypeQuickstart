package fr.example.khris.dao;

import fr.example.khris.model.Produit;

import javax.persistence.*;
import java.util.List;

/**
 * Implementation du CRUD des Produits
 */
public class ProduitDaoImpl implements IProduitDao {
    
    // Decalaration de l'objet EntityManager qui permet de gérer la persistance des entités
    private EntityManager entityManager;
    
    

    
    /**
     * Constructeur
     */
    public ProduitDaoImpl() {
        
        //CREATION DE L'OBJET ENTITY MANAGER FACTORY
        // Lit le fichier persistence.xml
        // cree le datasource et etablit une connexion avec la bdd
        // si table n'existe pas, il les créé, sinon, il touche pas
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PRODUITS_PERSISTENCE_UNIT");
        
        //CREATION DE L'OBJET ENTITY MANAGER
        // entityManager est l'objet qui permet de gerer la persistence des entités
        entityManager = entityManagerFactory.createEntityManager();
    }
    
    /**
     * Enregistrer le produit dans la bdd
     *
     * @param pProduit
     */
    @Override
    public void save(Produit pProduit) {
        // Creation d'une transaction
        EntityTransaction entityTransaction = entityManager.getTransaction();
        
        // Transaction
        entityTransaction.begin();
        try {
            // Avec ce code, hibernate va automatiquement creer le "INSERT INTO ...", inserer le produit
            entityManager.persist(pProduit);
            // Valider la transaction si tout est ok
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
        }
    }
    
    /**
     * Retourne tous les Produits
     *
     * @return Liste de Produits
     */
    @Override
    public List<Produit> findAll() {
        
        // Attention!!! Ici, le langage est du HQL (appelé aussi JPA QL)! Ici, je ne connais pas les tables ni la bdd. Je ne sais
        // pas ce qu'il y aura en bdd. Je ne connais que les classes. Donc, les requetes seront basées sur les classes et pas
        // les tables. D'où une requete comme "SELECT p FROM Produit p"!!!
        
        // C'est le dialect qui a été defini dans le fichier de conf qui se chargera d'ecrire la bonne requete avec le bon
        // dialect en fonction de la bdd (Oracle, postgresql, MariaDB, mysql, nosql?, etc). C'est comme ca que la portabilité
        // des bdds est gérée
        
        // Dans la requete, ce n'est donc pas le nom de la table qui est ecrite mais le nom de la classe!!!!
        Query vQuery = entityManager.createQuery("SELECT p FROM Produit p");
        //Hibernate fait derriere l'equivalent d'une boucle pour aller chercher tous les produiits
        return vQuery.getResultList();
    }
    
    /**
     * Retourne tous les produits trouvés avec un mot_clef
     *
     * @param mot_clef
     * @return Liste de Produit
     */
    @Override
    public List<Produit> findByDesignation(String mot_clef) {
        
        Query vQuery = entityManager.createQuery("SELECT p FROM Produit p WHERE p.description like :x");
        vQuery.setParameter("x", "%" + mot_clef + "%");
        return vQuery.getResultList();
    }
    
    /**
     * Pour trouver un objet par son ID, ne pas ecrire une requete mais utiliser la methode "find()". Si le produit n'existe
     * pas, retourne NULL
     *
     * @param id
     * @return Produit
     */
    @Override
    public Produit findById(int id) {
        Produit vProduit = entityManager.find(Produit.class, id);
        return vProduit;
    }
    
    /**
     * MAJ un produit. Pas de requete à ecrire, on utilise la methode "merge()". La methode "persist()" qu'on utilise pour
     * faire le INSERT peut aussi faire le UPDATE
     *
     * @param pProduit
     */
    @Override
    public void update(Produit pProduit) {
        entityManager.merge(pProduit);
        // entityManager.persist(pProduit);
    }
    
    /**
     * Supprime l'objet.
     *
     * @param id
     */
    @Override
    public void deleteById(int id) {
        Produit vProduit = entityManager.find(Produit.class, id);
        entityManager.remove(vProduit);
    
    }
}
