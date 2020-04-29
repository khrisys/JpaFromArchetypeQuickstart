package fr.example.khris;

import fr.example.khris.dao.IProduitDao;
import fr.example.khris.dao.ProduitDaoImpl;
import fr.example.khris.model.Produit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        System.out.println( "Hello World!" );
    
        Produit vProduit = new Produit();
        vProduit.setDescription("Imprimante HD");
        vProduit.setPrix(1200);
        vProduit.setQuantité(2);
    
        IProduitDao produitDao = new ProduitDaoImpl();
        produitDao.save(vProduit);
    }

}
